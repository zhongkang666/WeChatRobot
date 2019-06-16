package com.zk.WeChatRobot.mapper;

import com.zk.WeChatRobot.pojo.User;
import com.zk.WeChatRobot.pojo.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Mybatis Generator 2019/06/16
 */
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String openId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String openId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    void updateUserOfPosition(@Param("user") User user);
}