package com.sun.task.service;


import com.sun.task.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findStu();

    Student findStu(Integer SNo);

    int upStudent(Student stu);

    int SaveStu(Student stu);

    int DelStu(Integer SNo);


}
