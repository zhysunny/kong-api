package com.zhysunny.kong.service;

import com.zhysunny.common.json.JsonUtils;
import com.zhysunny.kong.entity.*;
import com.zhysunny.rest.client.RestResponse;
import org.junit.*;
import org.powermock.reflect.Whitebox;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.*;

/**
 * KongTargetService Test
 */
public class KongTargetServiceTest {

    private KongUpstreamService kongUpstreamService;
    private KongTargetService kongTargetService;
    private String upstream = "test_upstream";
    private String target = "test_target:8080";
    private Upstream initUpstream = Upstream.INIT;
    private Target initTarget = Target.INIT;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test KongTargetService Class Start...");
    }

    @Before
    public void before() throws Exception {
        // 添加一个service
        kongUpstreamService = new KongUpstreamService();
        Whitebox.setInternalState(kongUpstreamService, "adminUrl", "http://192.168.1.12:8001");
        initUpstream.setName(upstream);
        kongUpstreamService.delete(upstream);
        kongUpstreamService.add(initUpstream);

        kongTargetService = new KongTargetService();
        Whitebox.setInternalState(kongTargetService, "adminUrl", "http://192.168.1.12:8001");
        initTarget.setTarget(target);
        // 初始化，先清空
        kongTargetService.delete(upstream, target);
    }

    @After
    public void after() throws Exception {
        kongTargetService.delete(upstream, target);
        kongUpstreamService.delete(upstream);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test KongTargetService Class End...");
    }

    @Test
    public void testMain() throws Exception {
        System.out.println("添加");
        RestResponse response = kongTargetService.add(upstream, initTarget);
        assertThat(response.getCode()).isEqualTo(201);
        Target parse = JsonUtils.parse(response.getResponse(), Target.class);
        assertThat(parse.getTarget()).isEqualTo(target);
        System.out.println(parse);

        System.out.println("再添加，重新覆盖");
        response = kongTargetService.add(upstream, initTarget);
        assertThat(response.getCode()).isEqualTo(201);
        parse = JsonUtils.parse(response.getResponse(), Target.class);
        assertThat(parse.getTarget()).isEqualTo(target);
        System.out.println(parse);

        System.out.println("查询所有");
        response = kongTargetService.list(upstream);
        assertThat(response.getCode()).isEqualTo(200);
        Targets list = JsonUtils.parse(response.getResponse(), Targets.class);
        System.out.println(list);

    }

} 
