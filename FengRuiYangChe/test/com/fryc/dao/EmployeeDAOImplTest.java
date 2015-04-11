package com.fryc.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fryc.common.NonTransactionalTestBase;
import com.fryc.model.input.EmployeeInputBean;
import com.fryc.model.output.EmployeeBean;

public class EmployeeDAOImplTest extends NonTransactionalTestBase {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	
	@Test
	public void testFetchEmployeeList() throws Exception {
		EmployeeInputBean empInputBean = new EmployeeInputBean();
		empInputBean.setActiveFlag("T");
		empInputBean.setEmployeeId("testid");
		empInputBean.setEmployeeName("tiger");
		empInputBean.setEmployeeType("xxx");
		empInputBean.setEmail("xxxxxx@163.com");
		empInputBean.setPassword("123456");
		empInputBean.setPhone("13889529219");
		empInputBean.setUserName("张老虎");
		List<EmployeeBean> result = employeeDAO.fetchEmployeeList(empInputBean);
		System.out.println(result == null ? "Empty list" : "Resultset size: " + result.size());
		System.out.println(result);
	}
	
}
