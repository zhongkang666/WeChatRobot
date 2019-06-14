package com.zk.WeChatRobot.Message;

import com.zk.WeChatRobot.Message.type.MessageType;
import lombok.Data;

/**
 * ClassName: MessageBase <br/>
 * Description: <br/>
 * date: 2019/6/13 21:20<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Data
public class MessageBase {
    protected String toUserName;
    protected String fromUserName;
    protected Long createTime;
    protected MessageType msgType;
}
