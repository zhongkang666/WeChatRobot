package com.zk.WeChatRobot.MsgHandler.Impl;

import com.zk.WeChatRobot.Message.MessageBase;
import com.zk.WeChatRobot.Message.MessageFactory;
import com.zk.WeChatRobot.Message.type.EventType;
import com.zk.WeChatRobot.Message.type.EventTypeHanlder;
import com.zk.WeChatRobot.Message.type.MessageType;
import com.zk.WeChatRobot.MsgHandler.EventMessageHandler;
import com.zk.WeChatRobot.MsgHandler.MessageHandlerFactory;
import com.zk.WeChatRobot.mapper.UserMapper;
import com.zk.WeChatRobot.pojo.User;
import com.zk.WeChatRobot.pojo.UserExample;
import com.zk.WeChatRobot.utils.WeChatUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: EventMessageHandlerImpl <br/>
 * Description: <br/>
 * date: 2019/6/13 22:15<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Service
public class EventMessageHandlerFactoryImpl implements MessageHandlerFactory, BeanPostProcessor {

    private Map<String, EventMessageHandler> handlerMap = new HashMap<String, EventMessageHandler>();

    @Override
    public String handleMessage(Map<String, String> map) {
        EventMessageHandler handler = handlerMap.get(map.get("Event"));
        return handler.process(map);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        EventTypeHanlder annotation = bean.getClass().getAnnotation(EventTypeHanlder.class);
        if (annotation != null && bean instanceof EventMessageHandler) {
            handlerMap.put(annotation.value().name(), (EventMessageHandler) bean);
        }
        return null;
    }
}
