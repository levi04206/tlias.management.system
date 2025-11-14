package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j

public class MyAspect1 {

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt(){}

    //前置通知，在目标方法运行之前执行
    @Before("pt()")
    public void before(){
        log.info("before...");
    }

    //环绕通知，在目标方法运行之前和之后都执行
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around before...");

        Object result = pjp.proceed();

        log.info("around after...");
        return result;
    }

    //后置通知，在目标方法运行之后执行,无论方法正常结束或者异常结束都执行
    @After("pt()")
    public void after(){
        log.info("after...");
    }

    //返回后通知，在目标方法正常返回之后执行
    @AfterReturning("pt()")
    public void afterReturning(){
        log.info("afterReturning...");
    }

    //异常后通知，在目标方法异常之后执行
    @AfterThrowing("pt()")
   public void aferThrowing(){
       log.info("aferThrowing...");
   }
}
