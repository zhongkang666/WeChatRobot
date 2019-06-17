package com.zk.WeChatRobot.template;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Administrator on 2017/8/1.
 */
@Data
@AllArgsConstructor
public class RegisterData {
    private ColorModel result;//模板开始前描述
    private ColorModel withdrawMoney;//关键词1
    private ColorModel withdrawTime;//关键词2
	private ColorModel cardInfo;//关键词2
	private ColorModel arrivedTime;//关键词2
    private ColorModel remark;//模板结束描述
}
