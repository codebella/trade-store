package com.sample.services.trade.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableZuulProxy
public class TradeProxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeProxyApplication.class, args);
    }
}
