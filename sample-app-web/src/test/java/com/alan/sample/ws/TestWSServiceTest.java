package com.alan.sample.ws;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.alan.sample.BaseResponse;
import com.alan.sample.TestWSService;
import com.alan.sample.model.MenuInfoVO;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-ws.xml"})
public class TestWSServiceTest {
	
	
	@Autowired
	private TestWSService service;
	
	@Test
	public void test1(){
		BaseResponse<MenuInfoVO> response = service.test("Alan client");
		
		System.out.println(response.getResult());
		System.out.println(response.getMessage());
		
	}

}
