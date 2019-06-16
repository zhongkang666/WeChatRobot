package com.zk.WeChatRobot.mapper;

import com.zk.WeChatRobot.pojo.TempMaterial;
import com.zk.WeChatRobot.pojo.TempMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* Created by Mybatis Generator 2019/06/16
*/
public interface TempMaterialMapper {
    long countByExample(TempMaterialExample example);

    int deleteByExample(TempMaterialExample example);

    int deleteByPrimaryKey(String mediaId);

    int insert(TempMaterial record);

    int insertSelective(TempMaterial record);

    List<TempMaterial> selectByExample(TempMaterialExample example);

    TempMaterial selectByPrimaryKey(String mediaId);

    int updateByExampleSelective(@Param("record") TempMaterial record, @Param("example") TempMaterialExample example);

    int updateByExample(@Param("record") TempMaterial record, @Param("example") TempMaterialExample example);

    int updateByPrimaryKeySelective(TempMaterial record);

    int updateByPrimaryKey(TempMaterial record);
}