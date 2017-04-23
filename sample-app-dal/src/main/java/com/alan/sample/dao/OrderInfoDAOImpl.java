package com.alan.sample.dao;

import com.istock.base.ibatis.IbatisDaoAnnotation4Template;
import com.istock.base.ibatis.IbatisTemplate;
import com.istock.base.ibatis.model.Page;
import com.alan.sample.model.OrderInfo;
import com.alan.sample.model.OrderInfoExample;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("OrderInfoDAO")
public class OrderInfoDAOImpl extends IbatisDaoAnnotation4Template implements OrderInfoDAO {
    @Resource(name="sqlMapClientTemplate")
    private IbatisTemplate template;

    public OrderInfoDAOImpl() {
        super();
    }

    public int countByExample(OrderInfoExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("TB_ORDER_INFO.countByExample", example);
        return count;
    }

    public int deleteByExample(OrderInfoExample example) {
        int rows = getSqlMapClientTemplate().delete("TB_ORDER_INFO.deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(String id) {
        OrderInfo _key = new OrderInfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("TB_ORDER_INFO.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(OrderInfo record) {
        getSqlMapClientTemplate().insert("TB_ORDER_INFO.insert", record);
    }

    public void insertSelective(OrderInfo record) {
        getSqlMapClientTemplate().insert("TB_ORDER_INFO.insertSelective", record);
    }

    @SuppressWarnings("unchecked")
    public List<OrderInfo> selectByExample(OrderInfoExample example) {
        List<OrderInfo> list = getSqlMapClientTemplate().queryForList("TB_ORDER_INFO.selectByExample", example);
        return list;
    }

    public OrderInfo selectByPrimaryKey(String id) {
        OrderInfo _key = new OrderInfo();
        _key.setId(id);
        OrderInfo record = (OrderInfo) getSqlMapClientTemplate().queryForObject("TB_ORDER_INFO.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByExampleSelective(OrderInfo record, OrderInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TB_ORDER_INFO.updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(OrderInfo record, OrderInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TB_ORDER_INFO.updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(OrderInfo record) {
        int rows = getSqlMapClientTemplate().update("TB_ORDER_INFO.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(OrderInfo record) {
        int rows = getSqlMapClientTemplate().update("TB_ORDER_INFO.updateByPrimaryKey", record);
        return rows;
    }

    @SuppressWarnings("unchecked")
    public List<OrderInfo> selectByPage(OrderInfoExample example, Page page) {
        List<OrderInfo> list = this.searchListPageMyObject("TB_ORDER_INFO.selectByExample", example, page);
        return list;
    }

    public IbatisTemplate getSqlMapClientTemplate() {
        return this.template;
    }

    protected static class UpdateByExampleParms extends OrderInfoExample {
        private Object record;

        public UpdateByExampleParms(Object record, OrderInfoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}