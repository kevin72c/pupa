package com.cmy.main;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.cmy.server.dao.student.StudentMapper;
import com.cmy.server.model.TStudent;

public class SpringTest {

    public static void main(String[] args) throws Exception {
        // StudentService ss = context.getBean(StudentService.class);
        // List<TStudent> list = ss.queryStudentList(null);
        // System.out.println(list);
        StudentMapper sm = context.getBean(StudentMapper.class);
        TStudent param = new TStudent();
        param.setGender(0);
        List<TStudent> selectList = sm.selectList(param);
        param.setGender(1);
        selectList = sm.selectList(param);
        selectList = sm.selectList(param);
        selectList = sm.selectList(param);
        selectList = sm.selectList(param);
        System.out.println(JSON.toJSON(selectList));
    }

    static ClassPathXmlApplicationContext context;
    static {
        context = new ClassPathXmlApplicationContext("spring/applicationContext*.xml");
        context.start();
        System.out.println("init successfully");
    }
}
