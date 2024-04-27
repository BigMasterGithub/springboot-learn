package com.john.springboot.controller;

import com.john.springboot.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleController {

    /*
     * description: 通过校验jwt来获取登录状态，并返回文章数据
     * create time: 2024/4/27 下午1:20
     * @param token
@param response
     * @return com.john.springboot.pojo.Result<java.lang.String>
     */
    @GetMapping("/list")
    public Result<String> list() {

        List<String> list = new ArrayList<>();
        list.add("文章1");
        list.add("文章2");
        list.add("文章3");
        list.add("文章4");
        return new Result<>(2, "成功返回文章列表", list.toString());
    }
}
