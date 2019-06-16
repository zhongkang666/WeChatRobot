package com.zk.WeChatRobot.utils;

import com.alibaba.fastjson.JSON;
import com.zk.WeChatRobot.pojo.User;

import java.util.Date;


public class WeChatUtils {

    public static String access_token;

    private static final double EARTH_RADIUS = 6378.137;

    private static final String userInfoApi = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    public static User getUserInfo(String openId){
        String userInfo = HttpClientUtils.sendGetRequest(String.format(userInfoApi, WeChatUtils.access_token, openId));
        User user = JSON.parseObject(userInfo, User.class);
        user.setSubscribeTime(new Date());
        return user;
    }
}
