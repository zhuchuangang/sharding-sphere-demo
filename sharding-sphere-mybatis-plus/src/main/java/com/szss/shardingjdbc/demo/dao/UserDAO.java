package com.szss.shardingjdbc.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.szss.shardingjdbc.demo.domain.UserDO;

/**
 * @author 鼠笑天
 * @date 2018/8/5
 */
@Mapper
public interface UserDAO extends BaseMapper<UserDO> {

}
