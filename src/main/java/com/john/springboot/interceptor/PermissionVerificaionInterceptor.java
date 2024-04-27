package com.john.springboot.interceptor;


import com.john.springboot.util.JWTUtil;
import com.john.springboot.util.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class PermissionVerificaionInterceptor implements HandlerInterceptor {

    /*
     * description:  拦截需要用户登录信息的请求，对于未登录用户，禁止通行；对于已登录的用户，放行并将信息存入ThreadLocal中
     * create time: 2024/4/27 下午8:49
     * @param request
@param response
@param handler
     * @return boolean
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        //打印拦截的请求
        System.out.println("将要拦截的请求：" + request.getRequestURL());


        //获取token
        String token = request.getHeader("Authorization");
        try {
            Map<String, Object> userInfoMap = JWTUtil.parseToken(token);
            System.out.println("存入到threadlocal中");
            //存入到 ThreadLocal中
            ThreadLocalUtil.set(userInfoMap);


            return true;
        } catch (Exception e) {
            response.setStatus(401);

            return false;//no pass
        }


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("移除请求threadlocal中的数据");
        ThreadLocalUtil.remove();
    }
}
