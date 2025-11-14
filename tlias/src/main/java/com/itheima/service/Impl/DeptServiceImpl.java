package com.itheima.service.Impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        //查询该部门下员工数量
        Long count = empMapper.countEmpData(id);
        //如果该部门有员工则不能删除
        if (count > 0){
            throw new RuntimeException("对不起，当前部门下有员工，不能直接删除！");
        }
        //否则删除
        deptMapper.deleteById(id);
    }
    @Override
    public void add(Dept dept) {
        //1、补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2、调用mapper接口插入数据
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void insert(Dept dept) {
        //1、对数据进行补全
        dept.setUpdateTime(LocalDateTime.now());
        //2、调用mapper的方法
        deptMapper.update(dept);
    }


}
