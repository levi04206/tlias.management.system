package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter("/*")//拦截路径
@Slf4j
public class ABCFilter implements Filter {

    /**
     * 初始化方法，web服务器启动的时候执行，只执行一次*/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法执行了...");
    }

    /*拦截到请求后执行，会执行多次*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter 拦截到请求，执行了 放行前操作...");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        //放行后
        log.info("doFilter 放行后，继续执行 放行后操作...");
    }

    /**
     * 销毁方法，web服务器关闭后执行，只执行一次*/
    @Override
    public void destroy() {
       log.info("destroy 销毁方法执行了...");
    }
}
