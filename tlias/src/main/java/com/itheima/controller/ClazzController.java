package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j//记录日志
@RestController//表示其为控制层，接受请求，响应数据
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;


    /*查询所有班级*/
    @GetMapping("/list")
    public Result findAll(){
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

    /*添加班级*/
    @PostMapping
    public Result insert(@RequestBody Clazz clazz){
        log.info("添加班级信息为：{}",clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    /*根据id删除班级*/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除的部门为：{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }

    /*根据id查询班级*/
    @GetMapping("/{id}")
    public Result SelectById(@PathVariable Integer id){
        log.info("查询班级为：{}",id);
        Clazz clazz = clazzService.selectById(id);
        return Result.success(clazz);
    }

    /*修改班级*/
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /*班级列表分页查询*/
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("班级列表分页查询");
        PageResult<Clazz> pageResult = clazzService.list(clazzQueryParam);
        return Result.success(pageResult);
    }



}
