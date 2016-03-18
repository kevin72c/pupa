package com.cmy.test;

import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SPX {

    
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml", "applicationContext-dubbo.xml", "applicationContext-redis.xml" });
        
    }
}
