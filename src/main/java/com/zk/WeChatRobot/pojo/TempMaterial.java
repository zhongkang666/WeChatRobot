package com.zk.WeChatRobot.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/06/16
*/
@Data
public class TempMaterial implements Serializable {
    /* */
    private String mediaId;

    /* */
    private String type;

    /* */
    private Date createdAt;

    private static final long serialVersionUID = 1L;
}