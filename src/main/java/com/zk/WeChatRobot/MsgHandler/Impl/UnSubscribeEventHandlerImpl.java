package com.zk.WeChatRobot.MsgHandler.Impl;

import com.zk.WeChatRobot.Message.MessageBase;
import com.zk.WeChatRobot.Message.type.EventType;
import com.zk.WeChatRobot.Message.type.EventTypeHanlder;
import com.zk.WeChatRobot.MsgHandler.EventMessageHandler;
import com.zk.WeChatRobot.mapper.UserMapper;
import com.zk.WeChatRobot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:对用户取消关注时进行处理，通常是设置用户的关注状态
 */
@EventTypeHanlder(value = EventType.UNSUBCRIBE)
@Component
public class UnSubscribeEventHandlerImpl implements EventMessageHandler {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String process(Map<String, String> map) {
        User user = new User();
        user.setIsSubscribe(false);
        user.setOpenId(map.get("FromUserName"));
        userMapper.updateByPrimaryKeySelective(user);
        return "";
    }
}
