package com.alan.sample.dao;

import com.istock.base.ibatis.IbatisDaoAnnotation4Template;
import com.istock.base.ibatis.IbatisTemplate;
import com.istock.base.ibatis.model.Page;
import com.alan.sample.model.OrderApply;
import com.alan.sample.model.OrderApplyExample;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("OrderApplyDAO")
public class OrderApplyDAOImpl extends IbatisDaoAnnotation4Template implements OrderApplyDAO {
    @Resource(name="sqlMapClientTemplate")
    private IbatisTemplate template;

    public OrderApplyDAOImpl() {
        super();
    }

    public int countByExample(OrderApplyExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("TB_ORDER_APPLY.countByExample", example);
        return count;
    }

    public int deleteByExample(OrderApplyExample example) {
        int rows = getSqlMapClientTemplate().delete("TB_ORDER_APPLY.deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Integer id) {
        OrderApply _key = new OrderApply();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("TB_ORDER_APPLY.deleteByPrimaryKey", _key);
        return rows;
    }

    public Integer insert(OrderApply record) {
        Object newKey = getSqlMapClientTemplate().insert("TB_ORDER_APPLY.insert", record);
        return (Integer) newKey;
    }

    public Integer insertSelective(OrderApply record) {
        Object newKey = getSqlMapClientTemplate().insert("TB_ORDER_APPLY.insertSelective", record);
        return (Integer) newKey;
    }

    @SuppressWarnings("unchecked")
    public List<OrderApply> selectByExample(OrderApplyExample example) {
        List<OrderApply> list = getSqlMapClientTemplate().queryForList("TB_ORDER_APPLY.selectByExample", example);
        return list;
    }

    public OrderApply selectByPrimaryKey(Integer id) {
        OrderApply _key = new OrderApply();
        _key.setId(id);
        OrderApply record = (OrderApply) getSqlMapClientTemplate().queryForObject("TB_ORDER_APPLY.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByExampleSelective(OrderApply record, OrderApplyExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TB_ORDER_APPLY.updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(OrderApply record, OrderApplyExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TB_ORDER_APPLY.updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(OrderApply record) {
        int rows = getSqlMapClientTemplate().update("TB_ORDER_APPLY.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(OrderApply record) {
        int rows = getSqlMapClientTemplate().update("TB_ORDER_APPLY.updateByPrimaryKey", record);
        return rows;
    }

    @SuppressWarnings("unchecked")
    public List<OrderApply> selectByPage(OrderApplyExample example, Page page) {
        List<OrderApply> list = this.searchListPageMyObject("TB_ORDER_APPLY.selectByExample", example, page);
        return list;
    }

    public IbatisTemplate getSqlMapClientTemplate() {
        return this.template;
    }

    protected static class UpdateByExampleParms extends OrderApplyExample {
        private Object record;

        public UpdateByExampleParms(Object record, OrderApplyExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}