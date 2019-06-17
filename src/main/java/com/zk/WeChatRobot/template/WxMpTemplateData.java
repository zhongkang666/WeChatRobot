package com.zk.WeChatRobot.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Created by LDY on 2017/8/1.
 * 微信消息的字体模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxMpTemplateData {
    @NotBlank
    private String name;
    @NotBlank
    private String value;   //内容
    private String color;   //字体颜色
}
