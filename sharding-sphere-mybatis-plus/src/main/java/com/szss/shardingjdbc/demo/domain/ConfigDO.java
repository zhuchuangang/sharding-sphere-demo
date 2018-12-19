package com.szss.shardingjdbc.demo.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @author 鼠笑天
 * @date 2018/12/19
 */
@Data
@TableName("t_config")
public class ConfigDO {
    @TableId(value = "id")
    private Long id;

    private String name;
}
