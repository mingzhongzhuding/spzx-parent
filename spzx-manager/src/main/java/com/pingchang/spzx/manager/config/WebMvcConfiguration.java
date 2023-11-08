package com.pingchang.spzx.manager.config;

import com.pingchang.spzx.manager.interceptor.LoginAuthInterceptor;
import com.pingchang.spzx.manager.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/10/19  11:06
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {


    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;

    @Autowired
    private UserProperties userProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                //.excludePathPatterns("/admin/system/index/login",
                //        "/admin/system/index/generateValidateCode")
                .excludePathPatterns(userProperties.getNoAuthUrls())
                .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      //添加路径规则
                .allowCredentials(true)              //是否允许在跨域的情况下传递cookie
                .allowedOriginPatterns("*")          //允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
