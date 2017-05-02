package com.alan.sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alan.sample.BaseResponse;
import com.alan.sample.TestWSService;
import com.alan.sample.model.IFStateEnum;
import com.alan.sample.model.MenuInfoVO;
import com.alan.sample.model.RetCodeEnum;

public class TestWSServiceImpl implements TestWSService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public BaseResponse<MenuInfoVO> test(String name) {
		logger.info("========test============");
		MenuInfoVO result = new MenuInfoVO();
		result.setName("Alan");
		
		return new BaseResponse<MenuInfoVO>(IFStateEnum.SUCCESS , RetCodeEnum.SUCCESS , result , "成功");
	}

}
