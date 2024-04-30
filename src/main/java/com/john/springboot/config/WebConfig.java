package com.john.springboot.config;

import com.john.springboot.interceptor.PermissionVerificaionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
                .excludePathPatterns("/**");
               /* .excludePathPatterns(
                        "/login",
                        "/index",
                        "/mystatic/**",
                        "/",
                        "/error",
                        "/hello",
                        "/user/**");*/
    }

    /*解决浏览器禁止跨域访问*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOriginPatterns("*") // 支持域
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}) // 支持方法
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

}
