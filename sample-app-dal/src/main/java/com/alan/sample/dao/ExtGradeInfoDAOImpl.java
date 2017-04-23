package com.alan.sample.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.istock.base.ibatis.IbatisDaoAnnotation;
import com.istock.base.ibatis.model.Page;
import com.alan.sample.model.GradeInfo;

@Repository
public class ExtGradeInfoDAOImpl extends IbatisDaoAnnotation implements ExtGradeInfoDAO {

	@Override
	public List<GradeInfo> findExtGradeInfo(Long studentId, Page page) {
		Map<String , Object> paramMap = new HashMap<String , Object>();
		paramMap.put("studentId", studentId);
		return this.searchListPageMyObject("TB_GRADE_INFO.findExtGradeInfo", paramMap, page);
	}

}
