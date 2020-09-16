package com.zhysunny.kong.service;

import com.zhysunny.common.json.JsonUtils;
import com.zhysunny.kong.entity.*;
import com.zhysunny.rest.client.RestResponse;
import org.junit.*;
import org.powermock.reflect.Whitebox;
import static org.assertj.core.api.Assertions.*;

/**
 * KongUpstreamService Test
 */
public class KongUpstreamServiceTest {

    private KongUpstreamService kongUpstreamService;
    private String upstream = "test_upstream";
    private String upstream2 = "test_upstream_2";
    private Upstream init = Upstream.INIT;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test KongUpstreamService Class Start...");
    }

    @Before
    public void before() throws Exception {
        kongUpstreamService = new KongUpstreamService();
        Whitebox.setInternalState(kongUpstreamService, "adminUrl", "http://192.168.1.12:8001");
        init.setName(upstream);
        // 初始化，先清空
        kongUpstreamService.delete(upstream);
        kongUpstreamService.delete(upstream2);
    }

    @Test
    public void test() throws Exception {
        RestResponse response = kongUpstreamService.list();
        System.out.println(response);
        assertThat(response.getCode()).isEqualTo(200);
        Upstreams list = JsonUtils.parse(response.getResponse(), Upstreams.class);
        System.out.println(list);
    }

    @After
    public void after() throws Exception {
        // 删除
        kongUpstreamService.delete(upstream);
        kongUpstreamService.delete(upstream2);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test KongUpstreamService Class End...");
    }

    @Test
    public void testMain() throws Exception {
        System.out.println("添加");
        RestResponse response = kongUpstreamService.add(init);
        assertThat(response.getCode()).isEqualTo(201);
        Upstream parse = JsonUtils.parse(response.getResponse(), Upstream.class);
        assertThat(parse.getName()).isEqualTo(upstream);
        System.out.println(parse);

        System.out.println("再添加，报错：已存在");
        response = kongUpstreamService.add(init);
        assertThat(response.getCode()).isEqualTo(409);
        ErrorMsg errorMsg = JsonUtils.parse(response.getResponse(), ErrorMsg.class);
        assertThat(errorMsg.getName()).isEqualTo("unique constraint violation");
        assertThat(errorMsg.getCode()).isEqualTo(5);
        System.out.println(errorMsg);

        System.out.println("修改");
        init.setName(upstream2);
        response = kongUpstreamService.update(upstream, init);
        assertThat(response.getCode()).isEqualTo(200);
        parse = JsonUtils.parse(response.getResponse(), Upstream.class);
        assertThat(parse.getName()).isEqualTo(upstream2);
        System.out.println(parse);

        System.out.println("再修改，报错：不存在");
        response = kongUpstreamService.update(upstream, init);
        assertThat(response.getCode()).isEqualTo(404);
        errorMsg = JsonUtils.parse(response.getResponse(), ErrorMsg.class);
        assertThat(errorMsg.getName()).isEqualTo("not found");
        assertThat(errorMsg.getCode()).isEqualTo(6);
        System.out.println(errorMsg);

        System.out.println("修改不存在的，使用put，自动创建");
        // init中name被忽略
        response = kongUpstreamService.put(upstream, init);
        assertThat(response.getCode()).isEqualTo(200);
        parse = JsonUtils.parse(response.getResponse(), Upstream.class);
        assertThat(parse.getName()).isEqualTo(upstream);
        System.out.println(parse);

        System.out.println("查询所有");
        response = kongUpstreamService.list();
        assertThat(response.getCode()).isEqualTo(200);
        Upstreams list = JsonUtils.parse(response.getResponse(), Upstreams.class);
        System.out.println(list);

        System.out.println("查询单个");
        response = kongUpstreamService.get(upstream);
        assertThat(response.getCode()).isEqualTo(200);
        parse = JsonUtils.parse(response.getResponse(), Upstream.class);
        assertThat(parse.getName()).isEqualTo(upstream);
        System.out.println(parse);
    }

} 
