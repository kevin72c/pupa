package com.github.cms.config.web;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author chenjw
 * @Date 2016年05月20日
 */
@ControllerAdvice
public class ExceptionResolver {

    private static final Logger logger = Logger.getLogger(ExceptionResolver.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String jsonErrorHandler(HttpServletRequest request,
                                   HttpServletResponse response,
                                   Exception e) throws Exception {
        e.printStackTrace();
        logger.error(e);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "{message:\"internal server error\"}";
    }

}