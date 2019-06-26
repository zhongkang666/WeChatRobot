package com.zk.WeChatRobot.MsgHandler.Impl;

import com.zk.WeChatRobot.Message.MessageBase;
import com.zk.WeChatRobot.Message.MessageFactory;
import com.zk.WeChatRobot.Message.type.EventType;
import com.zk.WeChatRobot.Message.type.MessageType;
import com.zk.WeChatRobot.MsgHandler.EventMessageHandler;
import com.zk.WeChatRobot.mapper.UserMapper;
import com.zk.WeChatRobot.pojo.User;
import com.zk.WeChatRobot.pojo.UserExample;
import com.zk.WeChatRobot.utils.WeChatUtils;
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
        }else if(EventType.LOCATION.isEquals(event)){
            return locationEventHandle(map);
        }else if(EventType.UNSUBCRIBE.isEquals(event)){
            return unSubscribeEventHandle(map);
        }
        return messageBase.toXML();
    }

    @Override
    public MessageBase subscribeEventHandle(Map<String, String> map) {
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
        return message;
    }

    @Override
    public String locationEventHandle(Map<String, String> map) {
        User user = new User();
        user.setOpenId(map.get("FromUserName"));
        user.setLatitude(Double.valueOf(map.get("Latitude")));
        user.setLongitude(Double.valueOf(map.get("Longitude")));
        user.setAccuracy(Double.valueOf(map.get("Precision")));
        userMapper.updateByPrimaryKeySelective(user);
        return "success";
    }

    @Override
    public String unSubscribeEventHandle(Map<String, String> map) {
        User user = new User();
        user.setIsSubscribe(false);
        user.setOpenId(map.get("FromUserName"));
        userMapper.updateByPrimaryKeySelective(user);
        return "";
    }
}
