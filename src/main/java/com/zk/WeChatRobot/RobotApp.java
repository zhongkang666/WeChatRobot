package com.zk.WeChatRobot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zk.WeChatRobot.mapper")
public class RobotApp {

    public static void main(String[] args) {
        SpringApplication.run(RobotApp.class,args);
    }
}
