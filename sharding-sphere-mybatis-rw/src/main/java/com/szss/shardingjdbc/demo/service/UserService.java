package com.szss.shardingjdbc.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szss.shardingjdbc.demo.dao.UserDAO;

/**
 * @author 鼠笑天
 * @date 2018/8/5
 */
@Service
public class UserService {
    /**
     * UserDAO
     */
    @Autowired
    private UserDAO userDAO;
}
