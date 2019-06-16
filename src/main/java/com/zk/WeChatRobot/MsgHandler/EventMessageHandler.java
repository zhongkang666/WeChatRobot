package com.zk.WeChatRobot.MsgHandler;

import com.zk.WeChatRobot.Message.MessageBase;

import java.util.Map;

/**
 * ClassName: EventMessageHandler <br/>
 * Description: <br/>
 * date: 2019/6/13 21:09<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
public interface EventMessageHandler extends MessageHandler{

    /**
     * Description: 关注事件的handler<br/>
     * @param:<br/>
     * @return:
     */
    MessageBase subscribeEventHandle(Map<String, String> map);

    /**
     * Description: 地理位置上报事件的handler<br/>
     * @param:<br/>
     * @return:
     */
    String locationEventHandle(Map<String, String> map);

    /**
     * Description:对用户取消关注时进行处理，通常是设置用户的关注状态 <br/>
     * @autor: zk<br />
     * @param: Map<br/>
     * @return:a
     */
    String unSubscribeEventHandle(Map<String,String> map);
}
