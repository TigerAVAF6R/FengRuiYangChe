package com.fryc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fryc.dao.EmployeeDAO;
import com.fryc.model.input.EmployeeInputBean;
import com.fryc.model.output.EmployeeBean;
import com.fryc.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDAO;

	
	@Override
	public List<EmployeeBean> fetchEmployeeList(EmployeeInputBean empInputBean) {
		List<EmployeeBean> list = null;
		try {
			list = employeeDAO.fetchEmployeeList(empInputBean);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public void saveEmployee(EmployeeInputBean empInputBean) {
		try {
			employeeDAO.saveEmployee(empInputBean);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateEmployee(EmployeeInputBean empInputBean) {
		try {
			employeeDAO.updateEmployee(empInputBean);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteEmployee(EmployeeInputBean empInputBean) {
		try {
			employeeDAO.deleteEmployee(empInputBean);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
