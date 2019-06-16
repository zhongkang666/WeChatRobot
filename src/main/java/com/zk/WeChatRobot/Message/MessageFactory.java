package com.zk.WeChatRobot.Message;

import com.zk.WeChatRobot.Message.Media.Media;
import com.zk.WeChatRobot.Message.type.MessageType;

import java.util.Date;
import java.util.Map;

/**
 * ClassName: MessageFactory <br/>
 * Description: 创建各种消息的工厂类<br/>
 * date: 2019/6/14 22:35<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
public class MessageFactory {

    public static MessageBase createMessage(MessageType type, Map<String,String> map, Media media){
        MessageBase message = null;
        if(type == MessageType.TEXT){
            message = createTextMessage(type, map);
        }else if(type == MessageType.IMAGE){

        }else if(type == MessageType.MUSIC){

        }else if(type == MessageType.NEWS){

        }
        return message;
    }
    private static MessageBase createTextMessage(MessageType type, Map<String,String> map){
        TextMessage textMessage = new TextMessage();
        currentSettings(textMessage,type,map);
        textMessage.setContent(map.get("content"));
        return textMessage;
    }

    private static void currentSettings(MessageBase message,MessageType type, Map<String,String> map){
        message.setToUserName(map.get("FromUserName"));
        message.setFromUserName(map.get("ToUserName"));
        message.setCreateTime(new Date().getTime());
        message.setMsgType(type);
    }
}
