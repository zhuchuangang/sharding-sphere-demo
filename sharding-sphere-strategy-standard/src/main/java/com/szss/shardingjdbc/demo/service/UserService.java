package com.szss.shardingjdbc.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szss.shardingjdbc.demo.dao.UserDAO;
import com.szss.shardingjdbc.demo.domain.UserDO;

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

    @Transactional(rollbackFor = RuntimeException.class)
    public Long insertUser(UserDO user) {
        return userDAO.insertUser(user);
    }
}
