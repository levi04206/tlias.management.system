package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentDTO;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    PageResult<Student> select(StudentQueryParam studentQueryParam);

    void insert(Student student);

    void deleteByIds(List<Integer> ids);

    Student seletById(Integer id);

    void update(Student student);

    void violationById(Integer id, Integer score);
}
