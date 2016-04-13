package com.cmy.test;

import java.beans.Transient;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class XX {

    public static void main(String[] args) {
        A a = new A();
        a.id = 111;
        System.out.println(JSONObject.toJSON(a));
    }
    
    public A<XX> g() {
        
        return null;
    }
    
}
class A<E> {

    int id;
    String name;
    int age;
    private E info;
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
    public E getInfo() {
        return info;
    }
    public void setInfo(E info) {
        this.info = info;
    }
    
}
