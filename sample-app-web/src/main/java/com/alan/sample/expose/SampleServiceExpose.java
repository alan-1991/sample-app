/**
 * 
 */
package com.alan.sample.expose;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.istock.base.ibatis.model.Page;
import com.alan.sample.BaseResponse;
import com.alan.sample.SampleService;
import com.alan.sample.model.AddressInfo;
import com.alan.sample.model.IFStateEnum;
import com.alan.sample.model.MenuInfo;
import com.alan.sample.model.MenuInfoQuery;
import com.alan.sample.model.MenuInfoVO;
import com.alan.sample.model.RetCodeEnum;
import com.alan.sample.service.MenuInfoService;

/**
 * @author alan
 *
 */
@Controller
@RequestMapping("sample")
public class SampleServiceExpose implements SampleService {

	@Autowired
	private MenuInfoService menuInfoService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	@RequestMapping("findUserList")
	public @ResponseBody BaseResponse<List<MenuInfoVO>> findUserList(String name, Integer pageNo, Integer pageSize) {
		logger.info("receive the parameter name:{}" , name);
		List<MenuInfo> menuList = menuInfoService.findByName(name, new Page());
		
		List<MenuInfoVO> result = new ArrayList<MenuInfoVO>();
		if(menuList != null){
			for(MenuInfo menuInfo : menuList){
				MenuInfoVO menu = new MenuInfoVO();
				BeanUtils.copyProperties(menuInfo, menu);
				
				List<AddressInfo> addressList = new ArrayList<AddressInfo>();
				AddressInfo addressInfo = new AddressInfo();
				addressInfo.setCity("来个中文");
				addressInfo.setLocation("shanghai");
				addressList.add(addressInfo);
				menu.setAddressList(addressList);
				result.add(menu);
			}
		}
		return new BaseResponse<List<MenuInfoVO>>(IFStateEnum.SUCCESS , RetCodeEnum.SUCCESS , result , "查询成功");
	}

	@Override
	@RequestMapping("findUserListByQuery")
	public @ResponseBody BaseResponse<List<MenuInfoVO>> findUserListByQuery(MenuInfoQuery userQuery) {
		logger.info("findUserListByQuery receive the parameter :{}" , ToStringBuilder.reflectionToString(userQuery));
		return findUserList(userQuery.getName() , 1 , 2);
	}

	@Override
	@RequestMapping("findUserListJSON")
	public @ResponseBody BaseResponse<List<MenuInfoVO>> findUserListJSON(MenuInfoQuery userQuery) {
		logger.info("findUserListJSON receive the parameter :{}" , ToStringBuilder.reflectionToString(userQuery));
		return findUserList(userQuery.getName() , 1 , 2);
	}

}
