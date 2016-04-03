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
    
    static String url = "jdbc:mysql://127.0.0.1:3306/dev?useUnicode/=true&characterEncoding/=utf8";
    static String userName = "";
    static String password = "";
    
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
                "select userID from sport_user where userType = 0");
        JSONObject json = new JSONObject();
        while(rs.next()) {
            String userID = rs.getString("userID");
            json.put(userID, IdentityGenerator.toSerialCode(Integer.parseInt(userID)));
        }
        System.out.println(json);
    }
}
