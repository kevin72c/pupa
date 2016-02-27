package com.cmy.basis.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.cmy.util.IdentityGenerator;

public class Conn {

    public static Connection conn;
//  static String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
//  static String userName = "scott";
//  static String password = "tiger";
    
    static String url = "jdbc:mysql://rdsd2wm8c03gi328edy4.mysql.rds.aliyuncs.com:3306/sports_release?useUnicode/=true&characterEncoding/=utf8";
//    static String url = "jdbc:mysql://rdsd2wm8c03gi328edy4.mysql.rds.aliyuncs.com:3306/sports_test?useUnicode=true&characterEncoding=utf8";
    static String userName = "sport_user";
    static String password = "1qaz2wsx";
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("oracle.jdbc.OracleDriver");
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
        ResultSet rs = stat.executeQuery("select userID from sport_user where userType = 0");
        JSONObject json = new JSONObject();
        while(rs.next()) {
            String userID = rs.getString("userID");
            json.put(userID, IdentityGenerator.toSerialCode(Integer.parseInt(userID)));
        }
        System.out.println(json);
        int i = 0;
        for (Map.Entry<String, Object> m : json.entrySet()) {
            String s = "update sport_user set invitationCode = '" +m.getValue()
                    + "' where userid = " +m.getKey() + ";";
            stat.addBatch(s);
            i++;
            if (i == 100) {
                System.out.println(s);
                stat.executeBatch();
                i=0;
            }
        }
        stat.executeBatch();
        System.out.println("last execute");
        
    }

    
    static void insert1() throws Exception {
//        String sql = " INSERT INTO sport_user  (userName,tel,pwd,userAddress,userType,isDel,createTime)  SELECT  '西湖体育馆','15111111111','e10adc3949ba59abbe56e057f20f883e','杭州市西湖区文一路100号 ',2,0,0  FROM DUAL WHERE NOT EXISTS( SELECT 1 FROM sport_user WHERE userName ='西湖体育馆')";
//        String sql = "INSERT INTO sport_venue (  bus,  tel,  img,  remarks,  endHour,  content,  lontitude,  venueAddress,  linkMan,  images,  startHour,  latitude,  venueName,  minPrice,  isDel,  STATUS,  createTime) SELECT   '10路,15路,188路,256到西湖广场下车',  '15111111111',  'imgs/venue/61277243-fb8d-41ee-ac3c-63ac0001c174.png',  '西湖体育馆是国家级体育馆',  '22',  NULL,  '13366',  '杭州市西湖区文一路100号',  '张三',  'imgs/venue/55601df5-c4bb-47bf-97de-21622d1115f6.png,imgs/venue/9a2b3a91-9e8a-48cc-b6da-b385f603f982.jpg',  '18',  '12345',  '西湖体育馆',  NULL,  0,  0,  1445476374 FROM  DUAL ";
        String sql = "INSERT INTO sport_venue (  bus,  tel,  img,  remarks,  endHour,  content,  lontitude,  venueAddress,  linkMan,  images,  startHour,  latitude,  venueName,  minPrice,  isDel,  STATUS,  createTime) values( '10路,15路,188路,256到西湖广场下车',  '15111111111',  'imgs/venue/61277243-fb8d-41ee-ac3c-63ac0001c174.png',  '西湖体育馆是国家级体育馆',  '22',  NULL,  '13366',  '杭州市西湖区文一路100号',  '张三',  'imgs/venue/55601df5-c4bb-47bf-97de-21622d1115f6.png,imgs/venue/9a2b3a91-9e8a-48cc-b6da-b385f603f982.jpg',  '18',  '12345',  '西湖体育馆',  NULL,  0,  0,  1445476374 ) ";
        Statement stat = conn.createStatement();
        boolean execute = stat.execute(sql);
        System.out.println(execute);
        conn.close();
    }
    static void insert2() throws Exception {
        Long cnt = 0L;
//        String sql = " INSERT INTO sport_user  (userName,tel,pwd,userAddress,userType,isDel,createTime)  SELECT  '西湖体育馆','15111111111','e10adc3949ba59abbe56e057f20f883e','杭州市西湖区文一路100号 ',2,0,0  FROM DUAL "
//                + "WHERE NOT EXISTS( SELECT 1 FROM sport_user WHERE userName ='西湖体育馆')";
        String sql = " INSERT INTO sport_user  (userName,tel,pwd,userAddress,userType,isDel,createTime)  SELECT  '西湖体育馆','15111111111','e10adc3949ba59abbe56e057f20f883e','杭州市西湖区文一路100号 ',2,0,0  FROM DUAL "
                + "WHERE '西湖体育馆' not in( SELECT userName FROM sport_user )";
//        String sql = "INSERT INTO sport_user (  userName,  tel,  pwd,  userAddress,  userType,  isDel,  createTime) VALUE(  '西湖体育馆',  '15111111111',  'e10adc3949ba59abbe56e057f20f883e',  '杭州市西湖区文一路100号 ',  2,  0,  0 )";
//        String sql = "INSERT INTO sport_venue (  bus,  tel,  img,  remarks,  endHour,  content,  lontitude,  venueAddress,  linkMan,  images,  startHour,  latitude,  venueName,  minPrice,  isDel,  STATUS,  createTime) values( '10路,15路,188路,256到西湖广场下车',  '15111111111',  'imgs/venue/61277243-fb8d-41ee-ac3c-63ac0001c174.png',  '西湖体育馆是国家级体育馆',  '22',  NULL,  '13366',  '杭州市西湖区文一路100号',  '张三',  'imgs/venue/55601df5-c4bb-47bf-97de-21622d1115f6.png,imgs/venue/9a2b3a91-9e8a-48cc-b6da-b385f603f982.jpg',  '18',  '12345',  '西湖体育馆',  NULL,  0,  0,  1445476374 ) ";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            cnt = rs.getLong(1);
        }
        System.out.println(cnt);
        rs.close();
        ps.close();
        conn.close();
    }
}
