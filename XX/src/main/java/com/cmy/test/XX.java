package com.cmy.test;

import java.util.ArrayList;
import java.util.List;

public class XX {

    public static void main(String[] args) throws Exception {
        Integer i1 = new Integer(0);
        Integer i2 = new Integer(0);
        List<Integer> list = new ArrayList<>();
        list.add(i1);
        System.out.println(list.contains(i2));
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
