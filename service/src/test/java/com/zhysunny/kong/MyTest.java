package com.zhysunny.kong;

import com.zhysunny.kong.entity.Route;
import com.zhysunny.kong.service.KongRouteService;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import java.util.Arrays;

/**
 * @author zhysunny
 * @date 2020/9/1 23:20
 */
public class MyTest {
    private KongRouteService kongRouteService = new KongRouteService();

    @Test
    public void test() throws Exception {
        Whitebox.setInternalState(kongRouteService, "adminUrl", "http://192.168.1.12:8001");
        Route init = Route.INIT;
        init.setName("my_route");
        init.setPaths(Arrays.asList("/"));
        kongRouteService.add("my_service", init);
    }
}
