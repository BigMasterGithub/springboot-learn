package com.john.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.john.springboot.mapper.UserMapper;
import com.john.springboot.pojo.Result;
import com.john.springboot.pojo.User;
import com.john.springboot.util.JWTUtil;
import com.john.springboot.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /*
     * description: 测试@Validated注解的使用，要求name值的长度为5~10
     * create time: 2024/4/27 下午9:03
     * @param name
     * @return java.lang.String
     */
    @GetMapping("/hello")
    public String hello(@Pattern(regexp = "^\\S{5,10}") String name) {
        return "Hello World";
    }

    @GetMapping("/user/search")
    public List<User> search(@RequestParam(name = "name") String name) {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().like("username", name));

        return users;
    }



    @GetMapping("/user/getAll")
    public List<User> getAll() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> users = userMapper.selectList(queryWrapper);
        return users;
    }

    @PostMapping("/login")
    public Result<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        //hutool 工具包进行加密
//        String new_password = DigestUtil.md5Hex(password);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null) {
            return new Result<>(500, "登录失败！", "账号或密码错误！");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("id", user.getId());
        String token = JWTUtil.generateToken(map);
        return new Result<>(2, "成功！", token);

    }
    /*
     * description: 根据header中authonrization信息进行校验用户章台
     * create time: 2024/4/27 下午8:09
     * @param token
     * @return com.john.springboot.pojo.Result<com.john.springboot.pojo.User>
     */
   /* @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token) {

        System.out.println("token:" + token);
        Map<String, Object> stringObjectMap = JWTUtil.parseToken(token);

        Object id = stringObjectMap.get("id");

        User user = userMapper.selectById((int)id);
        return new Result<>(1, "成功", user);

    }*/

    /*
     * description: 获取用户的数据，此时用户必须为登录状态。
     * create time: 2024/4/27 下午8:26
     * @param
     * @return com.john.springboot.pojo.Result<com.john.springboot.pojo.User>
     */
    @GetMapping("/userInfo")
    public Result<User> userInf() {
        //直接通过threadlocal获取
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        User user = userMapper.selectById(id);
        return new Result<>(1, "成功", user);

    }


    @PutMapping("/update")
    public Result<User> update(@Validated @RequestBody User user) {
        userMapper.updateById(user);
        return new Result<>(200, "修改成功！", user);
    }

}
