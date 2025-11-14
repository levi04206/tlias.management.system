package com.itheima.controller;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.OperateLogParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;
    /*查询所有日志*/
    @GetMapping("/log/page")
    public Result list(OperateLogParam operateLogParam){
        log.info("查询所有日志");
        PageResult<OperateLog> pageResult = operateLogService.list(operateLogParam);
        return Result.success(pageResult);
    }
}
