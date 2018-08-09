package com.szss.shardingjdbc.demo.service;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.javafaker.Faker;
import com.szss.shardingjdbc.demo.StrategyStandardApplication;
import com.szss.shardingjdbc.demo.dao.UserDAO;
import com.szss.shardingjdbc.demo.domain.UserDO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 鼠笑天
 * @date 2018/8/5
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StrategyStandardApplication.class)
public class StrategyStandardUserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    private Faker faker = new Faker(new Locale("zh-CN"));

    @Test
    public void testInsertUser() {
        for (long i = 0; i < 10; i++) {
            UserDO userDO = new UserDO();
            userDO.setUsername(faker.name().lastName() + faker.name().firstName());
            userDO.setPassword("123456");
            userDAO.insertUser(userDO);
            //log.info("user id:{}", userDO.getUserId());
        }
    }

    @Test
    public void testFindById() {
        Long id = 233913777018372096L;
        UserDO result = userDAO.findById(id);
        log.info("user info:" + result.toString());
    }

    @Test
    public void testFindByUsername() {
        UserDO result = userDAO.findByUsername("zcg");
        log.info("user info:" + result.toString());
    }
}