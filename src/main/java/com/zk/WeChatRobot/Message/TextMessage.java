package com.zk.WeChatRobot.Message;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: TextMessage <br/>
 * Description: <br/>
 * date: 2019/6/13 21:35<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
public class TextMessage extends MessageBase {
    private String content;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[" + this.toUserName + "]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[" + this.fromUserName  + "]]></FromUserName>");
        sb.append("<CreateTime>" +this.createTime + "</CreateTime>");
        sb.append("<MsgType><![CDATA[" +this.msgType.toString()+ "]]></MsgType>");
        sb.append("<Content>" +this.content+ "</Content>");
        sb.append("</xml>");
        return sb.toString();
    }
}
