package com.jj0327.practice.mapper;

import com.jj0327.practice.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jin
 * @since 2018-09-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("selectUserList")
    List<String> getPhoneList();

}
