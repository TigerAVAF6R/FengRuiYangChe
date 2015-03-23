package com.fryc.dao;

import java.util.List;

import com.fryc.model.output.TestBean;

public interface TestDAO {

	List<TestBean> fetchAllTestBean();
	
}
