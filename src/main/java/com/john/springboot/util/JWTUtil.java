package com.john.springboot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JWTUtil {

    private static final String KEY = "密钥1";

    /*
     * description: 生成jwttoken
     * create time: 2024/4/26 下午10:44
     * @param claims
     * @return java.lang.String
     */
    public static String generateToken(Map<String, Object> claims) {
        return JWT.create().withClaim("claims", claims).withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)).sign(Algorithm.HMAC256(KEY));
    }

    /*
     * description: 接受token，验证token，并返回业务数据
     * create time: 2024/4/26 下午10:45
     * @param token
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY)).build().verify(token).getClaim("claims").asMap();

    }

}



