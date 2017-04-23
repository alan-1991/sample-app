package com.alan.sample.dao;

import com.istock.base.ibatis.model.Page;
import com.alan.sample.model.GradeInfo;
import com.alan.sample.model.GradeInfoExample;
import java.util.List;

public interface GradeInfoDAO {
    int countByExample(GradeInfoExample example);

    int deleteByExample(GradeInfoExample example);

    int deleteByPrimaryKey(Integer id);

    Integer insert(GradeInfo record);

    Integer insertSelective(GradeInfo record);

    List<GradeInfo> selectByExample(GradeInfoExample example);

    GradeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(GradeInfo record, GradeInfoExample example);

    int updateByExample(GradeInfo record, GradeInfoExample example);

    int updateByPrimaryKeySelective(GradeInfo record);

    int updateByPrimaryKey(GradeInfo record);

    List<GradeInfo> selectByPage(GradeInfoExample example, Page page);
}