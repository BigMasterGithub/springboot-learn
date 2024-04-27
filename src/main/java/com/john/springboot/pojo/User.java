package com.john.springboot.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@TableName(value = "user")
@Data
public class User {


    @TableId
    private int id;
    private String username;
    @JsonIgnore// 转换json对象时忽略此属性
    private String password;
    @NotEmpty
    @Pattern(regexp = "^\\S{5,10}")
    private String nickname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime signtime;

}
