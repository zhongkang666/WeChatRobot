package com.zk.WeChatRobot.MsgHandler.Impl;

import com.zk.WeChatRobot.Message.MessageBase;
import com.zk.WeChatRobot.Message.type.EventType;
import com.zk.WeChatRobot.MsgHandler.EventMessageHandler;
import com.zk.WeChatRobot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public String handleMessage(Map<String, String> map) {
        String event = map.get("Event");
        MessageBase messageBase = null;
        if(EventType.SUBCRIBE.isEquals(event)){
            messageBase = subscribeEventHandle(map);
        }else if (EventType.LOCATION.isEquals(event)){
            messageBase = locationEventHandle(map);
        }
        return null;
    }

    @Override
    public MessageBase subscribeEventHandle(Map<?, ?> map) {
        return null;
    }

    @Override
    public MessageBase locationEventHandle(Map map) {
        return null;
    }
}
