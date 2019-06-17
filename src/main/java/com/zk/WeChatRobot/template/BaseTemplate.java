package com.zk.WeChatRobot.template;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * LDY ：2017.10.27
 * 两个关键字的模板
 * Created by Administrator on 2017/8/1.
 */
@Data
@AllArgsConstructor
public class BaseTemplate {
    private String touser;      //openId
    private String template_id; //模版消息Id
    private String url;         //模版点击后链接的地方
    private String topcolor;    //模版头颜色
    private RegisterData data;
}
