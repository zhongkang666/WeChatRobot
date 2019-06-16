package com.zk.WeChatRobot.router;

import com.zk.WeChatRobot.MsgHandler.CommonMsgHandler;
import com.zk.WeChatRobot.MsgHandler.EventMessageHandler;
import com.zk.WeChatRobot.MsgHandler.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ClassName: WeChatRouter <br/>
 * Description: 该类主要功能是针对微信发送的不同的消息请求进行路由，分别用不同的messageHandler进行处理 <br/>
 * date: 2019/6/13 20:39<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Component
public class WeChatRouter {

    @Autowired
    private EventMessageHandler eventMessageHandler;

    @Autowired
    private CommonMsgHandler commonMsgHandler;

    public MessageHandler route(Map<String,String> map){
        if(map.get("Event") != null){
            return eventMessageHandler;
        }
        return commonMsgHandler;
    }
}
