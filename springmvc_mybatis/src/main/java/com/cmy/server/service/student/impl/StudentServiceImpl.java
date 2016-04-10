package com.cmy.server.service.student.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmy.server.dao.student.StudentMapper;
import com.cmy.server.model.TStudent;
import com.cmy.server.service.student.StudentService;
import com.github.pagehelper.PageHelper;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    
    @Override
    public List<TStudent> queryStudentList(TStudent param) throws Exception {
        // 如分页查询时,在Check注解加入校验公式[pageSize,pageNum]@d
        PageHelper.startPage(1, 10);
//        return sc.selectList(jsonParam);
        return studentMapper.selectList(param);
    }

}
