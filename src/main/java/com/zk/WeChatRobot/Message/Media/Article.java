package com.zk.WeChatRobot.Message.Media;

import lombok.Builder;
import lombok.Data;

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
public class Article {
    private String title;
    private String description;
    private String picUrl;
    private String url;
}
