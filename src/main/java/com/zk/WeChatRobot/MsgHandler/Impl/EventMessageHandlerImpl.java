package com.zk.WeChatRobot.MsgHandler.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zk.WeChatRobot.Message.MessageBase;
import com.zk.WeChatRobot.Message.TextMessage;
import com.zk.WeChatRobot.Message.type.EventType;
import com.zk.WeChatRobot.Message.type.MessageType;
import com.zk.WeChatRobot.MsgHandler.EventMessageHandler;
import com.zk.WeChatRobot.mapper.UserMapper;
import com.zk.WeChatRobot.pojo.User;
import com.zk.WeChatRobot.utils.HttpClientUtils;
import com.zk.WeChatRobot.utils.WeChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: EventMessageHandlerImpl <br/>
 * Description: <br/>
 * date: 2019/6/13 22:15<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Service
public class EventMessageHandlerImpl implements EventMessageHandler {

    @Autowired
    private UserMapper userMapper;

    private String userInfoApi = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
    @Override
    public String handleMessage(Map<String, String> map) {
        String event = map.get("Event");
        MessageBase messageBase = null;
        if(EventType.SUBCRIBE.isEquals(event)){
            messageBase = subscribeEventHandle(map);
        }else if (EventType.LOCATION.isEquals(event)){
            messageBase = locationEventHandle(map);
        }
        return messageBase.toString();
    }

    @Override
    public MessageBase subscribeEventHandle(Map<String, String> map) {
        //在用户关注的时候获取用户的信息
        String userInfo = HttpClientUtils.sendGetRequest(String.format(userInfoApi, WeChatUtils.access_token, map.get("FromUserName")));
        User user = JSON.parseObject(userInfo, User.class);
        user.setUserId(UUID.randomUUID().toString());
        userMapper.insert(user);
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageType.TEXT);
        textMessage.setContent("欢迎关注");
        return textMessage;
    }

    @Override
    public MessageBase locationEventHandle(Map<String, String> map) {
        return null;
    }
}
