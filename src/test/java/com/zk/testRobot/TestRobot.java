package com.zk.testRobot;

import com.zk.WeChatRobot.utils.TuLingUtils;

import java.io.IOException;

public class TestRobot {


    public static void main(String[] args) throws IOException {
        String s = TuLingUtils.sendRequest("你好", "owGjY1B4IObud8kCac7ml4D6JNw");
        System.out.println(s);
    }


}
