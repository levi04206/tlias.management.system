package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
//@Component
public class TokenInterceptpr implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、获取请求路径url

        String url = request.getRequestURI();  // /login

//        //2、判断路径是否包含login开头，如果包含则说明为登陆操作，此时可以放行
//        if (url.contains("login")){
//            log.info("登陆操作，放行：{}",url);
//            return true;
//        }

        //3、其他路径则需要验证token，获取请求头中的令牌token
        String token = request.getHeader("token");
        log.info("获取的令牌：{}",token);

        //4、判断令牌是否存在，如果令牌不存在则响应401
        if (token == null || token.isEmpty()){
            log.info("令牌不存在，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //5、如果令牌存在，则解析令牌，如果解析失败，则响应401

        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.info("令牌解析失败，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //6、如果令牌存在且解析成功，则放行
        log.info("令牌解析成功，放行");
        return true;
    }
}
