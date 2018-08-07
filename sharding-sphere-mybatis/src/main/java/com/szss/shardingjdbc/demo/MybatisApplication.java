package com.szss.shardingjdbc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 鼠笑天
 * @date 2018/8/5
 */
@SpringBootApplication
@MapperScan(basePackages = "com.szss.shardingjdbc.demo.dao")
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
