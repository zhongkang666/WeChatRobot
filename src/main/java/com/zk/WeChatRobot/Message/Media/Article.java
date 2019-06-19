package com.zk.WeChatRobot.Message.Media;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.zk.WeChatRobot.Message.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Article <br/>
 * Description: <br/>
 * date: 2019/6/13 21:53<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Data
@Builder
@XStreamAlias("item")
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Media{
    @XStreamAlias("Title")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String title;

    @XStreamAlias("Description")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String description;

    @XStreamAlias("PicUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String picUrl;

    @XStreamAlias("Url")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String url;
}
