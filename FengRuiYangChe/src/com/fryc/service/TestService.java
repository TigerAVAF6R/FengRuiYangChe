package com.fryc.service;

import java.util.List;

import com.fryc.model.output.TestBean;

public interface TestService {

	List<TestBean> fetchAllTestBean();
	
}
