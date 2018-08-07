package com.szss.shardingjdbc.demo.domain;

import lombok.Data;

/**
 * @author 鼠笑天
 * @date 2018/8/5
 */
@Data
public class UserDO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
