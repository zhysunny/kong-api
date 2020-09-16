package com.zhysunny.kong.service;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import com.zhysunny.common.json.JsonUtils;
import com.zhysunny.kong.entity.*;
import com.zhysunny.rest.client.RestResponse;
import org.junit.*;
import org.powermock.reflect.Whitebox;
import java.util.Arrays;

/**
 * KongRouteService Test
 */
public class KongRouteServiceTest {

    private KongServiceService kongServiceService;
    private KongRouteService kongRouteService;
    private String service = "test_service";
    private String route = "test_route";
    private String route2 = "test_route_2";
    private String path = "/rest";
    private String path2 = "/rest2";
    private Service initService = Service.INIT;
    private Route initRoute = Route.INIT;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test KongRouteService Class Start...");
    }

    @Before
    public void before() throws Exception {
        // 添加一个service
        kongServiceService = new KongServiceService();
        Whitebox.setInternalState(kongServiceService, "adminUrl", "http://192.168.1.12:8001");
        initService.setName(service);
        kongServiceService.delete(service);
        kongServiceService.add(initService);

        kongRouteService = new KongRouteService();
        Whitebox.setInternalState(kongRouteService, "adminUrl", "http://192.168.1.12:8001");
        initRoute.setName(route);
        initRoute.setPaths(Arrays.asList(path));
        // 初始化，先清空
        kongRouteService.delete(route);
        kongRouteService.delete(route2);
    }

    @After
    public void after() throws Exception {
        kongRouteService.delete(route);
        kongRouteService.delete(route2);
        kongServiceService.delete(service);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test KongRouteService Class End...");
    }

    @Test
    public void testMain() throws Exception {
        System.out.println("添加");
        RestResponse response = kongRouteService.add(service, initRoute);
        assertThat(response.getCode()).isEqualTo(201);
        Route parse = JsonUtils.parse(response.getResponse(), Route.class);
        assertThat(parse.getName()).isEqualTo(route);
        System.out.println(parse);

        System.out.println("再添加，报错：已存在");
        response = kongRouteService.add(service, initRoute);
        assertThat(response.getCode()).isEqualTo(409);
        ErrorMsg errorMsg = JsonUtils.parse(response.getResponse(), ErrorMsg.class);
        assertThat(errorMsg.getName()).isEqualTo("unique constraint violation");
        assertThat(errorMsg.getCode()).isEqualTo(5);
        System.out.println(errorMsg);

        System.out.println("修改");
        initRoute.setName(route2);
        initRoute.setPaths(Arrays.asList(path2));
        response = kongRouteService.update(route, initRoute);
        assertThat(response.getCode()).isEqualTo(200);
        parse = JsonUtils.parse(response.getResponse(), Route.class);
        assertThat(parse.getName()).isEqualTo(route2);
        assertThat(parse.getPaths().get(0)).isEqualTo(path2);
        System.out.println(parse);

        System.out.println("再修改，报错：不存在");
        response = kongRouteService.update(route, initRoute);
        assertThat(response.getCode()).isEqualTo(404);
        errorMsg = JsonUtils.parse(response.getResponse(), ErrorMsg.class);
        assertThat(errorMsg.getName()).isEqualTo("not found");
        assertThat(errorMsg.getCode()).isEqualTo(6);
        System.out.println(errorMsg);

        System.out.println("修改不存在的，使用put，自动创建");
        // init中name被忽略
        response = kongRouteService.put(route, initRoute);
        assertThat(response.getCode()).isEqualTo(200);
        parse = JsonUtils.parse(response.getResponse(), Route.class);
        assertThat(parse.getName()).isEqualTo(route);
        assertThat(parse.getPaths().get(0)).isEqualTo(path2);
        System.out.println(parse);

        System.out.println("查询所有");
        response = kongRouteService.list();
        assertThat(response.getCode()).isEqualTo(200);
        Routes list = JsonUtils.parse(response.getResponse(), Routes.class);
        System.out.println(list);

        System.out.println("查询service下所有");
        response = kongRouteService.list(service);
        assertThat(response.getCode()).isEqualTo(200);
        list = JsonUtils.parse(response.getResponse(), Routes.class);
        assertThat(list.getData().size()).isEqualTo(1);
        System.out.println(list);

        System.out.println("查询单个");
        response = kongRouteService.get(route);
        assertThat(response.getCode()).isEqualTo(200);
        parse = JsonUtils.parse(response.getResponse(), Route.class);
        assertThat(parse.getName()).isEqualTo(route);
        System.out.println(parse);
    }

} 
