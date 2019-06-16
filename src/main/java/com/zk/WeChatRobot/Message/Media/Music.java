package com.zk.WeChatRobot.Message.Media;

import lombok.Builder;
import lombok.Data;

/**
 * ClassName: Music <br/>
 * Description: <br/>
 * date: 2019/6/13 21:50<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Data
@Builder
public class Music implements Media{
    private String title;
    private String description;
    private String hQMusicUrl;
    private String thumbMediaId;
}
