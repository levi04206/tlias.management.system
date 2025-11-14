package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /*学员信息查询（条件分页查询）*/
    @GetMapping
    public Result select(StudentQueryParam studentQueryParam){
        log.info("班级列表分页查询");
        PageResult<Student> pageResult = studentService.select(studentQueryParam);
        return Result.success(pageResult);
    }

    /*添加学员*/
    @PostMapping
    public Result insert(@RequestBody Student student){
        log.info("添加学员：{}",student);
        studentService.insert(student);
        return Result.success();
    }

    /*根据id删除学员*/
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        log.info("删除员工：{}",ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /*根据id查询学员*/
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("根据id查询学员：{}",id);
        Student student = studentService.seletById(id);
        return Result.success(student);
    }

    /*修改学员基本信息*/
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学员信息：{}",student);
        studentService.update(student);
        return Result.success();
    }

    /*修改学员违纪信息*/
    @PutMapping("/violation/{id}/{score}")
    public Result violationByid(@PathVariable Integer id,@PathVariable Integer score){
        log.info("学员违纪处理：{},{}",id,score);
        studentService.violationById(id,score);
        return Result.success();
    }
}
