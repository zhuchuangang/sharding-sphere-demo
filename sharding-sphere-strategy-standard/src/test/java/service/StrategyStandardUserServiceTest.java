package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.szss.shardingjdbc.demo.StrategyStandardApplication;
import com.szss.shardingjdbc.demo.dao.UserDAO;
import com.szss.shardingjdbc.demo.domain.UserDO;
import com.szss.shardingjdbc.demo.service.UserService;

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

    @Test
    public void testInsertUser() {
        UserDO userDO = new UserDO();
        userDO.setUsername("zcg");
        userDO.setPassword("123456");
        userDAO.insertUser(userDO);
        log.debug("user id:{}", userDO.getUserId());
    }
}