package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentDTO;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /*学员信息分页条件查询*/
    @Override
    public PageResult<Student> select(StudentQueryParam studentQueryParam) {
        //1、设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        //2、调用mapper方法获取list
        List<Student> list = studentMapper.select(studentQueryParam);
        //3、解析结果并封装
        Page<Student> pageList = (Page<Student>) list;
        return new PageResult<Student>(pageList.getTotal(),pageList.getResult());
    }

    /*添加学员*/
    @Override
    public void insert(Student student) {
        //1、补全基本信息
        student.setUpdateTime(LocalDateTime.now());
        student.setCreateTime(LocalDateTime.now());
        //2、调用mapper接口将该对象传递过去
        studentMapper.insert(student);
    }

    /*根据id批量删除员工*/
    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    /*根据id查询学员*/
    @Override
    public Student seletById(Integer id) {
        return studentMapper.selectById(id);
    }

    /*根据id修改员工信息*/
    @Override
    public void update(Student student) {
        //1、调用mappper接口修改学员基本信息
        studentMapper.update(student);

    }

    /*学员违纪信息修改*/
    @Override
    public void violationById(Integer id, Integer score) {
        //1、查询学员是否存在
        Student student = studentMapper.selectById(id);
        if (student == null){
            throw new RuntimeException("该学员不存在！");
        }

        //2、查询分数score是否合法
        if (score <= 0){
            throw new RuntimeException("违纪扣分必须大于0！");
        }

        //3、更新违纪分数
        student.setViolationScore((short) (student.getViolationScore() + score));

        //4、更新违纪次数
        student.setViolationCount((short) (student.getViolationCount() + 1));

        //5、调用mapper接口将数据传递
        studentMapper.violationById(student);
    }
}
