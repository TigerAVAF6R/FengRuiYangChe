package com.fryc.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fryc.common.NonTransactionalTestBase;
import com.fryc.dao.TestDAO;
import com.fryc.model.output.TestBean;

public class TestDAOImplTest extends NonTransactionalTestBase {

	@Autowired
	private TestDAO testDAO;
	
	
	@Test
	public void testFetchFTERecordList() throws Exception {
		List<TestBean> result = testDAO.fetchAllTestBean();
		System.out.println(result == null ? "Empty list" : "Resultset size: " + result.size());
		System.out.println(result);
	}
	
}
