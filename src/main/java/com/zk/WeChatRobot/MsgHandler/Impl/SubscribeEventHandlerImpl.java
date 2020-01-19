package com.zk.WeChatRobot.MsgHandler.Impl;

import com.zk.WeChatRobot.Message.MessageBase;
import com.zk.WeChatRobot.Message.MessageFactory;
import com.zk.WeChatRobot.Message.type.EventType;
import com.zk.WeChatRobot.Message.type.EventTypeHanlder;
import com.zk.WeChatRobot.Message.type.MessageType;
import com.zk.WeChatRobot.MsgHandler.EventMessageHandler;
import com.zk.WeChatRobot.mapper.UserMapper;
import com.zk.WeChatRobot.pojo.User;
import com.zk.WeChatRobot.pojo.UserExample;
import com.zk.WeChatRobot.utils.WeChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 关注事件的handler
 */
@EventTypeHanlder(value = EventType.SUBCRIBE)
@Component
public class SubscribeEventHandlerImpl implements EventMessageHandler {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String process(Map<String, String> map) {
        //在用户关注的时候获取用户的信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenIdEqualTo(map.get("FromUserName"));
        if(userMapper.countByExample(userExample) < 1){
            User user = WeChatUtils.getUserInfo(map.get("FromUserName"));
            user.setIsSubscribe(true);
            userMapper.insert(user);
        }else{
            User user = new User();
            user.setIsSubscribe(true);
            user.setOpenId(map.get("FromUserName"));
            userMapper.updateByPrimaryKeySelective(user);
        }
        map.put("content","欢迎关注");
        MessageBase message = MessageFactory.createMessage(MessageType.TEXT, map, null);
        return message.toXML();
    }
}
