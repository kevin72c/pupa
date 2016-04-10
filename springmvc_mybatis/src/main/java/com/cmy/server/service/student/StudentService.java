package com.cmy.server.service.student;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cmy.server.model.TStudent;

public interface StudentService {

    public List<TStudent> queryStudentList(TStudent param) throws Exception;

}
