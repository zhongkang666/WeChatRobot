package com.zk.WeChatRobot.Message.type;

/**
 * ClassName: EventType <br/>
 * Description: <br/>
 * date: 2019/6/13 22:30<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
public enum EventType {
    SUBCRIBE("subscribe"),
    UNSUBCRIBE("unsubscribe"),
    SCAN("scan"),
    LOCATION("location"),
    CLICK("CLICK"),
    VIEW("VIEW");
    private String code;
    EventType(String code){
        this.code = code;
    }
    public boolean isEquals(String event){
        if (this.code.equals(event)){
            return true;
        }
        return false;
    }
}
