package com.cmy.framework;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {

    
    static ClassPathXmlApplicationContext context = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
    
    public static void main(String[] args) throws Exception {
        
    }
}
