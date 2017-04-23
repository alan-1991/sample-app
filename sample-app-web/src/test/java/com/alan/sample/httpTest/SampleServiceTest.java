package com.alan.sample.httpTest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.alan.sample.BaseResponse;
import com.alan.sample.SampleService;
import com.alan.sample.model.MenuInfoVO;

/**要运行此service,一定要先运行服务器
 * @author alan
 *
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test.xml"})
public class SampleServiceTest {

	@Autowired
	private SampleService sampleService;
	
	@Test
	public void test1() throws Exception{
		BaseResponse<List<MenuInfoVO>> result = sampleService.findUserList("alan", 1, 3);
		
	}
}
