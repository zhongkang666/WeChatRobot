package com.zk.WeChatRobot.controller;

import com.zk.WeChatRobot.MsgHandler.MessageHandler;
import com.zk.WeChatRobot.router.WeChatRouter;
import com.zk.WeChatRobot.utils.TuLingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class WeChatController {

    @Value("${token}")
    private String token;

    @Autowired
    private WeChatRouter router;

    @PostMapping(value = "/robotAnswer")
    public String getAnswer(HttpServletRequest request){
        System.out.println("进入post请求");
        Map<String, String> requestMap = TuLingUtils.getRequestMap(request);
        MessageHandler route = router.route(requestMap);
        String message = route.handleMessage(requestMap);
//        String event = requestMap.get("Event");
//        if(event != null){
//            if(event.equals("subscribe")){
//                requestMap.put("Content","欢迎关注");
//            }
//        }else{
//            String answer = TuLingUtils.sendRequest(requestMap.get("Content"), requestMap.get("FromUserName").replace("_",""));
//            requestMap.put("Content",answer);
//        }
        //String textMessage = TuLingUtils.getTextMessage(requestMap);
        return message;
    }

    @GetMapping(value = "/robotAnswer",produces = "text/plain;charset=utf-8")
    public String check(String timestamp, String nonce, String echostr, String signature){
        System.out.println(timestamp+":"+nonce+":"+echostr+":"+signature);
        if(TuLingUtils.sha1Result(timestamp,nonce,token,signature)){
           return echostr;
        }else{
            return "未知请求";
        }
    }
}
