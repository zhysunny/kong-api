package com.zhysunny.kong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author zhysunny
 * @date 2020/8/26 22:51
 */
@SpringBootApplication
public class KongApiMain {
    private static ApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(KongApiMain.class, args);
    }
}
