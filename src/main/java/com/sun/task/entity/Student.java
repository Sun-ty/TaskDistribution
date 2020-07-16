package com.sun.task.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Student implements Serializable {
    private Integer SNo;
    private String Sname;

    public Student() {
    }

    public Student(Integer SNo, String sname) {
        this.SNo = SNo;
        Sname = sname;
    }

    public Integer getSNo() {
        return SNo;
    }

    public void setSNo(Integer SNo) {
        this.SNo = SNo;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    @Override
    public String toString() {
        return "student{" +
                "SNo=" + SNo +
                ", Sname='" + Sname + '\'' +
                '}';
    }
}

