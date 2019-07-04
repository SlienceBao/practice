package com.jj0327.practice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jj0327.practice.annotation.WebLogAnnotation;
import com.jj0327.practice.entity.User;
import com.jj0327.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisPool;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jin
 * @since 2018-09-28
 */
@Controller
@RequestMapping("/user")
public class UserController<main> {

    @Autowired
    UserService userService;

    @Autowired
    JedisPool jedisPool;

    @GetMapping("/page")
    @ResponseBody
    @WebLogAnnotation(description = "获取分页")
    public IPage<User> getList(Page userPage, User user) {
        IPage<User> userIPage = user.selectPage(new Page<User>(userPage.getCurrent(), userPage.getSize()),
                new QueryWrapper<User>().select("id", "nickname").eq("id", 1 / 0));
        return userIPage;
    }

    @GetMapping("/redis/set")
    @ResponseBody
    public String set(String key, String value) {
        String set = jedisPool.getResource().set(key, value);
        return set;
    }

    @GetMapping("/sendmsg")
    @WebLogAnnotation(description = "发送短信")
    @ResponseBody
    public int sendmsg() {
        String content = "测试短信内容";
        return userService.senfmsg(content);
    }
}

