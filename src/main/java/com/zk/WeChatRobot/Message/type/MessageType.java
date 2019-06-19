package com.zk.WeChatRobot.Message.type;

import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.zk.WeChatRobot.Message.xml.XStreamMsgTypeConverter;

/**
 * ClassName: MessageType <br/>
 * Description: <br/>
 * date: 2019/6/13 21:25<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@XStreamConverter(value = XStreamMsgTypeConverter.class)
public enum MessageType {
    TEXT("text"),
    IMAGE("image"),
    VOICE("voice"),
    VIDEO("video"),
    MUSIC("music"),
    NEWS("news");

    private String code;
    MessageType(String code){
        this.code = code;
    }
    public String code(){
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
