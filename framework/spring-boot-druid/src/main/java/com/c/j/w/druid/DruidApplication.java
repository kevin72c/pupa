package com.c.j.w.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author chenjw
 * @Date 2017年01月19日
 */
@SpringBootApplication
@ComponentScan("com.cjw.druid")
//@EnableAutoConfiguration
@ServletComponentScan("com.cjw.druid.config")
//@Configuration
public class DruidApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DruidApplication.class, args);
//        DataSourceAutoConfiguration bean = context.getBean(DataSourceAutoConfiguration.class);
        DruidDataSource bean = context.getBean(DruidDataSource.class);
        System.out.println(bean);
    }

    @Bean
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
}
