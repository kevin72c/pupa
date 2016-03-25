package com.cmy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class XInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest paramHttpServletRequest,
            HttpServletResponse paramHttpServletResponse, Object paramObject, Exception paramException)
                    throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse,
            Object paramObject, ModelAndView paramModelAndView) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object paramObject) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(request.getRequestURL());
//        request.get
        System.out.println(paramObject);
        if (paramObject instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod)paramObject;
            System.out.println(hm.getMethod());
        } else {
            System.err.println(paramObject);
        }
        return true;
    }

}
