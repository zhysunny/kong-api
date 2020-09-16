package com.zhysunny.kong;

import com.zhysunny.kong.entity.Route;
import com.zhysunny.kong.entity.Service;
import com.zhysunny.kong.service.KongRouteService;
import com.zhysunny.kong.service.KongServiceService;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import java.util.Arrays;

/**
 * @author zhysunny
 * @date 2020/9/1 23:20
 */
public class MyTest {
    private KongServiceService kongServiceService = new KongServiceService();
    private KongRouteService kongRouteService = new KongRouteService();

    @Test
    public void test() throws Exception {
        Whitebox.setInternalState(kongServiceService, "adminUrl", "http://192.168.1.12:8001");
        Whitebox.setInternalState(kongRouteService, "adminUrl", "http://192.168.1.12:8001");

        Service service = Service.INIT;
        service.setName("my_service");
        kongServiceService.add(service);

        Route route = Route.INIT;
        route.setName("my_route");
        route.setPaths(Arrays.asList("/"));
        kongRouteService.add("my_service", route);
    }
}
