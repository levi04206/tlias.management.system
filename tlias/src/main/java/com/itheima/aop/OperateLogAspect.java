package com.itheima.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;



    /**
     * 切点：匹配 controller 包下的所有增删改方法
     * 这里通过方法名前缀判断，例如：add、save、insert、update、delete、remove等
     */


    /**
     * 环绕通知：在目标方法执行前后记录日志
     */
    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordOperateLog(ProceedingJoinPoint pjp) throws Throwable {

        // 1. 记录开始时间
        long startTime = System.currentTimeMillis();

        // 2. 执行目标方法
        Object result = pjp.proceed();


        // 3. 记录结束时间和耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;


        // 获取当前操作人ID（此处演示假定从上下文中取）
        Integer empId = getCurrentUserId();

        //  构造日志对象
        OperateLog logObj = new OperateLog();
        logObj.setOperateEmpId(empId);
        logObj.setOperateTime(LocalDateTime.now());
        logObj.setClassName(pjp.getTarget().getClass().getName());
        logObj.setMethodName(pjp.getSignature().getName());
        logObj.setMethodParams(Arrays.toString(pjp.getArgs()));
        logObj.setReturnValue(result != null ? result.toString() : "void");
        logObj.setCostTime(costTime);

        //  保存日志
        try {
            operateLogMapper.insert(logObj);
            log.info("操作日志已记录: {}", logObj);
        } catch (Exception e) {
            log.error("保存操作日志失败: {}", e.getMessage());
        }
        return result;
    }

    /**
     * 模拟获取当前登录用户ID
     * 实际项目中可从ThreadLocal、SecurityContextHolder、Jwt等方式获取
     */
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId(); // 例如临时固定为1
    }
}
