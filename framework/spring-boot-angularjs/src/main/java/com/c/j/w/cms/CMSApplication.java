package com.c.j.w.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by kevin on 16/10/2.
 */
@SpringBootApplication
@ComponentScan("com.c.j.w.cms")
@EnableAutoConfiguration
@EnableTransactionManagement
public class CMSApplication {

    /**
     * 登陆页面: http://localhost:8080/login.html
     * 首页：http://localhost:8080/
     * 接口文档地址: http://localhost:8080/swagger-ui.html#/
     * 接口测试地址:
     * Sql统计地址: http://localhost:8080/druid/index.html
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CMSApplication.class, args);
        context.start();
        System.out.println("started up");
    }
}
