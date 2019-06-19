package com.zk.WeChatRobot.Message.xml;

import com.thoughtworks.xstream.converters.enums.EnumSingleValueConverter;

public class XStreamMsgTypeConverter extends EnumSingleValueConverter {

    private Class<? extends Enum> enumType;

    public XStreamMsgTypeConverter(Class<? extends Enum> type) {
        super(type);
        enumType = type;
    }

    @Override
    public String toString(Object obj) {
        return "<![CDATA["+obj.toString()+ "]]>";
    }

    @Override
    public Object fromString(String str) {
        for(Enum<?> item : enumType.getEnumConstants()){
            if(str.contains(item.toString())){
                return item;
            }
        }
        return null;
    }
}
