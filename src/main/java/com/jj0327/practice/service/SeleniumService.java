package com.jj0327.practice.service;

import com.jj0327.practice.entity.base.Result;

/**
 * @author jinbao
 * @date 2019/7/8 14:08
 * @description:
 */
public interface SeleniumService {

    Result login(String username, String password);

    Result publish(String username, String password);
}
