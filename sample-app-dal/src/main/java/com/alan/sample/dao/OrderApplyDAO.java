package com.alan.sample.dao;

import com.istock.base.ibatis.model.Page;
import com.alan.sample.model.OrderApply;
import com.alan.sample.model.OrderApplyExample;
import java.util.List;

public interface OrderApplyDAO {
    int countByExample(OrderApplyExample example);

    int deleteByExample(OrderApplyExample example);

    int deleteByPrimaryKey(Integer id);

    Integer insert(OrderApply record);

    Integer insertSelective(OrderApply record);

    List<OrderApply> selectByExample(OrderApplyExample example);

    OrderApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(OrderApply record, OrderApplyExample example);

    int updateByExample(OrderApply record, OrderApplyExample example);

    int updateByPrimaryKeySelective(OrderApply record);

    int updateByPrimaryKey(OrderApply record);

    List<OrderApply> selectByPage(OrderApplyExample example, Page page);
}