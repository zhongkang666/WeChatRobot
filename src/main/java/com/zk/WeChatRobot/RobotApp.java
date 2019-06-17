package com.zk.WeChatRobot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.zk.WeChatRobot.mapper")
@EnableCaching
public class RobotApp {

    public static void main(String[] args) {
        SpringApplication.run(RobotApp.class,args);
    }
}
