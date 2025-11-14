package com.itheima.controller;

import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.pojo.StudentDegreeOption;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/*报表统计类*/
@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;


    /*统计员工职位人数*/
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /*统计员工性别人数*/
    @GetMapping("empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别人数");
        List<Map<String,Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /*统计班级学员人数*/
    @GetMapping("/studentCountData")
    public Result getStudentData(){
        log.info("统计班级学员人数");
        ClazzOption clazzOption = reportService.getStudentData();
        return Result.success(clazzOption);
    }

    /*学员最高学历统计*/
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("学院最高学历统计");
        List<StudentDegreeOption> studentDegreeData = reportService.getStudentDegreeData();
        return Result.success(studentDegreeData);

    }

}
