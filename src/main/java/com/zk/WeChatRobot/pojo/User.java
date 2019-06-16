package com.zk.WeChatRobot.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/06/16
*/
@Data
public class User implements Serializable {
    /* */
    private String openId;

    /* 用户名称*/
    private String nickname;

    /* */
    private String headimgurl;

    /* 用户性别*/
    private Byte sex;

    /* 用户所在城市*/
    private String city;

    /* 省份*/
    private String province;

    /* 国家*/
    private String country;

    /* 用户所用语言*/
    private String language;

    /* 用户关注时间*/
    private Date subscribeTime;

    /* 用户所在地理位置的纬度*/
    private Double latitude;

    /* 经度*/
    private Double longitude;

    /* 地理位置精度*/
    private Double accuracy;

    /* */
    private Boolean isSubscribe;

    private Double distance;

    private static final long serialVersionUID = 1L;
}