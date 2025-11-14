package com.itheima.controller;
        /*员工管理*/
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult =  empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    /*新增员工*/
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.insert(emp);
        return Result.success();
    }

    /*删除员工*/
   /* @DeleteMapping
    public Result delete(Integer[] ids){
        log.info("根据id批量删除员工：{}", Arrays.toString(ids));
        return Result.success();
    }*/

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("根据id批量删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /*根据id查询员工*/
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询用户：{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /*根据id修改员工*/
    @PutMapping
    public Result UpdateById(@RequestBody Emp emp){
        log.info("根据id修改员工：{}",emp);
        empService.updateById(emp);
        return Result.success();
    }

    /*查询所有员工*/
    @GetMapping("/list")
    public Result findAll(){
        log.info("查询所有员工");
        List<Emp> emp = empService.findAll();
        return Result.success(emp);
    }
}
