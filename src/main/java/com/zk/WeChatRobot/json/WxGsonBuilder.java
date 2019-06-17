package com.zk.WeChatRobot.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zk.WeChatRobot.json.adapter.WxTemplateMessageAdapter;
import com.zk.WeChatRobot.template.WxTemplateMessage;

/**
 * ClassName: WxGsonBuilder <br/>
 * Description: <br/>
 * date: 2019/6/17 20:54<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
public class WxGsonBuilder {

    private static final GsonBuilder INSTANCE = new GsonBuilder();

    static {
        INSTANCE.registerTypeAdapter(WxTemplateMessage.class,new WxTemplateMessageAdapter());
    }

    public static Gson create(){
        return INSTANCE.create();
    }
}
