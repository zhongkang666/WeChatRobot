package com.zk.WeChatRobot.Message.Media;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.zk.WeChatRobot.Message.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Video <br/>
 * Description: <br/>
 * date: 2019/6/13 21:47<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class Video implements Media{

    @XStreamAlias("MediaId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String mediaId;

    @XStreamAlias("Title")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String title;

    @XStreamAlias("Description")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String description;
}
