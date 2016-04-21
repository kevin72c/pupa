package com.cmy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmy.pojo.ResultDto;
import com.cmy.pojo.UserModel;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Api(basePath = "/demo 测试", value = "demo Test", description = "demo 控制器")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/add",  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "添加用户", httpMethod = "POST",
        notes = "demo 笔记说明")
    @ResponseBody
    public String add(
//            @ApiParam(required = true, name = "postData", value = "demo arguments")
            @RequestParam(value = "postData") String postData,
            HttpServletRequest request) {
        System.out.println(0);
        return "demoTest";
    }
    
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "list演示", httpMethod = "POST",
    notes = "demo add 笔记说明")
    public String list(
            @ApiParam(required = true, name = "postData", value = "demo arguments")
            @RequestParam(value = "postData") String postData,
            HttpServletRequest request) {
        System.out.println(0);
        return "demoTest";
    }

    @ApiOperation(value = "test演示", httpMethod = "POST",
            notes = "demo add 笔记说明")
    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultDto<UserModel> test(
            @ApiParam(required = true, name = "postData", value = "demo arguments")
            @RequestParam(value = "postData") String postData,
            HttpServletRequest request) {
        return null;
    }
    
    @ApiOperation(value = "test2演示", httpMethod = "POST",
            notes = "demo add 笔记说明")
    @RequestMapping(value = "/test2", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultDto<UserModel> test2(
            @ApiParam(required = true, name = "postData", value = "demo arguments")
            @RequestParam(value = "postData") String postData,
            UserModel u,
            HttpServletRequest request) {
        System.out.println(postData);
        return new ResultDto<UserModel>(11,"ss",null);
    }
    
    
}
