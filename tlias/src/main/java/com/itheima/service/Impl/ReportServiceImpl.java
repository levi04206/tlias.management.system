package com.itheima.service.Impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentDegreeOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    /*统计员工职位人数*/

    //第一个map的值{pos:学工部，num:2}  {pos:教研部，num:3} {pos:财务部,num:4
    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(mapData -> mapData.get("pos")).toList();
        List<Object> dataList = list.stream().map(mapData -> mapData.get("num")).toList();
        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    /*统计员工学员人数*/
    //每一个map的值：{clz:java就业100,num:2}  {clz:java就业106,num:2}  {clz:java就业108,num:8}，所有的map共同构成这样一个list集合
    @Override
    public ClazzOption getStudentData() {
       List<Map<String,Object>> list =  empMapper.countStudentData();
        List<Object> clazzList = list.stream().map(mapData -> mapData.get("clz")).toList();
        List<Object> dataList = list.stream().map(mapData -> mapData.get("num")).toList();
        return new ClazzOption(clazzList,dataList);
    }

    /*学员学历分布统计*/
    //每一个map值：{deg:高中，num:5},{deg:大学，num:6}
    @Override
    public List<StudentDegreeOption> getStudentDegreeData() {
        List<Map<String, Object>> list = empMapper.countStudentDegreeData();
        List<StudentDegreeOption> studentDegreeOptions = list.stream().map(map -> new StudentDegreeOption( map.get("deg"),  map.get("num"))).toList();
        return studentDegreeOptions;

    }
}
