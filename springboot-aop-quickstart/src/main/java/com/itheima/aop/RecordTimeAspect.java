package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
//@Aspect//标识这个类是一个aop类
@Component
@Slf4j
public class RecordTimeAspect {
    @Around("execution(* com.itheima.service.impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
       //1、记录方法运行开始时间
        long start = System.currentTimeMillis();

        //2、执行原始的方法
        Object result = pjp.proceed();

        //3、记录方法运行结束时间，记录耗时
        long end = System.currentTimeMillis();
        log.info("{}方法运行耗时：{}ms",pjp.getSignature(),end-start);
        return result;
    }
}
