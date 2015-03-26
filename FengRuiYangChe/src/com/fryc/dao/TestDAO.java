package com.fryc.dao;

import java.util.List;

import com.fryc.model.input.TestInputBean;
import com.fryc.model.output.TestBean;

public interface TestDAO {

	List<TestBean> fetchAllTestBean();
	
	void saveNewTest(TestInputBean inputBean);
	
}
