package com.zk.WeChatRobot.MsgHandler;

import com.zk.WeChatRobot.Message.MessageBase;

import java.util.Map;

/**
 * ClassName: MessageHandler <br/>
 * Description: 消息服务接口<br/>
 * date: 2019/6/13 20:59<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
public interface MessageHandlerFactory {

    String handleMessage(Map<String,String> map);
}
