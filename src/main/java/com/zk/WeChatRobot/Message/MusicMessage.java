package com.zk.WeChatRobot.Message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.zk.WeChatRobot.Message.Media.Music;
import com.zk.WeChatRobot.Message.xml.XStreamMediaIdConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class MusicMessage extends MessageBase {

    @XStreamAlias("Music")
    private Music music;
}
