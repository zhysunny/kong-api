package com.zhysunny.kong.service;

import com.zhysunny.kong.entity.Route;
import com.zhysunny.rest.client.HttpClientUtils;
import com.zhysunny.rest.client.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zhysunny
 * @date 2020/8/26 23:08
 */
@Slf4j
@org.springframework.stereotype.Service
public class KongRouteService {

    @Value("kong.admin.baseurl")
    private String adminUrl;

    /**
     * @param service name or id
     * @param body
     * @return
     * @throws Exception
     */
    public RestResponse add(String service, Route body) throws Exception {
        String url = adminUrl + "/services/" + service + "/routes";
        try {
            return HttpClientUtils.post(url, body);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param route name or id
     */
    public void delete(String route) throws Exception {
        String url = adminUrl + "/routes/" + route;
        try {
            HttpClientUtils.delete(url);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param route name or id
     * @param body
     * @throws Exception
     */
    public RestResponse update(String route, Route body) throws Exception {
        String url = adminUrl + "/routes/" + route;
        try {
            return HttpClientUtils.patch(url, body);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param route name or id
     * @param body
     * @throws Exception
     */
    public RestResponse put(String route, Route body) throws Exception {
        String url = adminUrl + "/routes/" + route;
        try {
            return HttpClientUtils.put(url, body);
        } catch (Exception e) {
            throw e;
        }
    }

    public RestResponse list() throws Exception {
        String url = adminUrl + "/routes";
        try {
            return HttpClientUtils.get(url);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param service name or id
     * @return
     * @throws Exception
     */
    public RestResponse list(String service) throws Exception {
        String url = adminUrl + "/services/" + service + "/routes";
        try {
            return HttpClientUtils.get(url);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param route name or id
     * @return
     * @throws Exception
     */
    public RestResponse get(String route) throws Exception {
        String url = adminUrl + "/routes/" + route;
        try {
            return HttpClientUtils.get(url);
        } catch (Exception e) {
            throw e;
        }
    }

}
