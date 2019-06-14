package com.zk.WeChatRobot.Message;

import com.zk.WeChatRobot.Message.Media.Article;
import lombok.Builder;
import lombok.Data;

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
public class ArticleMessage extends MessageBase {
    private int articleCount;
    private List<Article> articles;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[" + this.getToUserName() + "]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[" + this.getFromUserName() + "]]></FromUserName>");
        sb.append("<CreateTime>" +this.getCreateTime() + "</CreateTime>");
        sb.append("<MsgType>"+this.getMsgType()+"</MsgType>");
        sb.append("<ArticleCount>"+this.articleCount+"</ArticleCount>");
        sb.append("<Articles>");
        for (Article article:articles){
            sb.append("<item>" +
                    "      <Title>"+article.getTitle()+"</Title>" +
                    "      <Description>"+article.getDescription()+"</Description>" +
                    "      <PicUrl>"+article.getPicUrl()+"</PicUrl>" +
                    "      <Url>"+article.getUrl()+"</Url>" +
                    "    </item>");
        }
        sb.append("</Articles>");
        sb.append("</xml>");
        return sb.toString();
    }
}
