package com.zk.testRobot;

import com.alibaba.fastjson.JSONObject;
import com.zk.WeChatRobot.template.BaseTemplate;
import com.zk.WeChatRobot.template.ColorModel;
import com.zk.WeChatRobot.template.RegisterData;
import com.zk.WeChatRobot.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
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
        JSONObject params = new JSONObject();
        JSONObject image = new JSONObject();
        JSONObject filter = new JSONObject();
        filter.put("is_to_all",true);
        image.put("media_id","9kY5LWUxsx7PEpBt7ANhqFCYDy4f_T9ef5GQVXm5R_2l9VmwHJlB9zjjtami18wr");
        params.put("filter",filter);
        params.put("image",image);
        params.put("msgtype","image");
        StringEntity stringEntity = new StringEntity(params.toString(), Charset.forName("UTF-8"));
        String s = HttpClientUtils.sendPostRequest(String.format(url,access_token), stringEntity);
        System.out.println(s);
    }

    @Test
    public void testSendMessageTemplate(){
        int i = sendWxText("owGjY1B4IObud8kCac7m_l4D6JNw", null, "haha", "xiexiehuigu");
        System.out.println(i);
    }

    public int sendWxText(String openId,String toUrl, String first, String remarke) {
        int ret=0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ColorModel result = new ColorModel(first+"\n", "#173177");
        ColorModel withdrawMoney = new ColorModel("36", "#000000");
        ColorModel withdrawTime = new ColorModel(sdf.format(new Date()), "#000000");
        ColorModel cardInfo = new ColorModel("123456789", "#000000");
        ColorModel arrivedTime = new ColorModel("2019-06-18 00:00", "#000000");
        ColorModel remark = new ColorModel("1111111", "#000000");
        RegisterData data= new RegisterData(result, withdrawMoney, withdrawTime, cardInfo,arrivedTime,remark);
        BaseTemplate baseTemplate=new BaseTemplate(openId,"4W_YmgByCgyPizo2raCT0e3gTwGAVKQ_i38HdLJFR1Q",toUrl,"#FF0000",data);
        String postData = JSONObject.toJSONString(baseTemplate);
        JSONObject json=JSONObject.parseObject(postData);

        StringEntity stringEntity = new StringEntity(json.toString(), Charset.forName("UTF-8"));
        String res = HttpClientUtils.sendPostRequest(String.format(sendTemplate,access_token), stringEntity);
        JSONObject resJson = JSONObject.parseObject(res);
        if("0".equals(resJson.getString("errcode")))
        {
            log.info("发送模板消息成功！");
            ret=1;
        }
        else if("40003".equals(resJson.getString("errcode")))
            return 40003;
        log.info("微信模板消息返回："+res);
        return ret;
    }
}
