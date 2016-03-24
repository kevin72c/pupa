package com.cmy.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmy.pojo.ResultPojo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

//@Api(basePath = "/demo2", value = "ResultPojo", description = "学生信息")
@RestController
@RequestMapping("/student")
public class StudentController {

    /**
     * 新增加学生信息x
     * @param ResultPojoInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addResultPojo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "新增学生信息", notes = "增加学生信息", httpMethod = "POST", response = ResultPojo.class)
    public String addResultPojo(
            @ApiParam(required = true, name = "ResultPojoInfo", value = "学生实体类") @RequestBody ResultPojo ResultPojoInfo) {

        System.out.println(0);

        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/ResultPojo/{id}", method = RequestMethod.GET,
        produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取学生信息", httpMethod = "GET",
        notes = "根据学生id获取学生的信息")
    public String getResultPojoById(
            @ApiParam(required = true, name = "id",
            value = "ResultPojo id Integer") @PathVariable(value = "id") int id) {

        return "success";
    }

}
