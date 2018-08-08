package com.szss.shardingjdbc.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 根据用户ID查询用户
     *
     * @param id 用户ID
     * @return UserDO
     */
    @Select("select user_id as userId,username,password from t_user where user_id=#{id}")
    UserDO findById(@Param("id") Long id);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return UserDO
     */
    @Select("select user_id as userId,username,password from t_user where username=#{username} limit 1,1")
    UserDO findByUsername(@Param("username") String username);
}
