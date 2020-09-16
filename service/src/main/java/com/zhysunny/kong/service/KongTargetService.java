package com.zhysunny.kong.service;

import com.zhysunny.kong.entity.Target;
import com.zhysunny.rest.client.HttpClientUtils;
import com.zhysunny.rest.client.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zhysunny
 * @date 2020/8/26 23:08
 */
@Slf4j
@Service
public class KongTargetService {

    @Value("kong.admin.baseurl")
    private String adminUrl;

    /**
     * @param upstream name or id
     * @param body
     * @return
     * @throws Exception
     */
    public RestResponse add(String upstream, Target body) throws Exception {
        String url = adminUrl + "/upstreams/" + upstream + "/targets";
        try {
            return HttpClientUtils.post(url, body);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param upstream name or id
     * @param target   host:port or id
     */
    public void delete(String upstream, String target) throws Exception {
        String url = adminUrl + "/upstreams/" + upstream + "/targets/" + target;
        try {
            HttpClientUtils.delete(url);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param upstream name or id
     * @return
     * @throws Exception
     */
    public RestResponse list(String upstream) throws Exception {
        String url = adminUrl + "/upstreams/" + upstream + "/targets";
        try {
            return HttpClientUtils.get(url);
        } catch (Exception e) {
            throw e;
        }
    }

}
