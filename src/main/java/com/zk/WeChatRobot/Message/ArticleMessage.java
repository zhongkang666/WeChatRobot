package com.zk.WeChatRobot.Message;

import com.zk.WeChatRobot.Message.Media.Article;
import lombok.Builder;
import lombok.Data;

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
public class ArticleMessage extends MessageBase {
    private int articleCount;
    private List<Article> articles;
}
