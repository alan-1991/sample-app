package com.alan.sample.expose;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.istock.base.ibatis.model.Page;
import com.alan.sample.dao.OrderInfoDAO;
import com.alan.sample.model.OrderInfo;
import com.alan.sample.model.OrderInfoExample;
import com.alan.sample.service.BankServiceImpl;

@Controller
public class MoneyOutExpose {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrderInfoDAO orderInfoDao;
	@Autowired
	private BankServiceImpl bankService;
	
	@RequestMapping("out")
	public @ResponseBody Map<String , Object> out(){
		OrderInfoExample example = new OrderInfoExample();
		OrderInfoExample.Criteria criteria = example.createCriteria();
		
		criteria.andStatusEqualTo(1);
		List<OrderInfo> orderList = orderInfoDao.selectByPage(example, new Page(3));
		if(orderList != null && orderList.size()>0){
			for(OrderInfo order : orderList){
//				bankService.tradeOut(order);
				Thread t = new Thread(new ExecuteThread(order));
				t.start();
			}
			
		}
		Map<String , Object> result = new HashMap<String , Object>();
		result.put("code", "SSSS");
		result.put("msg", "调用成功");
		return result;
	}
	
	private class ExecuteThread implements Runnable {
		private OrderInfo orderInfo;
		public ExecuteThread(OrderInfo orderInfo){
			this.orderInfo = orderInfo;
		}
		public void run() {
			bankService.tradeOut(orderInfo);
		}
	}
}
