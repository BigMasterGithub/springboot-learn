package com.john.springboot.config;

import com.john.springboot.interceptor.PermissionVerificaionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private PermissionVerificaionInterceptor permissionVerificaionInterceptor;


    /*
     * description: 为某些接口添加拦截
     * create time: 2024/4/27 下午1:30
     * @param registry
     * @return void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissionVerificaionInterceptor)
                .excludePathPatterns(
                        "/login",
                        "/index",
                        "/mystatic/**",
                        "/",
                        "/error",
                        "/hello");
    }
}
