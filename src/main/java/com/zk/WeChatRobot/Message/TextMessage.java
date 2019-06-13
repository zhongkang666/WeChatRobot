package com.zk.WeChatRobot.Message;

import lombok.Builder;
import lombok.Data;

/**
 * ClassName: TextMessage <br/>
 * Description: <br/>
 * date: 2019/6/13 21:35<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Data
@Builder
public class TextMessage extends MessageBase {
    private String content;
}
