package com.zk.WeChatRobot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class HttpClientUtils {

    public static String sendGetRequest(String url){
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        setRequestConfig(httpGet);
        CloseableHttpResponse response = null;
        try {
            response = build.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String sendPostRequest(String url, String entity){
        StringEntity stringEntity = new StringEntity(entity, Charset.forName("UTF-8"));
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        setRequestConfig(httpPost);
        httpPost.setEntity(stringEntity);
        String result = null;
        try {
            HttpResponse response = build.execute(httpPost);
            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK){
                result = "";
                log.warn("请求出错，url:{},状态码：{}",url,statusCode);
            }else{
                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //上传文件方法
    public static String uploadFile(String url, File file){
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        setRequestConfig(post);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody("file",file);
        HttpEntity httpEntity = multipartEntityBuilder.build();
        post.setEntity(httpEntity);
        try {
            CloseableHttpResponse response = build.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK){
                String result = EntityUtils.toString(response.getEntity(),"UTF-8");
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("上传文件异常，异常信息为：{}",e.getMessage());
        }
        return null;
    }

    private static void setRequestConfig(HttpRequestBase request){
        request.setHeader("Content-Type", "application/json; charset=utf-8");
        request.setHeader("Connection", "Keep-Alive");
        request.setHeader("Connection", "Close");
    }
}
