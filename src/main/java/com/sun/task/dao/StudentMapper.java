package com.sun.task.dao;


import com.sun.task.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 使用Mapper注解版 不需要  *mapper.xml和 mybatis-config.xml文件
 * <p>
 * 这里不使用使用注解版
 */
//@Repository   只需要存在一个
@Mapper   //与主类配置的@MapperScan("com.sun.cache.dao")二选一
public interface StudentMapper {

    /**
     * 查找所有信息
     *
     * @return
     * @Select("SELECT * FROM student")
     * @Results({ //一一匹配属性跟数据库字段
     * @Result(column = "SNo", property = "SNo"),
     * @Result(column = "Sname", property = "Sname")
     * })
     */
    List<Student> findAlls();

    /**
     * 根据 id 查找所有信息
     *
     * @return
     * @Select("SELECT * FROM student WHERE `SNo`=#{SNo}")
     */
    Student findAll(Integer SNo);

    /**
     * 修改数据
     *
     * @Update("UPDATE `student` SET Sname=#{Sname} WHERE SNo=#{SNo}")
     */
    int upStu(Student stu);

    /**
     * 添加
     *
     * @return
     * @Options(useGeneratedKeys = true,keyProperty = "SNo") //id主键自动生成  不需要插入
     * @Insert("INSERT INTO `student` VALUES(#{Sname})")
     */
    int save(Student stu);

    /**
     * 根据 id 删除信息
     *
     * @return
     * @Delete("DELETE FROM student WHERE `SNo`=#{SNo}")
     */
    int delStu(Integer SNo);
}
