package com.john.springboot.controller;

import com.john.springboot.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleController {
    @GetMapping("/list")
    public Result<List<String>> list() {
        List<String> list = new ArrayList<>();
        list.add("文章1");
        list.add("文章2");
        list.add("文章3");
        list.add("文章4");
     return new Result<>(2,"成功返回文章列表",list);
    }
}
