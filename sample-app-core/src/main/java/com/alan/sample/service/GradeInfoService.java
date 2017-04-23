package com.alan.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alan.sample.dao.GradeInfoDAO;

@Repository
public class GradeInfoService {

	@Autowired
	private GradeInfoDAO gradeInfoDao;
}
