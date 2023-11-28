package com.aguo.wxpush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableRetry
@SpringBootApplication
public class WxPushApplication /*extends SpringBootServletInitializer */ {

    public static void main(String[] args) {
        SpringApplication.run(WxPushApplication.class, args);
    }

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 配置Springboot的应用环境
        SpringApplicationBuilder sources = builder.sources(WxPushApplication.class);

        return sources;
    }*/
}
