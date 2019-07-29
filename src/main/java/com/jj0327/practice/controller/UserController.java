package com.jj0327.practice.controller;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jj0327.practice.annotation.WebLogAnnotation;
import com.jj0327.practice.entity.User;
import com.jj0327.practice.entity.base.Result;
import com.jj0327.practice.service.SeleniumService;
import com.jj0327.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jin
 * @since 2018-09-28
 */
@RestController
@RequestMapping("/user")
public class UserController<main> {

    @Autowired
    UserService userService;
    @Resource
    SeleniumService seleniumService;


    @GetMapping("/page")
    @WebLogAnnotation(description = "获取分页")
    public IPage<User> getList(Page userPage, User user) {
        IPage<User> userIPage = user.selectPage(new Page<User>(userPage.getCurrent(), userPage.getSize()),
                new QueryWrapper<User>().select("id", "nickname").eq("id", 1 / 0));
        return userIPage;
    }

    @GetMapping("/sendmsg")
    @WebLogAnnotation(description = "发送短信")
    public int sendmsg() {
        String content = "测试短信内容";
        return userService.senfmsg(content);
    }

    @GetMapping("/login")
    public Result login(String username, String pwd) {
        return seleniumService.login("tongyuelong1313", "tyl199604");
    }

    @GetMapping("/publish")
    Result publish(String username, String pwd) {
        return seleniumService.publish("tongyuelong1313", "tyl199604");
    }

}

