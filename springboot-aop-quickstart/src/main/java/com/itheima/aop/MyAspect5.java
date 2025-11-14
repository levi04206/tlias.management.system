package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class MyAspect5 {
    //前置通知
    //@Before("execution(* com.itheima.service.impl.*.*(..))")

    //匹配com.itheima.service.impl包及其子包下delete方法
//    @Before("execution(* com.itheima.service.impl.DeptServiceImpl.del*(..)) ||" +
//            "execution(* com.itheima.service.impl.DeptServiceImpl.up*(..))")
    @Before("@annotation(com.itheima.anno.LogOperation)")
    public void before(){
        log.info("MyAspect4 -> before ...");
    }

}
