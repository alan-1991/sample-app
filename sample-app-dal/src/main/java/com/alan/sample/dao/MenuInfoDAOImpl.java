package com.alan.sample.dao;

import com.alan.sample.model.MenuInfo;
import com.alan.sample.model.MenuInfoExample;
import com.istock.base.ibatis.IbatisDaoAnnotation4Template;
import com.istock.base.ibatis.IbatisTemplate;
import com.istock.base.ibatis.model.Page;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("MenuInfoDAO")
public class MenuInfoDAOImpl extends IbatisDaoAnnotation4Template implements MenuInfoDAO {
    @Resource(name="sqlMapClientTemplate")
    private IbatisTemplate template;

    public MenuInfoDAOImpl() {
        super();
    }

    public int countByExample(MenuInfoExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("tb_menu_info.countByExample", example);
        return count;
    }

    public int deleteByExample(MenuInfoExample example) {
        int rows = getSqlMapClientTemplate().delete("tb_menu_info.deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Integer id) {
        MenuInfo _key = new MenuInfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("tb_menu_info.deleteByPrimaryKey", _key);
        return rows;
    }

    public Integer insert(MenuInfo record) {
        Object newKey = getSqlMapClientTemplate().insert("tb_menu_info.insert", record);
        return (Integer) newKey;
    }

    public Integer insertSelective(MenuInfo record) {
        Object newKey = getSqlMapClientTemplate().insert("tb_menu_info.insertSelective", record);
        return (Integer) newKey;
    }

    @SuppressWarnings("unchecked")
    public List<MenuInfo> selectByExample(MenuInfoExample example) {
        List<MenuInfo> list = getSqlMapClientTemplate().queryForList("tb_menu_info.selectByExample", example);
        return list;
    }

    public MenuInfo selectByPrimaryKey(Integer id) {
        MenuInfo _key = new MenuInfo();
        _key.setId(id);
        MenuInfo record = (MenuInfo) getSqlMapClientTemplate().queryForObject("tb_menu_info.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByExampleSelective(MenuInfo record, MenuInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tb_menu_info.updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(MenuInfo record, MenuInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tb_menu_info.updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(MenuInfo record) {
        int rows = getSqlMapClientTemplate().update("tb_menu_info.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(MenuInfo record) {
        int rows = getSqlMapClientTemplate().update("tb_menu_info.updateByPrimaryKey", record);
        return rows;
    }

    @SuppressWarnings("unchecked")
    public List<MenuInfo> selectByPage(MenuInfoExample example, Page page) {
        List<MenuInfo> list = this.searchListPageMyObject("tb_menu_info.selectByExample", example, page);
        return list;
    }

    public IbatisTemplate getSqlMapClientTemplate() {
        return this.template;
    }

    protected static class UpdateByExampleParms extends MenuInfoExample {
        private Object record;

        public UpdateByExampleParms(Object record, MenuInfoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}