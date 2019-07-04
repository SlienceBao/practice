package com.jj0327.practice;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author ceshi
 * @Title: 1
 * @Package 1
 * @Description: 1
 * @date 2018/9/3011:02
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PracticeApplication.class);  // **Application.class 为标注有@SpringBootApplication的主启动类
    }

}
