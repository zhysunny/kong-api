package com.zhysunny.kong.service;

import com.zhysunny.common.json.JsonUtils;
import com.zhysunny.kong.entity.ErrorMsg;
import com.zhysunny.kong.entity.Service;
import com.zhysunny.kong.entity.Services;
import com.zhysunny.rest.client.RestResponse;
import org.junit.*;
import org.powermock.reflect.Whitebox;
import static org.assertj.core.api.Assertions.*;

/**
 * KongServiceService Test
 */
public class KongServiceServiceTest {

    private KongServiceService kongServiceService;
    private String service = "test_service";
    private String service2 = "test_service_2";
    private String host = "localhost";
    private Service init = Service.INIT;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test KongServiceService Class Start...");
    }

    @Before
    public void before() throws Exception {
        kongServiceService = new KongServiceService();
        Whitebox.setInternalState(kongServiceService, "adminUrl", "http://192.168.1.12:8001");
        init.setName(service);
        // 初始化，先清空
        kongServiceService.delete(service);
        kongServiceService.delete(service2);
    }

    @After
    public void after() throws Exception {
        // 删除
        kongServiceService.delete(service);
        kongServiceService.delete(service2);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test KongServiceService Class End...");
    }

    @Test
    public void testMain() throws Exception {
        System.out.println("添加");
        RestResponse response = kongServiceService.add(init);
        assertThat(response.getCode()).isEqualTo(201);
        Service parse = JsonUtils.parse(response.getResponse(), Service.class);
        assertThat(parse.getName()).isEqualTo(service);
        System.out.println(parse);

        System.out.println("再添加，报错：已存在");
        response = kongServiceService.add(init);
        assertThat(response.getCode()).isEqualTo(409);
        ErrorMsg errorMsg = JsonUtils.parse(response.getResponse(), ErrorMsg.class);
        assertThat(errorMsg.getName()).isEqualTo("unique constraint violation");
        assertThat(errorMsg.getCode()).isEqualTo(5);
        System.out.println(errorMsg);

        System.out.println("修改");
        init.setName(service2);
        init.setHost(host);
        response = kongServiceService.update(service, init);
        assertThat(response.getCode()).isEqualTo(200);
        parse = JsonUtils.parse(response.getResponse(), Service.class);
        assertThat(parse.getName()).isEqualTo(service2);
        assertThat(parse.getHost()).isEqualTo(host);
        System.out.println(parse);

        System.out.println("再修改，报错：不存在");
        response = kongServiceService.update(service, init);
        assertThat(response.getCode()).isEqualTo(404);
        errorMsg = JsonUtils.parse(response.getResponse(), ErrorMsg.class);
        assertThat(errorMsg.getName()).isEqualTo("not found");
        assertThat(errorMsg.getCode()).isEqualTo(6);
        System.out.println(errorMsg);

        System.out.println("修改不存在的，使用put，自动创建");
        // init中name被忽略
        response = kongServiceService.put(service, init);
        assertThat(response.getCode()).isEqualTo(200);
        parse = JsonUtils.parse(response.getResponse(), Service.class);
        assertThat(parse.getName()).isEqualTo(service);
        assertThat(parse.getHost()).isEqualTo(host);
        System.out.println(parse);

        System.out.println("查询所有");
        response = kongServiceService.list();
        assertThat(response.getCode()).isEqualTo(200);
        Services list = JsonUtils.parse(response.getResponse(), Services.class);
        assertThat(list.getData().size()).isEqualTo(2);
        System.out.println(list);

        System.out.println("查询单个");
        response = kongServiceService.get(service);
        assertThat(response.getCode()).isEqualTo(200);
        parse = JsonUtils.parse(response.getResponse(), Service.class);
        assertThat(parse.getName()).isEqualTo(service);
        System.out.println(parse);
    }

} 
