package com.zk.WeChatRobot.Message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zk.WeChatRobot.Message.Media.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class VideoMessage {

    @XStreamAlias("Video")
    private Video video;
}
