package com.zk.WeChatRobot.Message;

import com.zk.WeChatRobot.Message.Media.Video;
import lombok.Builder;
import lombok.Data;

/**
 * ClassName: VideoMessage <br/>
 * Description: <br/>
 * date: 2019/6/13 21:47<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Data
@Builder
public class VideoMessage {
    private Video video;
}
