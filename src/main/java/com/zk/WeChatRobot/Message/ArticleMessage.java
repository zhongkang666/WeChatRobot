package com.zk.WeChatRobot.Message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zk.WeChatRobot.Message.Media.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * ClassName: ArticleMessage <br/>
 * Description: <br/>
 * date: 2019/6/13 21:55<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class ArticleMessage extends MessageBase {
    @XStreamAlias("ArticleCount")
    private int articleCount;
    @XStreamAlias("Articles")
    private List<Article> articles;
}
