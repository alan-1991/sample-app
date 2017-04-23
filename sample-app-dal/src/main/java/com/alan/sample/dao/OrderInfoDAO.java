package com.alan.sample.dao;

import com.istock.base.ibatis.model.Page;
import com.alan.sample.model.OrderInfo;
import com.alan.sample.model.OrderInfoExample;
import java.util.List;

public interface OrderInfoDAO {
    int countByExample(OrderInfoExample example);

    int deleteByExample(OrderInfoExample example);

    int deleteByPrimaryKey(String id);

    void insert(OrderInfo record);

    void insertSelective(OrderInfo record);

    List<OrderInfo> selectByExample(OrderInfoExample example);

    OrderInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(OrderInfo record, OrderInfoExample example);

    int updateByExample(OrderInfo record, OrderInfoExample example);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    List<OrderInfo> selectByPage(OrderInfoExample example, Page page);
}