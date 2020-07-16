package com.sun.task.controller;

import com.sun.task.entity.Student;
import com.sun.task.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StudentController {

    @Resource
    private StudentServiceImpl serviceImpl;

    @RequestMapping("/getStu")
    public List<Student> findStu() {

        return serviceImpl.findStu();
    }

    @RequestMapping("/getStu/{id}")
    public Student findStu2(@PathVariable("id") Integer id) {

        return serviceImpl.findStu(id);
    }

    @RequestMapping("/upStu/{name}/{id}")
    public String upstu(@PathVariable("name") String name, @PathVariable("id") Integer SNo) {
        Student student = new Student(SNo, name);
        serviceImpl.upStudent(student);
        return "修改成功";
    }

    @RequestMapping("/Delstu/{id}")
    public String DelStu(@PathVariable("id") Integer id) {
        serviceImpl.DelStu(id);
        return "删除成功！";
    }

}
