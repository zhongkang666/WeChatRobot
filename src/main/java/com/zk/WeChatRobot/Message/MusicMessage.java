package com.zk.WeChatRobot.Message;

import com.zk.WeChatRobot.Message.Media.Music;
import lombok.Builder;
import lombok.Data;

/**
 * ClassName: MusicMessage <br/>
 * Description: <br/>
 * date: 2019/6/13 21:52<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Data
@Builder
public class MusicMessage extends MessageBase {
    private Music music;
}
