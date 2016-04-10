package com.cmy.server.dao.student;

import java.util.List;

import com.cmy.server.model.TStudent;

public interface StudentMapper {

    public List<TStudent> selectList(TStudent param);
    
}
