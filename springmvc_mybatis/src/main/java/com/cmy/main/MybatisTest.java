package com.cmy.main;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {

    static SqlSession sqlSession;
    
    public static void main(String[] args) {

    }
    
    static {
        // 通过配置文件获取数据库连接信息
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("sqlMapConfig.xml");
            // 通过配置信息构建一个SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            // 通过sqlSessionFactory打开一个数据库会话
            sqlSession = sqlSessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
