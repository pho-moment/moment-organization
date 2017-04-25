package com.moment.gradegroup.dao;

import com.moment.gradegroup.domain.GradegroupVO;
import com.moment.gradegroup.domain.GradegroupVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GradegroupVOMapper {
    int countByExample(GradegroupVOExample example);

    int deleteByExample(GradegroupVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GradegroupVO record);

    int insertSelective(GradegroupVO record);

    List<GradegroupVO> selectByExample(GradegroupVOExample example);

    GradegroupVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GradegroupVO record, @Param("example") GradegroupVOExample example);

    int updateByExample(@Param("record") GradegroupVO record, @Param("example") GradegroupVOExample example);

    int updateByPrimaryKeySelective(GradegroupVO record);

    int updateByPrimaryKey(GradegroupVO record);
}