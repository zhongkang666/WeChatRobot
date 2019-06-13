package com.zk.WeChatRobot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;

public class TuLingUtils {

    private static final String URL = "http://www.tuling123.com/openapi/api/v2";

    private static final String apiKey = "3050f3ae77f74827aad7ea78283ca310";

    public static String sendRequest(String content,String userId){
        JSONObject jsonObject = formatParams(content, userId,apiKey);
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(URL);

        httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
        httpPost.setHeader("Connection", "Close");

        StringEntity entity = new StringEntity(jsonObject.toString(), Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        String result = null;
        try {
            HttpResponse response = build.execute(httpPost);
            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            result = "";
            if(statusCode != HttpStatus.SC_OK){
                result = "累了，不聊了";
            }else{
                String s = EntityUtils.toString(response.getEntity(),"UTF-8");
                System.out.println(s);
                JSONObject parse = JSON.parseObject(s);
                JSONArray results = parse.getJSONArray("results");
                StringBuffer sb = new StringBuffer();
                for(int i = 0;i < results.size();i++){
                    sb.append(results.getJSONObject(i).getJSONObject("values").getString("text"));
                }
                result = sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static JSONObject formatParams(String content, String userid, String apiKey){
        JSONObject params=new JSONObject();
        params.put("reqType",0);//输入类型:0-文本(默认)、1-图片、2-音频
        JSONObject perception=new JSONObject();
        JSONObject text=new JSONObject();
        text.put("text",content);
        perception.put("inputText", text);
        params.put("perception",perception);//输入信息
        JSONObject userInfo=new JSONObject();
        userInfo.put("apiKey",apiKey);
        userInfo.put("userId",userid);
        params.put("userInfo",userInfo);//	用户参数
        return params;
    }

    public static boolean sha1Result(String timestamp, String nonce, String token, String signature){
        String result = null;
        if(!(timestamp == null ||nonce == null ||token == null)){
            String[] keys = new String[3];
            keys[0] = timestamp;
            keys[1] = token;
            keys[2] = nonce;
            Arrays.sort(keys);
            StringBuffer sb = new StringBuffer();
            for(String key:keys){
                sb.append(key);
            }
            try {
                result = TuLingUtils.sha1(sb.toString());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return result != null&&result.equals(signature);
    }

    private static String sha1(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        byte[] b = data.getBytes();
        md.update(b);
        byte[] b2 = md.digest();
        int len = b2.length;
        String str = "0123456789abcdef";
        char[] ch = str.toCharArray();
        char[] chs = new char[len*2];
        for(int i=0,k=0;i<len;i++) {
            byte b3 = b2[i];
            chs[k++] = ch[b3 >>> 4 & 0xf];
            chs[k++] = ch[b3 & 0xf];
        }
        return new String(chs);
    }

    public static Map<String, String> getRequestMap(HttpServletRequest request){
        Document read = null;
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            SAXReader reader = new SAXReader();
            read = reader.read(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element rootElement = read.getRootElement();
        List<Element> elements = rootElement.elements();
        Map<String, String> map = new HashMap<String, String>();
        for (Element element:elements) {
            map.put(element.getName(),element.getText());
        }
        if(inputStream != null){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static String getTextMessage(Map<String,String> map){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[" + map.get("FromUserName") + "]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[" + map.get("ToUserName")  + "]]></FromUserName>");
        sb.append("<CreateTime>" +new Date().getTime() + "</CreateTime>");
        sb.append("<MsgType><![CDATA[" +"text"+ "]]></MsgType>");
        sb.append("<Content>" +map.get("Content")+ "</Content>");
        sb.append("</xml>");
        return sb.toString();
    }
}
