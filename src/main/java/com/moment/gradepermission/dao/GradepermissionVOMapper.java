package com.moment.gradepermission.dao;

import com.moment.gradepermission.domain.GradepermissionVO;
import com.moment.gradepermission.domain.GradepermissionVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GradepermissionVOMapper {
    int countByExample(GradepermissionVOExample example);

    int deleteByExample(GradepermissionVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GradepermissionVO record);

    int insertSelective(GradepermissionVO record);

    List<GradepermissionVO> selectByExample(GradepermissionVOExample example);

    GradepermissionVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GradepermissionVO record, @Param("example") GradepermissionVOExample example);

    int updateByExample(@Param("record") GradepermissionVO record, @Param("example") GradepermissionVOExample example);

    int updateByPrimaryKeySelective(GradepermissionVO record);

    int updateByPrimaryKey(GradepermissionVO record);
}