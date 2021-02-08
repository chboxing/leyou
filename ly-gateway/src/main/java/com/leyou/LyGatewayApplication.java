package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableDiscoveryClient
@SpringCloudApplication
@EnableZuulProxy //与eureka配合使用时添加此注解
public class LyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyGatewayApplication.class);
    }
}
