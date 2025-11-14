package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentDTO;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    /*查询学员信息（根据查询条件支持分页查询）*/
    List<Student> select(StudentQueryParam studentQueryParam);

    /*添加新学员*/
    void insert(Student student);

    /*根据id批量删除员工*/
    void deleteByIds(List<Integer> ids);

    /*根据id查询学员*/
    Student selectById(Integer id);

    /*根据id修改员工*/
    void update(Student student);

    /*根据id更新员工违纪信息*/
    void violationById(Student student);
}
