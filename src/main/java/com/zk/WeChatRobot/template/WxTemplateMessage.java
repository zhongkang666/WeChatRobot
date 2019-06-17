package com.zk.WeChatRobot.template;

import com.zk.WeChatRobot.json.WxGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * LDY ：2017.10.27
 * 两个关键字的模板
 * Created by Administrator on 2017/8/1.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WxTemplateMessage {
    @NotBlank
    private String touser;      //openId
    @NotBlank
    private String template_id; //模版消息Id
    private String url;         //模版点击后链接的地方
    private String topcolor;    //模版头颜色
    private List<WxMpTemplateData> data;

    public WxTemplateMessage addData(WxMpTemplateData datum) {
        if (this.data == null) {
            this.data = new ArrayList<WxMpTemplateData>();
        }
        this.data.add(datum);
        return this;
    }

    @Override
    public String toString() {
        return WxGsonBuilder.create().toJson(this);
    }
}
