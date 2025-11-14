package com.itheima.filter;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@WebFilter("/*")
@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1、获取请求路径url
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();  // /login

        //2、判断路径是否包含login开头，如果包含则说明为登陆操作，此时可以放行
        if (url.contains("login")){
            log.info("登陆操作，放行：{}",url);
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //3、其他路径则需要验证token，获取请求头中的令牌token
        String token = request.getHeader("token");
        log.info("获取的令牌：{}",token);

        //4、判断令牌是否存在，如果令牌不存在则响应401
        if (token == null || token.isEmpty()){
            log.info("令牌不存在，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5、如果令牌存在，则解析令牌，如果解析失败，则响应401

        try {
            Claims claims = JwtUtils.parseJwt(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("令牌解析成功，当前员工id为：{},将其存入threadLocal",empId);
        } catch (Exception e) {
            log.info("令牌解析失败，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //6、如果令牌存在且解析成功，则放行
        log.info("令牌解析成功，放行");
        filterChain.doFilter(servletRequest,servletResponse);

        CurrentHolder.remove();
    }
}
