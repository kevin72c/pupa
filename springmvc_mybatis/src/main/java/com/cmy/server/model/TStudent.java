package com.cmy.server.model;

import java.io.Serializable;

public class TStudent implements Serializable {

    private static final long serialVersionUID = -2854729731093949266L;
    
    private Integer studentID;
    private String studentName;
    private Integer gender;
    private Integer age;
    private String grade;
    public Integer getStudentID() {
        return studentID;
    }
    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
}
