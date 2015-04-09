package com.fryc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fryc.dao.TestDAO;
import com.fryc.model.input.TestInputBean;
import com.fryc.model.output.TestBean;

@Repository("testDAO")
public class TestDAOImpl extends BaseDAOImpl implements TestDAO {

	@Override
	public List<TestBean> fetchAllTestBean() throws Exception {
		String sql = "SELECT TEST_ID, TEST_VALUE from TEST";
		List<TestBean> listBean = this.getJdbcTemplate().query(sql, new TestBeanMapper());
		return listBean;
	}

	@Override
	public void saveNewTest(TestInputBean inputBean) throws Exception {
		String sql = "INSERT INTO TEST (TEST_ID, TEST_VALUE) VALUES (:TEST_ID,:TEST_VALUE)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("TEST_ID", this.getNewUUID());
		paramMap.put("TEST_VALUE", inputBean.getValue());
		this.getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	class TestBeanMapper implements org.springframework.jdbc.core.RowMapper<TestBean> {
		@Override
		public TestBean mapRow(ResultSet rs, int arg1) throws SQLException {
			TestBean bean = new TestBean();
			bean.setId(rs.getString("TEST_ID"));
			bean.setName(rs.getString("TEST_VALUE"));
			return bean;
		}
	}
	
}