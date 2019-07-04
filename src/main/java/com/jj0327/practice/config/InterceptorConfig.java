package com.jj0327.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jinbao
 * @date 2019/3/14 15:04
 * @description:
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("===============拦截器配置初始化================");
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }
}
