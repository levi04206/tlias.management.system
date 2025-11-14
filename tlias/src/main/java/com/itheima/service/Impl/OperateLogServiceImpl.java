package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.OperateLogParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    /*查询所有操作日志*/
    @Override
    public PageResult<OperateLog> list(OperateLogParam operateLogParam) {
        //设置分页参数
        PageHelper.startPage(operateLogParam.getPage(),operateLogParam.getPageSize());
        //执行查询语句
        List<OperateLog> operateLogList = operateLogMapper.list(operateLogParam);
        //解析结果并封装
        Page<OperateLog> list = (Page<OperateLog>) operateLogList;
        return new PageResult<>(list.getTotal(),list.getResult());

    }
}
