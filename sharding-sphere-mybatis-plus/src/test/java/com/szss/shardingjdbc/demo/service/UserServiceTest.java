package com.szss.shardingjdbc.demo.service;

import com.szss.shardingjdbc.demo.dao.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.szss.shardingjdbc.demo.domain.UserDO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 鼠笑天
 * @date 2018/8/5
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    //io.shardingsphere.core.keygen.DefaultKeyGenerator
    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testUser() {
        UserDO userDO = new UserDO();
        //userDO.setUserId(1L);
        userDO.setUsername("zcg");
        userDO.setPassword("123456");
        userDAO.insert(userDO);
        log.debug("user id:{}",userDO.getUserId());
    }
}