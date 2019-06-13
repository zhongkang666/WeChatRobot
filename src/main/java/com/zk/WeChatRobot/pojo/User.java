package com.zk.WeChatRobot.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/06/13
*/
@Data
public class User implements Serializable {
    /* 用户ID*/
    private String userId;

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
    private BigDecimal latitude;

    /* 经度*/
    private BigDecimal longitude;

    /* 地理位置精度*/
    private BigDecimal precision;

    private static final long serialVersionUID = 1L;
}