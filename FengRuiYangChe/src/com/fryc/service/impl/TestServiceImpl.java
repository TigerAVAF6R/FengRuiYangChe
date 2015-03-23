package com.fryc.service.impl;

import java.util.List;

import com.fryc.dao.TestDAO;
import com.fryc.exception.BusinessOperationException;
import com.fryc.factory.Autowired;
import com.fryc.model.output.TestBean;
import com.fryc.service.TestService;
import com.fryc.template.DataAccessTemplate;

public class TestServiceImpl implements TestService {

	@Autowired
	private DataAccessTemplate dataAccessTemplate;
	
	@Autowired
	private TestDAO testDAO;
	
	
	@Override
	public List<TestBean> fetchAllTestBean() {
		try{
			return testDAO.fetchAllTestBean();
		}catch(BusinessOperationException e){
			throw e;
		}
	}

}
