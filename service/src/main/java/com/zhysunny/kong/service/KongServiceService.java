package com.zhysunny.kong.service;

import com.zhysunny.kong.entity.Service;
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
public class KongServiceService {

    @Value("kong.admin.baseurl")
    private String adminUrl;

    public RestResponse add(Service body) throws Exception {
        String url = adminUrl + "/services";
        try {
            return HttpClientUtils.post(url, body);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param service name or id
     */
    public void delete(String service) throws Exception {
        String url = adminUrl + "/services/" + service;
        try {
            HttpClientUtils.delete(url);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param service name or id
     * @param body
     * @throws Exception
     */
    public RestResponse update(String service, Service body) throws Exception {
        String url = adminUrl + "/services/" + service;
        try {
            return HttpClientUtils.patch(url, body);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param service name or id
     * @param body
     * @throws Exception
     */
    public RestResponse put(String service, Service body) throws Exception {
        String url = adminUrl + "/services/" + service;
        try {
            return HttpClientUtils.put(url, body);
        } catch (Exception e) {
            throw e;
        }
    }

    public RestResponse list() throws Exception {
        String url = adminUrl + "/services";
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
    public RestResponse get(String service) throws Exception {
        String url = adminUrl + "/services/" + service;
        try {
            return HttpClientUtils.get(url);
        } catch (Exception e) {
            throw e;
        }
    }

}
