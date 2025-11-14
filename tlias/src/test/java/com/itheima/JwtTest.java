package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /**
     生成令牌
     */
    @Test
    public void testGenerateJWT(){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==")//指定加密算法和密钥
                .addClaims(dataMap)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置过期时间
                .compact();//compact()方法生成令牌
        System.out.println(jwt);
    }

    /*
    * 解析令牌*/
    @Test
    public void testParseJWT(){
       String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc2MTA1NzQ0NX0.aXzylcw3cvwtbKtIesAyUj8Hj5OAyJDWg2qRU9IzJBA";
        Claims body = Jwts.parser().setSigningKey("aXRoZWltYQ==")//指定密钥
                .parseClaimsJws(jwt)//解析令牌
                .getBody();//获取自定义信息
        System.out.println(body);
    }


}
