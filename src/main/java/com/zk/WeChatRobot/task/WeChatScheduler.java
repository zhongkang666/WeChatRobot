package com.zk.WeChatRobot.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zk.WeChatRobot.config.WxMpProperties;
import com.zk.WeChatRobot.utils.HttpClientUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
public class WeChatScheduler {

    @Autowired
    private WxMpProperties properties;

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("spring-task-thread");
        return scheduler;
    }

    @Scheduled(fixedDelay = 7100000)
    @CachePut(value = "SystemCache",key = "'access_token'")
    public String refreshAccessToken(){
        //访问微信的接口获取access_token
        String url = String.format(properties.getAccessTokenApi(), properties.getAppID(), properties.getAppsecret());
        String response = HttpClientUtils.sendGetRequest(url);
        JSONObject jsonObject = JSON.parseObject(response);
        System.out.println(jsonObject.getString("access_token"));
        return jsonObject.getString("access_token");
    }
}
