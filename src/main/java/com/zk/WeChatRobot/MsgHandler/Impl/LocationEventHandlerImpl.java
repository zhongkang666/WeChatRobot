package com.zk.WeChatRobot.MsgHandler.Impl;

import com.zk.WeChatRobot.Message.type.EventType;
import com.zk.WeChatRobot.Message.type.EventTypeHanlder;
import com.zk.WeChatRobot.MsgHandler.EventMessageHandler;
import com.zk.WeChatRobot.mapper.UserMapper;
import com.zk.WeChatRobot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@EventTypeHanlder(value = EventType.LOCATION)
@Component
public class LocationEventHandlerImpl implements EventMessageHandler {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String process(Map<String, String> map) {
        User user = new User();
        user.setOpenId(map.get("FromUserName"));
        user.setLatitude(Double.valueOf(map.get("Latitude")));
        user.setLongitude(Double.valueOf(map.get("Longitude")));
        user.setAccuracy(Double.valueOf(map.get("Precision")));
        userMapper.updateByPrimaryKeySelective(user);
        return "success";
    }
}
