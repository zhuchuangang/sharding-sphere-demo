package com.szss.shardingjdbc.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.szss.shardingjdbc.demo.domain.ConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 鼠笑天
 * @date 2018/12/19
 */
@Mapper
public interface ConfigDAO extends BaseMapper<ConfigDO> {

}
