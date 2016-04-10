package com.cmy.core.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SysContext implements ApplicationContextAware {
    
    private static ApplicationContext context;
    
    @Override
    public void setApplicationContext(ApplicationContext paramApplicationContext)
            throws BeansException {
        context = paramApplicationContext;
    }
    
    public static <T> T getBean(String paramString, Class<T> paramClass) {
        return context.getBean(paramString, paramClass); 
    }
    
    public static <T> T getBean(Class<T> paramClass) {
        return context.getBean(paramClass); 
    }
    
}
