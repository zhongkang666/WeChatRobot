package com.zk.WeChatRobot.MsgHandler;

import java.util.Map;

/**
 * ClassName: EventMessageHandler <br/>
 * Description: <br/>
 * date: 2019/6/13 21:09<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
public interface EventMessageHandler {

    /**
     * Description: 处理事件的handler<br/>
     * @param:<br/>
     * @return:
     */
    String process(Map<String, String> map);
}
