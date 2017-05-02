package com.alan.sample.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alan.sample.dao.MenuInfoDAO;
import com.alan.sample.model.MenuInfo;
import com.alan.sample.model.MenuInfoExample;
import com.istock.base.ibatis.model.Page;

@Repository
public class MenuInfoService {

	@Autowired
	private MenuInfoDAO menuInfoDao;
	
	@Autowired
	private ExtMenuInfoDAO menuInfoDao;
	
	public MenuInfo findMenuInfoById(Integer id){
		return menuInfoDao.selectByPrimaryKey(id);
	}
	
	public List<Map<String,Object>> groupByTest(String name,Page page){
		return 
	}
	
	public List<MenuInfo> findByName(String name , Page page){
		MenuInfoExample example = new MenuInfoExample();
		MenuInfoExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(name)){
			criteria.andNameLike("%"+name+"%");
		}
		return menuInfoDao.selectByPage(example, page);
	}
	
	public Integer saveMenuInfo(MenuInfo menuInfo){
		if(menuInfo != null){
			if(menuInfo.getId() != null && menuInfo.getId()>0){
				//update
				return menuInfoDao.updateByPrimaryKeySelective(menuInfo);
			}else{
				//insert
				return menuInfoDao.insertSelective(menuInfo);
			}
		}
		return 0;
	}
	
	public Integer deleteMenuInfo(Integer id){
		if(id != null && id>0){
			return menuInfoDao.deleteByPrimaryKey(id);
		}
		return 0;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//错误的示例
	//parent插入 ，child遇到问题后回滚
	//xxxservice.xxx()，事务是生效的
	//在service 内部调用service方法是没有意义的
	//父事务提交，子事务回滚
	@Transactional
	public void parentTransaction(){
		MenuInfo menuInfo = new MenuInfo();
		menuInfo.setName("parent");
		menuInfo.setMemo("alan parent");
		
		menuInfoDao.insertSelective(menuInfo);
		
		try {
			childTransaction();
		} catch (Exception e) {
			logger.info("parent catch child exceptionin------" ,e);
		}
		childTransaction();
		
	}
	//子事务
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void childTransaction(){
		MenuInfo menuInfo = new MenuInfo();
		menuInfo.setName("child");
		menuInfo.setMemo("alan child");
		
		menuInfoDao.insertSelective(menuInfo);
		
		//throw new RuntimeException("child exception ===========");
	}
	
}
