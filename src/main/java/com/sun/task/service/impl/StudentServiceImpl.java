package com.sun.task.service.impl;


import com.sun.task.dao.StudentMapper;
import com.sun.task.entity.Student;
import com.sun.task.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//@CacheConfig(cacheNames = "stu")   抽取缓存公共配置   例如这个 然后下面的配置都不用指定cacheNames/value
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Cacheable(cacheNames = {"stu"}/*,key = "7"*/)
    public List<Student> findStu() {
        System.out.println("查询所有员工！");//加了缓存，控制台只打印了一遍
        return studentMapper.findAlls();
    }

    /**
     * 将方法的运行结果 进行缓存；以后要调用这个方法的数据，直接从缓存中获取，不用调用方法；
     *
     * @param SNo
     * @return
     * @Cacheable 的属性：
     * cacheNames/value:指定缓存组件的名字，将方法的返回结果放在哪个缓存中，以数组的方式，可以指定多个缓存
     * key:缓存数据使用的key；可以用它来指定。默认使用方法参数的值  或编写SpEL: #a0 #p0 #root.args[0]
     * keyGenerator:key的生成器；可以指定key的生成器的组件id
     * key/keyGenerator二选一使用
     * cacheManager:指定缓存管理器；或者cacheResolver指定获取解析器
     * condition：指定符合条件的情况才缓存
     * unless:与condition相反，当条件为true，方法返回值就不会被缓存，可以获取结果进行判断
     * sync：是否同步
     * 先调用key(没有指定key默认是参数名，参数不支持复杂数据类型) 再通过key获取对应的值
     */
    @Cacheable(cacheNames = {"stu"})
    public Student findStu(Integer SNo) {
        System.out.println("查询：" + SNo + "号员工！");//加了缓存，控制台只打印了一遍
        return studentMapper.findAll(SNo);
    }

    /**
     * 更新操作 ，更新后也同时修改缓存的数据  缓存组件名必须一样
     * 先执行方法在调用key ( #result.id 获取到对应key的值  修改它的缓存)
     *
     * @param stu
     * @return
     */
    @CachePut(value = "stu", key = "#result.id")
    public int upStudent(Student stu) {
        System.out.println("修改：" + stu.getSNo() + "号员工！");
        return studentMapper.upStu(stu);
    }

    /**
     * @param SNo
     * @return
     * @CacheEvict ：清除缓存  删除一个用户后，该用户的缓存也一同删除  注：缓存组件名得在同一个缓存中
     * key:指定要清除的数据 ，默认清除那个参数的缓存
     * allEntries=true  ：指定清除这个缓存中所有的数据
     * beforeInvocation=false：缓存的清除是否在方法之前执行
     */
    @CacheEvict(value = "stu"/*,key = "#SNo"*/)
    public int DelStu(Integer SNo) {
        System.out.println("删除：" + SNo + "号员工！！");
        //  return studentMapper.delStu(SNo);
        return 1;
    }

    /**
     * @Caching包含三个注解：
     * @Cacheable
     * @CachePut
     * @CacheEvict
     */

    @Override
    public int SaveStu(Student stu) {
        return studentMapper.save(stu);
    }


}
