package com.zk.WeChatRobot.Message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.zk.WeChatRobot.Message.xml.XStreamMediaIdConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: VoiceMessage <br/>
 * Description: <br/>
 * date: 2019/6/13 21:37<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Data
@Builder
@XStreamAlias("xml")
@NoArgsConstructor
@AllArgsConstructor
public class VoiceMessage extends MessageBase {
    @XStreamAlias("Voice")
    @XStreamConverter(value = XStreamMediaIdConverter.class)
    private String mediaId;
}
