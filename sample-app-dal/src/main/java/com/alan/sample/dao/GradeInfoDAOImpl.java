package com.alan.sample.dao;

import com.istock.base.ibatis.IbatisDaoAnnotation4Template;
import com.istock.base.ibatis.IbatisTemplate;
import com.istock.base.ibatis.model.Page;
import com.alan.sample.model.GradeInfo;
import com.alan.sample.model.GradeInfoExample;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("GradeInfoDAO")
public class GradeInfoDAOImpl extends IbatisDaoAnnotation4Template implements GradeInfoDAO {
    @Resource(name="sqlMapClientTemplate")
    private IbatisTemplate template;

    public GradeInfoDAOImpl() {
        super();
    }

    public int countByExample(GradeInfoExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("TB_GRADE_INFO.countByExample", example);
        return count;
    }

    public int deleteByExample(GradeInfoExample example) {
        int rows = getSqlMapClientTemplate().delete("TB_GRADE_INFO.deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Integer id) {
        GradeInfo _key = new GradeInfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("TB_GRADE_INFO.deleteByPrimaryKey", _key);
        return rows;
    }

    public Integer insert(GradeInfo record) {
        Object newKey = getSqlMapClientTemplate().insert("TB_GRADE_INFO.insert", record);
        return (Integer) newKey;
    }

    public Integer insertSelective(GradeInfo record) {
        Object newKey = getSqlMapClientTemplate().insert("TB_GRADE_INFO.insertSelective", record);
        return (Integer) newKey;
    }

    @SuppressWarnings("unchecked")
    public List<GradeInfo> selectByExample(GradeInfoExample example) {
        List<GradeInfo> list = getSqlMapClientTemplate().queryForList("TB_GRADE_INFO.selectByExample", example);
        return list;
    }

    public GradeInfo selectByPrimaryKey(Integer id) {
        GradeInfo _key = new GradeInfo();
        _key.setId(id);
        GradeInfo record = (GradeInfo) getSqlMapClientTemplate().queryForObject("TB_GRADE_INFO.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByExampleSelective(GradeInfo record, GradeInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TB_GRADE_INFO.updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(GradeInfo record, GradeInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TB_GRADE_INFO.updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(GradeInfo record) {
        int rows = getSqlMapClientTemplate().update("TB_GRADE_INFO.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(GradeInfo record) {
        int rows = getSqlMapClientTemplate().update("TB_GRADE_INFO.updateByPrimaryKey", record);
        return rows;
    }

    @SuppressWarnings("unchecked")
    public List<GradeInfo> selectByPage(GradeInfoExample example, Page page) {
        List<GradeInfo> list = this.searchListPageMyObject("TB_GRADE_INFO.selectByExample", example, page);
        return list;
    }

    public IbatisTemplate getSqlMapClientTemplate() {
        return this.template;
    }

    protected static class UpdateByExampleParms extends GradeInfoExample {
        private Object record;

        public UpdateByExampleParms(Object record, GradeInfoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}