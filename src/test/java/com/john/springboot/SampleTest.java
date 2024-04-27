package com.john.springboot;


import com.john.springboot.mapper.UserMapper;
import com.john.springboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        int id = 1;
        User user = userMapper.selectById((Integer) id);


        System.out.println(user.toString());
    }

}