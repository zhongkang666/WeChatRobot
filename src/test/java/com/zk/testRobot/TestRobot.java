package com.zk.testRobot;

import com.alibaba.fastjson.JSONObject;
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

    private String access_token = "22_7-kGsVK03iaVzSssdoHUktHlINVde-7_Pd1EFl3HephcmEUYEHAQRyb0itlXGTj0ME_vhznnjaKnIJZut0UYvlyUkDcMSMMiKEQbgaG9lPxrIL8s-bEb1_FEjE_22RpUeeU_CEOAL4SHQdmPBNDjAFATFV";
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
        String s = HttpClientUtils.sendPostRequest(String.format(url,access_token), params.toString());
        System.out.println(s);
    }

    @Test
    public void testSendMessageTemplate(){
        sendWxText("owGjY1B4IObud8kCac7m_l4D6JNw", "4W_YmgByCgyPizo2raCT0e3gTwGAVKQ_i38HdLJFR1Q");
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
