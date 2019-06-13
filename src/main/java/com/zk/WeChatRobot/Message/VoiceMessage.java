package com.zk.WeChatRobot.Message;

import lombok.Builder;
import lombok.Data;

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
public class VoiceMessage extends MessageBase {
    private String mediaId;
}
