package com.fryc.service.impl;

import java.util.List;

import com.fryc.dao.TestDAO;
import com.fryc.exception.BusinessOperationException;
import com.fryc.factory.Autowired;
import com.fryc.model.input.TestInputBean;
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
		try {
			return testDAO.fetchAllTestBean();
		} catch (BusinessOperationException e) {
			throw e;
		}
	}

	@Override
	public void saveNewTest(TestInputBean inputBean) {
		try {
			// test two DB insert actions are in same transaction
			inputBean.setId(dataAccessTemplate.getNewUUID());
			testDAO.saveNewTest(inputBean);
			
			/*String s = null;
			s.indexOf(0);
			
			inputBean.setId(dataAccessTemplate.getNewUUID());
			testDAO.saveNewTest(inputBean);
			dataAccessTemplate.commitTransaction(true);*/
		} catch (Exception e) {
			/*try {
				dataAccessTemplate.rollbackTransaction();
			} catch (Exception e1) {
				throw e1;
			}*/
			throw new BusinessOperationException(e);
		} 
	}

}
