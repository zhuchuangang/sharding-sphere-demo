package com.szss.shardingjdbc.demo.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.szss.shardingjdbc.demo.dao.UserDAO;
import com.szss.shardingjdbc.demo.domain.UserDO;

/**
 * @author 鼠笑天
 * @date 2018/8/5
 */
@Service
public class UserService extends ServiceImpl<UserDAO, UserDO> {

}
