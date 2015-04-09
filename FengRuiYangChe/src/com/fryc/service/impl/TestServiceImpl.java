package com.fryc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fryc.dao.TestDAO;
import com.fryc.model.input.TestInputBean;
import com.fryc.model.output.TestBean;
import com.fryc.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDAO testDAO;
	
	
	@Override
	public List<TestBean> fetchAllTestBean() {
		List<TestBean> list = null;
		try {
			list = testDAO.fetchAllTestBean();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public void saveNewTest(TestInputBean inputBean) {
		try {
			testDAO.saveNewTest(inputBean);
			
			// test for rollback if any exception happened within same transaction
			/*String s = null;
			s.charAt(0);*/
			
			testDAO.saveNewTest(inputBean);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
