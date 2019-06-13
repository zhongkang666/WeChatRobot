package com.zk.WeChatRobot.Message.type;

/**
 * ClassName: MessageType <br/>
 * Description: <br/>
 * date: 2019/6/13 21:25<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
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
}
