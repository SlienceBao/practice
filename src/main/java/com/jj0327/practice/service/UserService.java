package com.jj0327.practice.service;

import com.jj0327.practice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jin
 * @since 2018-09-28
 */
public interface UserService extends IService<User> {

    int senfmsg(String content);
}
