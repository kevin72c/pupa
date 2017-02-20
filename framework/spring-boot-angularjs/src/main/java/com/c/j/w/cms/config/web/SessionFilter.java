package com.c.j.w.cms.config.web;

import com.c.j.w.cms.core.util.SessionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kevin on 2016/10/11.
 */
@Component
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        if (!requestURI.matches(".*?(/|login|js|html|jpg|css|ico|system/init)")) {
            if (SessionUtil.getSessionUser() == null) {
                HttpServletResponse res = (HttpServletResponse )response;
                res.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }

        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
