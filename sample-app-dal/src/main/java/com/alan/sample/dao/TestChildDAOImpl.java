package com.alan.sample.dao;

import com.istock.base.ibatis.IbatisDaoAnnotation4Template;
import com.istock.base.ibatis.IbatisTemplate;
import com.istock.base.ibatis.model.Page;
import com.alan.sample.model.TestChild;
import com.alan.sample.model.TestChildExample;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("TestChildDAO")
public class TestChildDAOImpl extends IbatisDaoAnnotation4Template implements TestChildDAO {
    @Resource(name="sqlMapClientTemplate")
    private IbatisTemplate template;

    public TestChildDAOImpl() {
        super();
    }

    public int countByExample(TestChildExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("TB_TEST_CHIND.countByExample", example);
        return count;
    }

    public int deleteByExample(TestChildExample example) {
        int rows = getSqlMapClientTemplate().delete("TB_TEST_CHIND.deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Integer id) {
        TestChild _key = new TestChild();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("TB_TEST_CHIND.deleteByPrimaryKey", _key);
        return rows;
    }

    public Integer insert(TestChild record) {
        Object newKey = getSqlMapClientTemplate().insert("TB_TEST_CHIND.insert", record);
        return (Integer) newKey;
    }

    public Integer insertSelective(TestChild record) {
        Object newKey = getSqlMapClientTemplate().insert("TB_TEST_CHIND.insertSelective", record);
        return (Integer) newKey;
    }

    @SuppressWarnings("unchecked")
    public List<TestChild> selectByExample(TestChildExample example) {
        List<TestChild> list = getSqlMapClientTemplate().queryForList("TB_TEST_CHIND.selectByExample", example);
        return list;
    }

    public TestChild selectByPrimaryKey(Integer id) {
        TestChild _key = new TestChild();
        _key.setId(id);
        TestChild record = (TestChild) getSqlMapClientTemplate().queryForObject("TB_TEST_CHIND.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByExampleSelective(TestChild record, TestChildExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TB_TEST_CHIND.updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TestChild record, TestChildExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TB_TEST_CHIND.updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TestChild record) {
        int rows = getSqlMapClientTemplate().update("TB_TEST_CHIND.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TestChild record) {
        int rows = getSqlMapClientTemplate().update("TB_TEST_CHIND.updateByPrimaryKey", record);
        return rows;
    }

    @SuppressWarnings("unchecked")
    public List<TestChild> selectByPage(TestChildExample example, Page page) {
        List<TestChild> list = this.searchListPageMyObject("TB_TEST_CHIND.selectByExample", example, page);
        return list;
    }

    public IbatisTemplate getSqlMapClientTemplate() {
        return this.template;
    }

    protected static class UpdateByExampleParms extends TestChildExample {
        private Object record;

        public UpdateByExampleParms(Object record, TestChildExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}