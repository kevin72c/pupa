package com.cmy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmy.pojo.ResultPojo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

//@Api(basePath = "/demo", value = "ResultPojo", description = "demo信息")
@Api(basePath = "/demo 测试", value = "demo Test", description = "demo 控制器")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "添加用户", httpMethod = "POST",
        response = ResultPojo.class,
        notes = "demo 笔记说明")
    public String demo(
            @ApiParam(required = true, name = "postData", value = "demo arguments")
            @RequestParam(value = "postData") String postData,
            HttpServletRequest request) {
        System.out.println(0);
        return "demoTest";
    }
    
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "list演示", httpMethod = "POST",
    response = ResultPojo.class,
    notes = "demo add 笔记说明")
    public String demo2(
            @ApiParam(required = true, name = "postData", value = "demo arguments")
            @RequestParam(value = "postData") String postData,
            HttpServletRequest request) {
        System.out.println(0);
        return "demoTest";
    }
}
