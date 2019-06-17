package com.zk.WeChatRobot.json.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.zk.WeChatRobot.template.WxMpTemplateData;
import com.zk.WeChatRobot.template.WxTemplateMessage;

import java.lang.reflect.Type;

/**
 * ClassName: WxTemplateMessageAdapter <br/>
 * Description: <br/>
 * date: 2019/6/17 20:58<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
public class WxTemplateMessageAdapter implements JsonSerializer<WxTemplateMessage> {

    @Override
    public JsonElement serialize(WxTemplateMessage wxTemplateMessage, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject messageObject = new JsonObject();
        messageObject.addProperty("touser",wxTemplateMessage.getTouser());
        messageObject.addProperty("template_id",wxTemplateMessage.getTemplate_id());
        if(wxTemplateMessage.getUrl() != null){
            messageObject.addProperty("url",wxTemplateMessage.getUrl());
        }
        if(wxTemplateMessage.getTopcolor() != null){
            messageObject.addProperty("topcolor",wxTemplateMessage.getTopcolor());
        }
        JsonObject datas = new JsonObject();
        for(WxMpTemplateData data:wxTemplateMessage.getData()){
            JsonObject objectData = new JsonObject();
            objectData.addProperty("value",data.getValue());
            if(data.getColor() != null&& !data.getColor().equals("")){
                objectData.addProperty("color",data.getColor());
            }
            datas.add(data.getName(),objectData);
        }
        messageObject.add("data", datas);
        return messageObject;
    }
}
