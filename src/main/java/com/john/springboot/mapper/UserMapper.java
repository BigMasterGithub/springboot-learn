package com.john.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.john.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
