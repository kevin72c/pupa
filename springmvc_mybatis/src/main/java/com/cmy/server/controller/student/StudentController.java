package com.cmy.server.controller.student;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmy.server.model.ResultDto;
import com.cmy.server.model.TStudent;
import com.cmy.server.service.student.StudentService;

@Controller
@Scope("prototype")
@RequestMapping("/demo")
public class StudentController {
    
    @Resource
    StudentService dm;
    
    @RequestMapping(value="/list" )
    public @ResponseBody ResultDto list() {
        List<TStudent> list = null;
        try {
            list = dm.queryStudentList(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultDto(100, "demo test", list);
    }

    
}
