package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT 令牌工具类
 * 功能：
 * 1. 生成 JWT 令牌
 * 2. 解析 JWT 令牌
 */
public class JwtUtils {

    // 签名密钥（和你测试类中一致）
    private static final String SECRET_KEY = "aXRoZWltYQ==";

    // 过期时间（12小时 = 12 * 60 * 60 * 1000 毫秒）
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000;

    /**
     * 生成 JWT 令牌
     * @param claims 自定义数据（例如用户id、用户名等）
     * @return 生成的 JWT 字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims) // 设置自定义负载信息
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 指定签名算法与密钥
                .compact(); // 生成令牌
    }

    /**
     * 解析 JWT 令牌
     * @param token JWT 字符串
     * @return 解析得到的 Claims 对象（包含负载信息）
     * @throws io.jsonwebtoken.ExpiredJwtException 如果令牌过期
     * @throws io.jsonwebtoken.SignatureException 如果签名验证失败
     * @throws io.jsonwebtoken.MalformedJwtException 如果格式不合法
     */
    public static Claims parseJwt(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // 指定密钥
                .parseClaimsJws(token) // 解析带签名的 JWT
                .getBody(); // 获取负载信息
    }
}
