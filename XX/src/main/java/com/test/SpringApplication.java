package com.test;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by 大胡子Kevin on 2016/5/29.
 */
@Configuration
@ComponentScan
@ImportResource()
public class SpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApplication.class);
        ConfigurableListableBeanFactory factory = context.getBeanFactory();
        System.out.println(factory);

    }

}
