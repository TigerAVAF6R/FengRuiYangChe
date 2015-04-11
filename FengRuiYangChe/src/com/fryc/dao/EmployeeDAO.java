package com.fryc.dao;

import java.util.List;

import com.fryc.model.input.EmployeeInputBean;
import com.fryc.model.output.EmployeeBean;

public interface EmployeeDAO {

	List<EmployeeBean> fetchEmployeeList(EmployeeInputBean empInputBean) throws Exception;
	
	void saveEmployee(EmployeeInputBean empInputBean) throws Exception;
	
	void updateEmployee(EmployeeInputBean empInputBean) throws Exception;
	
	void deleteEmployee(EmployeeInputBean empInputBean) throws Exception;
	
}
