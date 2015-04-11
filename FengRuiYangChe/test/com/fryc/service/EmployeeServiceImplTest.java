package com.fryc.service;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fryc.common.TransactionalTestBase;
import com.fryc.model.input.EmployeeInputBean;
import com.fryc.model.output.EmployeeBean;
import com.fryc.util.ApplicationUtil;

public class EmployeeServiceImplTest extends TransactionalTestBase {

	@Autowired
	private EmployeeService employeeService;
	
	
	@Test
	public void testFetchEmployeeList() throws Exception {
		EmployeeInputBean empInputBean = new EmployeeInputBean();
		empInputBean.setActiveFlag("T");
		//empInputBean.setEmployeeName("何喆");
		empInputBean.setUserName("ben");
		empInputBean.setPassword("123456");
		/*empInputBean.setEmployeeId("testid");
		empInputBean.setEmployeeType("xxx");
		empInputBean.setEmail("xxxxxx@163.com");
		empInputBean.setPhone("13889529219");*/
		List<EmployeeBean> result = employeeService.fetchEmployeeList(empInputBean);
		System.out.println(result == null ? "Empty list" : "Resultset size: " + result.size());
		System.out.println(result);
	}
	
	/**
	 * Columns: EMPLOYEE_ID, USERNAME, PASSWORD, EMPLOYEE_NAME, EMPLOYEE_TYPE, PHONE, EMAIL, ACTIVE_FLAG, CREATED_TS
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSaveEmployee() throws Exception {
		EmployeeInputBean empInputBean = new EmployeeInputBean();
		String newId = ApplicationUtil.generateNewID();
		empInputBean.setEmployeeId(newId);
		empInputBean.setActiveFlag("T");
		empInputBean.setEmployeeName("冯超");
		empInputBean.setEmployeeType("工程师");
		empInputBean.setEmail("13889529219@163.com");
		empInputBean.setPassword("123456");
		empInputBean.setPhone("13889529219");
		empInputBean.setUserName("Alex");
		empInputBean.setCreatedTS(new Timestamp(System.currentTimeMillis()));
		employeeService.saveEmployee(empInputBean);
	}
	
	/**
	 * Columns: PASSWORD=:password, EMPLOYEE_TYPE=:employeeType, PHONE=:phone, EMAIL=:email UPDATED_TS=:updatedTS
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateEmployee() throws Exception {
		EmployeeInputBean empInputBean = new EmployeeInputBean();
		String empId = "84709ab9-2f01-404a-964d-0479d31adc21";
		empInputBean.setEmployeeId(empId);
		empInputBean.setActiveFlag("T");
		empInputBean.setEmployeeName("张老虎");
		empInputBean.setEmployeeType("顾问");
		empInputBean.setEmail("823410151@qq.com");
		empInputBean.setPassword("123456789");
		empInputBean.setPhone("13689529219");
		empInputBean.setUserName("tiger");
		empInputBean.setUpdatedTS(new Timestamp(System.currentTimeMillis()));
		employeeService.updateEmployee(empInputBean);
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		EmployeeInputBean empInputBean = new EmployeeInputBean();
		String empId = "84709ab9-2f01-404a-964d-0479d31adc21";
		empInputBean.setEmployeeId(empId);
		empInputBean.setUpdatedTS(new Timestamp(System.currentTimeMillis()));
		employeeService.deleteEmployee(empInputBean);
	}
	
}
