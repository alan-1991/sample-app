package com.alan.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.istock.base.ibatis.model.Page;
import com.alan.sample.model.MenuInfo;
import com.alan.sample.service.MenuInfoService;

@Controller
public class MenuInfoController {

	@Autowired
	private MenuInfoService menuInfoService;
	
	@Value("${menu.title}")
	private String menuTitle;
	
	@RequestMapping("showMenuList")
	public String showMenuList(ModelMap model , String name , Page page){
		if(page == null){
			page = new Page();
		}
		page.setPageSize(3);
		menuInfoService.findByName(name, page);
		model.put("name", name);
		model.put("menuTitle", menuTitle);
		model.put("page", page);
		return "menuInfo/menuList";
	}
	
	@RequestMapping("editPre")
	public String editPre(ModelMap model , Integer id){
		if(id != null && id>0){
			//update
			MenuInfo menuInfo = menuInfoService.findMenuInfoById(id);
			model.put("menuInfo", menuInfo);
		}
		return "menuInfo/menuEdit";
	}
	
	@RequestMapping("edit")
	public String edit(ModelMap model , MenuInfo menuInfo){
		Integer result = menuInfoService.saveMenuInfo(menuInfo);
		if(result >0){
			model.put("message", "保存成功");
			
		}else{
			model.put("message", "保存失败");
			model.put("menuInfo", menuInfo);
			return "menuInfo/menuEdit";
		}
		return showMenuList(model ,null, new Page());
	}
	
	@RequestMapping("delete")
	public String delete(ModelMap model , Integer id){
		Integer result = menuInfoService.deleteMenuInfo(id);
		if(result >0){
			model.put("message", "保存成功");
		}else{
			model.put("message", "保存失败");
		}
		return showMenuList(model ,null, new Page());
	}
	
}
