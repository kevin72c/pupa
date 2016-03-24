package com.cmy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmy.pojo.ResultPojo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

public class DemoController {

    @ResponseBody
    @RequestMapping(value = "demo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "添加用户", httpMethod = "POST", response = ResultPojo.class, notes = "demo notes")
    public String demo(
            @ApiParam(required = true, name = "postData", value = "demo arguments")
            @RequestParam(value = "postData") String postData,
            HttpServletRequest request) {
        System.out.println(0);
        return "demoTest";
    }
}
