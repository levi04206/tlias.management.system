package com.itheima.service;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteById(Integer id);


    void add(Dept dept);

    Dept getById(Integer id);

    void insert(Dept dept);
}
