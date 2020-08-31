package com.zhysunny.kong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhysunny
 * @date 2020/8/31 23:16
 */
@RestController
public class HelloController {

    @Value("server.port")
    private String port;

    public String hello() {
        return "Hello Kong! This port: " + port;
    }
}
