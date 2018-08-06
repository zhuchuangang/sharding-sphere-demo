package com.szss.shardingjdbc.demo.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

/**
 * @author 鼠笑天
 * @date 2018/8/5
 */
@Data
@TableName("t_user")
public class UserDO {
    @TableId(value = "user_id")
    private Long userId;

    private String username;

    private String password;
}
