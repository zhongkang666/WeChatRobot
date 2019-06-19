package com.zk.WeChatRobot.Message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.zk.WeChatRobot.Message.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@XStreamAlias("xml")
public class TextMessage extends MessageBase{
    @XStreamAlias("Content")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String content;
}
