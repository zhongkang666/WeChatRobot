package com.zk.WeChatRobot.controller;

import com.alibaba.fastjson.JSON;
import com.zk.WeChatRobot.MsgHandler.MessageHandler;
import com.zk.WeChatRobot.config.WxMpProperties;
import com.zk.WeChatRobot.mapper.TempMaterialMapper;
import com.zk.WeChatRobot.pojo.TempMaterial;
import com.zk.WeChatRobot.router.WeChatRouter;
import com.zk.WeChatRobot.utils.HttpClientUtils;
import com.zk.WeChatRobot.utils.TuLingUtils;
import com.zk.WeChatRobot.utils.WeChatUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Map;

@Slf4j
@RestController
public class WeChatController {

    @Autowired
    private WxMpProperties properties;

    @Autowired
    private WeChatRouter router;

    @Autowired
    private TempMaterialMapper mapper;

    @PostMapping(value = "/robotAnswer",produces = "application/xml; charset=UTF-8")
    public String getAnswer(HttpServletRequest request){
        HttpSession session = request.getSession();
        //log.info(session.getAttribute("username").toString());
        log.info("进入post接口");
        Map<String, String> requestMap = TuLingUtils.getRequestMap(request);
        session.setAttribute("username",requestMap.get("FromUserName"));
        MessageHandler route = router.route(requestMap);
        String message = route.handleMessage(requestMap);
        log.info(message);
        return message;
    }

    @GetMapping(value = "/robotAnswer",produces = "text/plain;charset=utf-8")
    public String check(String timestamp, String nonce, String echostr, String signature){
        log.info("进入微信验证接口,%s,%s,%s,%s",timestamp,nonce,echostr,signature);
        if(TuLingUtils.sha1Result(timestamp,nonce,properties.getToken(),signature)){
           return echostr;
        }else{
            return "未知请求";
        }
    }

    @PostMapping(value = "/uploadTempMaterial")
    public String uploadTempMaterial(MultipartFile file){
        //首先获取文件的文件类型
        String mimeType = file.getContentType();
        String url = String.format(properties.getUploadApi(), WeChatUtils.getAccessToken(), mimeType);
        CommonsMultipartFile cf= (CommonsMultipartFile)file;
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        File f = fi.getStoreLocation();
        String result = HttpClientUtils.uploadFile(url, f);
        TempMaterial tempMaterial = JSON.parseObject(result, TempMaterial.class);
        mapper.insert(tempMaterial);
        return "success";
    }

    @GetMapping("test")
    @ResponseBody
    public String test(){
        String accessToken = WeChatUtils.getAccessToken();
        System.out.println(accessToken);
        return accessToken;
    }
}
