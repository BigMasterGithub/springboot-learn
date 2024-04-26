package com.john.springboot.controller;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.john.springboot.pojo.Result;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class SignInController {

    @GetMapping("/hello")
    public String hello(@Pattern(regexp = "^\\S{5,10}")String name) {
        return "Hello World";
    }

    @GetMapping("/login")
    public Result<String> login(@RequestParam String username, @RequestParam String password) {
        //hutool 工具包进行加密
        String new_password = DigestUtil.md5Hex(password);
        System.out.println(new_password);
        return new Result<>(2,"成功！","JWT令牌xx");
        /*
        202cb962ac59075b964b07152d234b70
        {"code":2,"msg":"成功！","data":"JWT令牌xx"}
        * */
    }
}
