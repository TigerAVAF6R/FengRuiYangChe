package com.fryc.service;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fryc.common.TransactionalTestBase;
import com.fryc.model.input.TestInputBean;
import com.fryc.model.output.TestBean;
import com.fryc.service.TestService;

public class TestServiceImplTest extends TransactionalTestBase {

	@Autowired
	private TestService testService;
	
	
	@Test
	public void testFetchAllTestBean() throws Exception {
		List<TestBean> result = testService.fetchAllTestBean();
		System.out.println(result == null ? "Empty list" : "Resultset size: " + result.size());
		System.out.println(result);
	}
	
	@Test
	public void testSaveNewTest() throws Exception {
		TestInputBean inputBean = new TestInputBean();
		String value = "test value 测试" + UUID.randomUUID().toString();
		inputBean.setValue(value);
		testService.saveNewTest(inputBean);
	}
	
}
