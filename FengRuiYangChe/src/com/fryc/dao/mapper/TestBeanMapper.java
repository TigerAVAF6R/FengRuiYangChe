package com.fryc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.fryc.dao.RowMapper;
import com.fryc.model.output.TestBean;

public class TestBeanMapper implements RowMapper<TestBean> {

	@Override
	public TestBean mapRow(ResultSet rs) throws SQLException {
		TestBean bean = new TestBean();
		bean.setId(rs.getString("TEST_ID"));
		bean.setName(rs.getString("TEST_VALUE"));
		return bean;
	}

}
