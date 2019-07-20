package com.jj0327.practice.util;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author jinbao
 * @date 2019/7/5 13:43
 * @description:
 */
public class SeleniumUtil {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

//    public static void main(String[] args) throws InterruptedException {
//        login("tongyuelong1313", "tyl199604");
//    }


}
