package com.fryc.service;

import java.util.List;

import com.fryc.model.input.TestInputBean;
import com.fryc.model.output.TestBean;

public interface TestService {

	List<TestBean> fetchAllTestBean();
	
	void saveNewTest(TestInputBean inputBean);
	
}
