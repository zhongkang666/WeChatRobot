package com.zk.WeChatRobot.MsgHandler.Impl;

import com.zk.WeChatRobot.Message.MessageBase;
import com.zk.WeChatRobot.Message.MessageFactory;
import com.zk.WeChatRobot.Message.type.MessageType;
import com.zk.WeChatRobot.MsgHandler.CommonMsgHandler;
import com.zk.WeChatRobot.mapper.UserMapper;
import com.zk.WeChatRobot.pojo.User;
import com.zk.WeChatRobot.pojo.UserExample;
import com.zk.WeChatRobot.utils.TuLingUtils;
import com.zk.WeChatRobot.utils.WeChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: CommonMsgHandlerImpl <br/>
 * Description: <br/>
 * date: 2019/6/14 21:48<br/>
 *
 * @author zk<br />
 * @since JDK 1.8
 */
@Service
public class CommonMsgHandlerImpl implements CommonMsgHandler {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String handleMessage(Map<String, String> map) {
        String content = map.get("Content");
        if(content != null&&content.equals("附近的人")){
            //查找该用户附近1000米范围内的人，并且按距离升序排序，并且返回距离该用户最近的人的用户名
            List<User> nearbyPeople = findNearbyPeople(map);
            int size = nearbyPeople.size();
            String result = null;
            if(size == 0){
                result = "在附近没有相关的人";
            }else{
                String nickname = nearbyPeople.get(0).getNickname();
                result = String.format("附近有%s个人，最近的是%s,距离您%s", String.valueOf(size), nickname,nearbyPeople.get(0).getDistance());
            }
            map.put("content",result);
        }else{
            //普通的消息处理设置图灵对话
            String answer = TuLingUtils.sendRequest(content, map.get("FromUserName").replace("_",""));
            map.put("content",answer);
        }
        MessageBase message = MessageFactory.createMessage(MessageType.TEXT, map, null);
        return message.toXML();
    }

    public List<User> findNearbyPeople(Map<String,String> map){
        UserExample userExample = new UserExample();
        String openId = map.get("FromUserName");
        final User user;
        User user1 = userMapper.selectByPrimaryKey(openId);
        if(user1 == null){
            User userInfo = WeChatUtils.getUserInfo(openId);
            user = userInfo;
        }else{
            user = user1;
        }
        //查询出除该用户外的所有用户
        userExample.createCriteria().andOpenIdNotEqualTo(openId).andIsSubscribeEqualTo(true);
        List<User> nearbyPeoples = userMapper.selectByExample(userExample);
        //过滤，过滤的条件是距离<=1000 M，再排序
        List<User> collect = nearbyPeoples.stream().filter(people -> {
            double distance = WeChatUtils.getDistance(user.getLatitude(), user.getLongitude(), people.getLatitude(), people.getLongitude());
            boolean flag = distance <= 1000d;
            //计算距离
            if (flag) {
                people.setDistance(distance);
            }
            return flag;
        }).sorted((o1, o2) -> (int) Math.ceil(o1.getDistance() - o2.getDistance())).collect(Collectors.toList());
        return collect;
    }
}
