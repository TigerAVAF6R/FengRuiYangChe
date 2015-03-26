package com.fryc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fryc.dao.TestDAO;
import com.fryc.dao.mapper.TestBeanMapper;
import com.fryc.exception.BusinessOperationException;
import com.fryc.factory.Autowired;
import com.fryc.model.input.TestInputBean;
import com.fryc.model.output.TestBean;
import com.fryc.template.DataAccessTemplate;

public class TestDAOImpl implements TestDAO {

	@Autowired
	private DataAccessTemplate dataAccessTemplate;
	
	
	@Override
	public List<TestBean> fetchAllTestBean() {
		String sql = "SELECT TEST_ID, TEST_VALUE from TEST";
		try {
			List<TestBean> listBean = dataAccessTemplate.executeQuery(sql, null, new TestBeanMapper());
			return listBean;
		} catch (BusinessOperationException e) {
			throw e;
		}
	}

	@Override
	public void saveNewTest(TestInputBean inputBean) {
		String sql = "INSERT INTO TEST (TEST_ID, TEST_VALUE) VALUES (?,?)";
		try {
			List<Object> params = new ArrayList<Object>();
			params.add(inputBean.getId());
			params.add(inputBean.getValue());
			dataAccessTemplate.executeUpdateNonTransactional(sql, params);
		} catch (BusinessOperationException e) {
			throw e;
		}
	}

}