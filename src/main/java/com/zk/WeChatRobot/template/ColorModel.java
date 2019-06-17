package com.zk.WeChatRobot.template;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by LDY on 2017/8/1.
 * 微信消息的字体模型
 */
@Data
@AllArgsConstructor
public class ColorModel {
    private String value;   //内容
    private String color;   //字体颜色
}
