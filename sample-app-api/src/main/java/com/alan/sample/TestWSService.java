package com.alan.sample;

import javax.jws.WebService;

import com.alan.sample.model.MenuInfoVO;

@WebService
public interface TestWSService {
	
	public BaseResponse<MenuInfoVO> test(String name);
	
}
