package com.alan.sample.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alan.bank.BankService;
import com.alan.bank.model.BankResponse;
import com.alan.bank.model.OrderVO;
import com.alan.sample.dao.OrderInfoDAO;
import com.alan.sample.model.OrderInfo;
import com.alan.sample.model.OrderInfoExample;

@Repository
public class BankServiceImpl {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BankService bankService;
	
	@Autowired
	private OrderInfoDAO orderInfoDao;
	
	@Resource(name="transactionTemplate")
	private TransactionTemplate template;
	
	/**出款
	 * 事务中包含远程调用,本地连接的占用时间会因为远程调用的时间不可控,而被消耗干净
	 * 导致其他用到数据库的服务不可用
	 * 
	 * 连接解决后,银行端口会出现失败,需要引入出款请求模型
	 * 
	 * 使用transactionTemplate以后,改善连接
	 * @param order
	 */
	@Transactional(propagation = Propagation.NEVER)
//	@Transactional
	public void tradeOut(final OrderInfo order){
		logger.info("==========:{}" , order);
		
		template.execute(new TransactionCallback<Object>(){
			@Override
			public Object doInTransaction(TransactionStatus status) {
				OrderInfo updateObj1 = new OrderInfo();
				updateObj1.setId(order.getId());
				updateObj1.setStatus(4);//1-->4处理中
				
				orderInfoDao.updateByPrimaryKeySelective(updateObj1);
				return null;
			}
			
		});
		
		
		OrderVO orderVo = new OrderVO();
		BeanUtils.copyProperties(order, orderVo);
		final BankResponse response = bankService.moneyOut(orderVo);
		template.execute(new TransactionCallback<Object>(){
			@Override
			public Object doInTransaction(TransactionStatus status) {
				
				OrderInfo updateObj = new OrderInfo();
				updateObj.setId(order.getId());
				if(response != null && response.getCode().equalsIgnoreCase("S")){
					updateObj.setStatus(2);//成功
				}else{
					updateObj.setStatus(3);//失败
				}
				orderInfoDao.updateByPrimaryKeySelective(updateObj);
				return null;
			}
		});
		
		/*OrderInfo updateObj = new OrderInfo();
		updateObj.setId(order.getId());
		if(response != null && response.getCode().equalsIgnoreCase("S")){
			updateObj.setStatus(2);//成功
		}else{
			updateObj.setStatus(3);//失败
		}
		orderInfoDao.updateByPrimaryKeySelective(updateObj);*/
		
		
	}
}
