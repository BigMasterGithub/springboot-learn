package com.john.springboot.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

public class JWTTest {


    @Test
    public void test01() {

        HashMap<String, String> map = new HashMap<>();
        map.put("id", "123");
        map.put("username", "张三");
        String jwtToken = JWT.create()
                .withClaim("key_1", map)
                .withClaim("key_2",map)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256("密钥xx"));
        System.out.println(jwtToken);

        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJrZXlfIjp7ImlkIjoiMTIzIiwidXNlcm5hbWUiOiLlvKD
        // kuIkifSwiZXhwIjoxNzE0MTg1MTUwfQ.a2JXPMfjtBB_I4d6KveDY1jT2-79hanQ_kEqikpoXrM

    }

    /*
     * description: com\john\springboot\jwt\JWTTest.java
     * create time: 2024/4/26 下午10:39
     * @param
     * @return void
     * 异常报错：SignatureVerificationException: The Token's Signature resulted invalid
     */
    @Test
    public void test02(){
        String testedToken="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJrZXlfMiI6eyJpZCI6IjEyMyIsInVzZXJuYW1lIjoi5byg5LiJIn0sImtleV8xIjp7ImlkIjo" +
                "iMTIzIiwidXNlcm5hbWUiOiLlvKDkuIkifSwiZXhwIjoxNzE0MTg1NDAyfQ." +
                "JQBMYlBOKoCd-lYnnsqgbjOePNHRCTmNbcvljeplE6s";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("密钥xx")).build();
        DecodedJWT actualInfo = jwtVerifier.verify(testedToken);

        System.out.println(actualInfo.getClaims());
        /*
        * {key_2={"id":"123","username":"张三"}, key_1={"id":"123","username":"张三"}, exp=1714185402}
        *
        * */

    }
}
