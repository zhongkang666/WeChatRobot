package com.zk.WeChatRobot.Message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.zk.WeChatRobot.Message.type.MessageType;
import com.zk.WeChatRobot.Message.xml.XStreamCDataConverter;
import com.zk.WeChatRobot.Message.xml.XStreamMsgTypeConverter;
import com.zk.WeChatRobot.Message.xml.XStreamTransformer;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: MessageBase <br/>
 * Description: <br/>
 * date: 2019/6/13 21:20<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Data
@XStreamAlias("xml")
public class MessageBase implements Serializable {
    @XStreamAlias("ToUserName")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String toUserName;

    @XStreamAlias("FromUserName")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String fromUserName;

    @XStreamAlias("CreateTime")
    protected Long createTime;

    @XStreamAlias("MsgType")
    protected MessageType msgType;

    public String toXML(){
        return XStreamTransformer.toXML(this.getClass(),this);
    }
}
