package com.john.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan(basePackages = "com.john.springboot.mapper")
public class SpringbootDemoApplication implements ApplicationRunner
{


    @Autowired
    private Environment env;
    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("localhost:"+env.getProperty("local.server.port"));
    }
}
