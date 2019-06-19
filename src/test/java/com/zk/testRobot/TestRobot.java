package com.zk.testRobot;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zk.WeChatRobot.Message.ArticleMessage;
import com.zk.WeChatRobot.Message.Media.Article;
import com.zk.WeChatRobot.Message.MessageBase;
import com.zk.WeChatRobot.Message.TextMessage;
import com.zk.WeChatRobot.Message.type.MessageType;
import com.zk.WeChatRobot.Message.xml.XStreamTransformer;
import com.zk.WeChatRobot.template.WxMpTemplateData;
import com.zk.WeChatRobot.template.WxTemplateMessage;
import com.zk.WeChatRobot.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TestRobot {

    private String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=%s";

    private String getTemplates = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s";

    private String sendTemplate = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    private String access_token = "22_WCdMlgDXl1JCzr3yviIM9dO1zkpTnZoOPbFDK6G9jA35nl0-mlkmsvLwOsnPV9kLImiKYwSPwHXGQ89TKLqJfddB8PyEvRsczpBCItsL9q035GK2l_8S6lX6RtcIFVjAIAVSI";
    @Test
    public void test() throws IOException {

    }

    @Test
    public void testSendMessageTemplate(){
//        ArticleMessage textMessage = new ArticleMessage();
//        textMessage.setFromUserName("test");
//        textMessage.setToUserName("touser");
//        textMessage.setMsgType(MessageType.TEXT);
//        textMessage.setCreateTime(1000L);
//        textMessage.setArticleCount(1);
//        ArrayList<Article> articles = new ArrayList<>();
//        Article article = new Article();
//        article.setTitle("testTitle");
//        article.setDescription("are you ok?");
//        article.setUrl("localhost");
//        article.setPicUrl("haha");
//        articles.add(article);
//        textMessage.setArticles(articles);
//        System.out.println(textMessage.toXML());
        ArticleMessage textMessage = XStreamTransformer.fromXML(ArticleMessage.class, "<xml>\n" +
                "  <ToUserName>&lt;![CDATA[touser]]&gt;</ToUserName>\n" +
                "  <FromUserName>&lt;![CDATA[test]]&gt;</FromUserName>\n" +
                "  <CreateTime>1000</CreateTime>\n" +
                "  <MsgType>&lt;![CDATA[text]]&gt;</MsgType>\n" +
                "  <ArticleCount>1</ArticleCount>\n" +
                "  <Articles>\n" +
                "    <item>\n" +
                "      <Title>testTitle</Title>\n" +
                "      <Description>are you ok?</Description>\n" +
                "      <PicUrl>haha</PicUrl>\n" +
                "      <Url>localhost</Url>\n" +
                "    </item>\n" +
                "  </Articles>\n" +
                "</xml>\n" );
        System.out.println(textMessage);
    }

    public void sendWxText(String openId,String template_id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        WxMpTemplateData result = new WxMpTemplateData("result", "您已提交领奖申请", null);
        WxMpTemplateData withdrawMoney = new WxMpTemplateData("withdrawMoney", simpleDateFormat.format(new Date()), null);
        WxMpTemplateData cardInfo = new WxMpTemplateData("cardInfo", "xx银行(尾号xxxx)", null);
        WxMpTemplateData arrivedTime = new WxMpTemplateData("arrivedTime", "2019-06-18 22:00", null);
        WxMpTemplateData remark = new WxMpTemplateData("remark", "谢谢惠顾", null);
        WxTemplateMessage wxTemplateMessage = new WxTemplateMessage().builder().touser(openId).template_id(template_id).build()
                .addData(result).addData(withdrawMoney).addData(cardInfo).addData(arrivedTime).addData(remark);
        String s = HttpClientUtils.sendPostRequest(String.format(sendTemplate, access_token), wxTemplateMessage.toString());
        System.out.println(s);
    }
}
