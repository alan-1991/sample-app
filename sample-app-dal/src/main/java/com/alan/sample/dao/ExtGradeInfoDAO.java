package com.alan.sample.dao;

import java.util.List;

import com.istock.base.ibatis.model.Page;
import com.alan.sample.model.GradeInfo;

public interface ExtGradeInfoDAO {

	public List<GradeInfo> findExtGradeInfo(Long studentId , Page page);
}
