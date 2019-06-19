package com.zk.WeChatRobot.Message.xml;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zk.WeChatRobot.Message.ArticleMessage;
import com.zk.WeChatRobot.Message.MessageBase;
import com.zk.WeChatRobot.Message.TextMessage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 这是一个javaBean转XML的工具类
 */
public class XStreamTransformer {

    private static final HashMap<Class<?>, XStream> CLASS_2_XSTREAM_INSTANCE = new HashMap<>();

    static {
        registerClass(TextMessage.class);
        registerClass(ArticleMessage.class);
    }

    public static <T> String toXML(Class<T> clazz,Object obj){
        return CLASS_2_XSTREAM_INSTANCE.get(clazz).toXML(obj);
    }

    public static <T> T fromXML(Class<T> clazz,String xml){
        T o = (T)CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(xml);
        return o;
    }

    public static <T> T fromXML(Class<T> clazz, InputStream is){
        T o = (T)CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(is);
        return o;
    }

    private static void registerClass(Class<?> clazz){
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(clazz);
        Class<?>[] innerClasses = getInnerClasses(clazz);
        if(innerClasses != null){
            xstream.processAnnotations(innerClasses);
        }
        CLASS_2_XSTREAM_INSTANCE.put(clazz,xstream);
    }

    private static Class<?>[] getInnerClasses(Class<?> clazz){
        Class<?>[] classes = clazz.getClasses();
        if(classes == null){
            return null;
        }
        ArrayList<Class<?>> result = new ArrayList<>();
        result.addAll(Arrays.asList(classes));
        for (Class<?> clz:classes){
            Class<?>[] innerClasses = getInnerClasses(clz);
            if(innerClasses == null){
                continue;
            }
            result.addAll(Arrays.asList(innerClasses));
        }
        return result.toArray(new Class[0]);
    }
}
