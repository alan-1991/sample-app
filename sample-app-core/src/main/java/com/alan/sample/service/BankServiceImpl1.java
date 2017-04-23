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
public class BankServiceImpl1 {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BankService bankService;
	
	@Autowired
	private OrderInfoDAO orderInfoDao;
	
	@Resource(name="transactionTemplate")
	private TransactionTemplate template;
	
	/**
	 * 引入多线程模式
	 * 外面多线程的时候,里面只请求一次
	 * 这个版本在某些情况下,可以满足业务需求
	 * 
	 * 悲观锁
	 * select * from xxx where id = xxx for update
	 * 
	 * 乐观锁
	 * select version from xxx where id = xxx
	 * update xxx set xxx = xxx where id = xxx and version = 'version'
	 * 
	 * 基于状态机的乐观锁
	 * int i = update xxx set status = 4 where id = xxx and status = 1;
	 * i == 1
	 * 
	 * 
	 * 如果银行返回失败,当我们重试的时候,银行需要和之前的交易不重复
	 * 我们需要引入OrderApply模型
	 * 
	 * @param order
	 */
	@Transactional(propagation = Propagation.NEVER)
//	@Transactional
	public void tradeOut(final OrderInfo order){
		logger.info("==========:{}" , order);
		
		//变更对象状态,顺便加锁
		//数据库乐观锁加锁操作
		Boolean lockStatus = template.execute(new TransactionCallback<Boolean>(){
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				OrderInfo updateObj1 = new OrderInfo();
//				updateObj1.setId(order.getId());
				updateObj1.setStatus(4);
				
				OrderInfoExample example = new OrderInfoExample();
				OrderInfoExample.Criteria criteria = example.createCriteria();
				criteria.andStatusEqualTo(1);
				criteria.andIdEqualTo(order.getId());
				
//				orderInfoDao.updateByPrimaryKeySelective(updateObj1);
				return 1 == orderInfoDao.updateByExampleSelective(updateObj1, example);
			}
			
		});
		
		
		if(lockStatus){
			OrderVO orderVo = new OrderVO();
			BeanUtils.copyProperties(order, orderVo);
			final BankResponse response = bankService.moneyOut(orderVo);
			template.execute(new TransactionCallback<Object>(){
				@Override
				public Object doInTransaction(TransactionStatus status) {
					
					OrderInfoExample example = new OrderInfoExample();
					OrderInfoExample.Criteria criteria = example.createCriteria();
					criteria.andStatusEqualTo(1);
					criteria.andIdEqualTo(order.getId());
					
					OrderInfo updateObj = new OrderInfo();
//					updateObj.setId(order.getId());
					if(response != null && response.getCode().equalsIgnoreCase("S")){
						updateObj.setStatus(2);//成功
					}else{
						updateObj.setStatus(3);//失败
					}
//					orderInfoDao.updateByPrimaryKeySelective(updateObj);
					orderInfoDao.updateByExampleSelective(updateObj, example);
					return null;
				}
			});
		}else{
			logger.info("========lock fail :{}=======",order);
		}
		
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
