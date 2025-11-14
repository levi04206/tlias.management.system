package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect6 {
    //前置通知
    //@Before("execution(* com.itheima.service.impl.*.*(..))")

    //匹配com.itheima.service.impl包及其子包下delete方法
//    @Before("execution(* com.itheima.service.impl.DeptServiceImpl.del*(..)) ||" +
//            "execution(* com.itheima.service.impl.DeptServiceImpl.up*(..))")
    @Before("execution(* com.itheima.service.*.*(..))")
    public void before(JoinPoint joinPoint){
        //获取目标对象
        Object target = joinPoint.getTarget();
        log.info("获取目标对象：{}", target);
        //获取目标类名
        String className = target.getClass().getName();
        log.info("获取目标类名：{}", className);
        //获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        log.info("获取目标方法名：{}", methodName);
        //获取目标方法运行参数
        Object[] args = joinPoint.getArgs();
        log.info("获取目标方法运行参数：{}", args);

    }

}
