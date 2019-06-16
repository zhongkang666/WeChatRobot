package com.zk.testRobot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zk.WeChatRobot.pojo.TempMaterial;
import com.zk.WeChatRobot.utils.HttpClientUtils;
import com.zk.WeChatRobot.utils.WeChatUtils;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class TestRobot {

    private String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=22_cwhH0yjPn-6ONn2phXLTB_qkeMTIJW4L4bF3MyhKCIJ988KWi6vfaIW0j2b-HhbkXQkZUKOlMyJdcqQ72uts77RdCVX281zco30Sh_SAumhd7UKnXJlouqwZ1pWLi9EHYPAWZpiKVrpj5q73VKKfAAARGG";

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
        String s = HttpClientUtils.sendPostRequest(url, stringEntity);
        System.out.println(s);
    }

}
