package com.zk.WeChatRobot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WxMpProperties {
    private String appID;

    private String appsecret;

    private String accessTokenApi;

    private String token;

    private String uploadApi;
}
