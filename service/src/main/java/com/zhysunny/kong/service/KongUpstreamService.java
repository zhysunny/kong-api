package com.zhysunny.kong.service;

import com.zhysunny.kong.entity.Service;
import com.zhysunny.kong.entity.Upstream;
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
public class KongUpstreamService {

    @Value("kong.admin.baseurl")
    private String adminUrl;

    public RestResponse add(Upstream body) throws Exception {
        String url = adminUrl + "/upstreams";
        try {
            return HttpClientUtils.post(url, body);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param upstream name or id
     */
    public void delete(String upstream) throws Exception {
        String url = adminUrl + "/upstreams/" + upstream;
        try {
            HttpClientUtils.delete(url);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param upstream name or id
     * @param body
     * @throws Exception
     */
    public RestResponse update(String upstream, Upstream body) throws Exception {
        String url = adminUrl + "/upstreams/" + upstream;
        try {
            return HttpClientUtils.patch(url, body);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param upstream name or id
     * @param body
     * @throws Exception
     */
    public RestResponse put(String upstream, Upstream body) throws Exception {
        String url = adminUrl + "/upstreams/" + upstream;
        try {
            return HttpClientUtils.put(url, body);
        } catch (Exception e) {
            throw e;
        }
    }

    public RestResponse list() throws Exception {
        String url = adminUrl + "/upstreams";
        try {
            return HttpClientUtils.get(url);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param upstream name or id
     * @return
     * @throws Exception
     */
    public RestResponse get(String upstream) throws Exception {
        String url = adminUrl + "/upstreams/" + upstream;
        try {
            return HttpClientUtils.get(url);
        } catch (Exception e) {
            throw e;
        }
    }

}
