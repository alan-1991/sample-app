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
import com.alan.sample.dao.OrderApplyDAO;
import com.alan.sample.dao.OrderInfoDAO;
import com.alan.sample.model.OrderApply;
import com.alan.sample.model.OrderApplyExample;
import com.alan.sample.model.OrderInfo;
import com.alan.sample.model.OrderInfoExample;

@Repository
public class BankServiceImpl2 {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BankService bankService;
	
	@Autowired
	private OrderInfoDAO orderInfoDao;
	@Autowired
	private OrderApplyDAO orderApplyDao;
	
	@Resource(name="transactionTemplate")
	private TransactionTemplate template;
	
	/**
	 * 引入orderApply模型
	 * 一定要使用transactionTemplate进行事务操作,不然order表和apply表的信息有可能会不一致
	 * 
	 * 
	 * 
	 * @param order
	 */
	@Transactional(propagation = Propagation.NEVER)
//	@Transactional
	public void tradeOut(final OrderInfo order){
		logger.info("==========:{}" , order);
		
		//变更对象状态,顺便加锁
		//数据库乐观锁加锁操作
		//锁(order)->插(orderApply)
		final Integer applyId = template.execute(new TransactionCallback<Integer>(){
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				OrderInfo updateObj1 = new OrderInfo();
				updateObj1.setStatus(4);
				
				OrderInfoExample example = new OrderInfoExample();
				OrderInfoExample.Criteria criteria = example.createCriteria();
				criteria.andStatusEqualTo(1);
				criteria.andIdEqualTo(order.getId());
				
				int updateRow = orderInfoDao.updateByExampleSelective(updateObj1, example);
				if(updateRow == 1){
					OrderApply orderApply = new OrderApply();
					orderApply.setOrderId(order.getId());
					orderApply.setStatus(1);
					return orderApplyDao.insertSelective(orderApply);
				}
				return -1;
			}
			
		});
		
		//如果插入成功
		//轮询的调用过程
		if(applyId != null && applyId >0){
			
			//锁orderApply记录
			//乐观锁的锁法.....不用锁表
			Boolean lockStatus = template.execute(new TransactionCallback<Boolean>(){

				@Override
				public Boolean doInTransaction(TransactionStatus status) {
					OrderApply orderApply = new OrderApply();
//					orderApply.setId(applyId);
					orderApply.setStatus(4);
					
					OrderApplyExample example = new OrderApplyExample();
					OrderApplyExample.Criteria criteria = example.createCriteria();
					criteria.andIdEqualTo(applyId);
					criteria.andStatusEqualTo(1);
//					return 1 == orderApplyDao.updateByPrimaryKeySelective(orderApply);
					return 1== orderApplyDao.updateByExampleSelective(orderApply, example);
				}
				
			});
			
			//如果锁成功,只有当前线程拿到了锁
			if(lockStatus){
				OrderVO orderVo = new OrderVO();
				BeanUtils.copyProperties(order, orderVo);
				final BankResponse response = bankService.moneyOut(orderVo);
				template.execute(new TransactionCallback<Object>(){
					@Override
					public Object doInTransaction(TransactionStatus status) {
						
						OrderInfoExample example = new OrderInfoExample();
						OrderInfoExample.Criteria criteria = example.createCriteria();
						criteria.andStatusEqualTo(4);
						criteria.andIdEqualTo(order.getId());
						
						OrderInfo updateObj = new OrderInfo();
//						updateObj.setId(order.getId());
						if(response != null && response.getCode().equalsIgnoreCase("S")){
							updateObj.setStatus(2);//成功
						}else{
							updateObj.setStatus(3);//失败
						}
//						orderInfoDao.updateByPrimaryKeySelective(updateObj);
						orderInfoDao.updateByExampleSelective(updateObj, example);
						
						OrderApply orderApply = new OrderApply();
						orderApply.setId(applyId);
						//response的正确判断
						if(response != null){
							if(response.getCode().equalsIgnoreCase("S")){
								orderApply.setStatus(2);//成功
								orderApplyDao.updateByPrimaryKeySelective(orderApply);
							}else if(response.getCode().equalsIgnoreCase("F")){
								orderApply.setStatus(3);//失败
								orderApplyDao.updateByPrimaryKeySelective(orderApply);
							}else{
								//do nothing
							}
						}
						
						return null;
					}
				});
			}
		}
		
	}
}
