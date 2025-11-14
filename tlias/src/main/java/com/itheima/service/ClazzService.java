package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> list(ClazzQueryParam clazzQueryParam);

    void add(Clazz clazz);

    void deleteById(Integer id);

    Clazz selectById(Integer id);

    void update(Clazz clazz);

    List<Clazz> findAll();
}


