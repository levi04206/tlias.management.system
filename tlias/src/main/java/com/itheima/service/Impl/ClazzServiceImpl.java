package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpMapper empMapper;

    /*对班级列表进行分页查询*/
    @Override
    public PageResult<Clazz> list(ClazzQueryParam clazzQueryParam) {
        //1、设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        //2、执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        //3、解析查询结果并封装
        Page<Clazz> list = (Page<Clazz>) clazzList;
        return new PageResult<>(list.getTotal(),list.getResult());
    }

    /*添加班级*/
    @Override
    public void add(Clazz clazz) {
        //1、补全基础属性
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        //2、根据masterId查询对应员工的信息
        Emp master = empMapper.getById(clazz.getId());
        if (master != null){
            clazz.setMasterName(master.getName());
        }

        //3、将该对象传入到mapper层
        clazzMapper.insert(clazz);



    }

    /*根据id删除部门*/
    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    /*根据id查询班级*/
    @Override
    public Clazz selectById(Integer id) {
        return clazzMapper.selectById(id);
    }

    /*修改班级*/
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());

        //根据masterId查询对应员工的信息
        Emp master = empMapper.getById(clazz.getId());
        if (master != null){
            clazz.setMasterName(master.getName());
        }

        clazzMapper.update(clazz);
    }

    /*查询所有列表*/
    @Override
    public List<Clazz> findAll() {
        List<Clazz> clazzList = clazzMapper.findAll();
        return clazzList;
    }


}
