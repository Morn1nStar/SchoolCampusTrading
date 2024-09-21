package com.campususedtrading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* Spring Boot 的核心注解之一，包含了 @Configuration、@EnableAutoConfiguration、@ComponentScan 这三个注解的功能
* 是启动 Spring Boot 应用的“一站式”注解，它集成了配置、自动配置和组件扫描等功能。
*/
@SpringBootApplication
public class CampusUsedTradingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusUsedTradingApplication.class, args);
    }

}
