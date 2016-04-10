package com.cmy.basis.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.fastjson.JSONObject;
import com.cmy.util.IdentityGenerator;

public class Conn {

    public static Connection conn;
//  static String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
//  static String userName = "scott";
//  static String password = "tiger";
    
    static String url = "jdbc:mysql://172.16.86.135:3306/db?useUnicode/=true&characterEncoding/=utf8";
    static String userName = "root";
    static String password = "0503";
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(
                "select * from t_student");
        JSONObject json = new JSONObject();
        while(rs.next()) {
            String studentName = rs.getString("studentName");
            String studentID = rs.getString("studentID");
            json.put(studentName, IdentityGenerator.toSerialCode(Integer.parseInt(studentID)));
        }
        System.out.println(json);
    }
}
