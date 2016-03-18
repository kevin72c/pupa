package com.cmy.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class XX {

    public static void main(String[] args) {
//        JSONObject j = new JSONObject();
//        j.put("id", 11);
//        j.put("name", "x");
//        A a = JSON.toJavaObject(j, A.class);
//        System.out.println(a);
        System.out.println(Integer.MAX_VALUE -1);
    }
}
class A {
    int id;
    String name;
    int age;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
}
