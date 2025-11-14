package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Slf4j
@RestController//用于接受请求，响应数据
@RequestMapping("/depts")

public class DeptController {

    @Autowired
    private DeptService deptService;
    /*
    * 查询部门
    * */
    @GetMapping
    public Result list(){
        List<Dept> result = deptService.findAll();
        return Result.success(result);
    }

    /*删除部门*/

    //方式一：通过HtttpServeletRequest
//    @DeleteMapping("depts")
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        Integer id = Integer.valueOf(idStr);
//        System.out.println("删除的部门为：" + id);
//        return Result.success();
//    }

    //方式二：通过@RequestParam注解
//    @DeleteMapping("depts")
//    public Result delete(@RequestParam("id") Integer deptId){
//        System.out.println("删除的部门为：" + deptId);
//        return Result.success();
//    }

    //方式三：如果前端页面传送的参数名称与形参一致，则注解可省略
    //注意事项：当加入了@RequestParam注解后，必须传入相关参数，因为底层的参数required默认为true
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除的部门为：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    /*新增部门*/
    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        log.info("新增的部门为：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /*根据id查询部门*/
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询的部门为：{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /*修改部门*/
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改的部门为：{}",dept);
        System.out.println("修改部门" + dept);
        deptService.insert(dept);
        return Result.success();
    }
}
