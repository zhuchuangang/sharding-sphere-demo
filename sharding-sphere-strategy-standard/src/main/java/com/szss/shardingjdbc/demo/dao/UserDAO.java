package com.szss.shardingjdbc.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.szss.shardingjdbc.demo.domain.UserDO;

/**
 * @author 鼠笑天
 * @date 2018/8/5
 */
@Mapper
public interface UserDAO {
    /**
     * 新增用户
     * 
     * @param user 用户信息
     * @return
     */
    @Insert("insert into t_user(username,password) values(#{user.username},#{user.password})")
    Long insertUser(@Param("user") UserDO user);
}
