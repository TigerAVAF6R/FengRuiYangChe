package com.fryc.service;

import java.util.List;

import com.fryc.model.input.EmployeeInputBean;
import com.fryc.model.output.EmployeeBean;

public interface EmployeeService {

	List<EmployeeBean> fetchEmployeeList(EmployeeInputBean empInputBean);
	
	void saveEmployee(EmployeeInputBean empInputBean);
	
	void updateEmployee(EmployeeInputBean empInputBean);
	
	void deleteEmployee(EmployeeInputBean empInputBean);
	
}
