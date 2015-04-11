package com.fryc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fryc.dao.EmployeeDAO;
import com.fryc.model.input.EmployeeInputBean;
import com.fryc.model.output.EmployeeBean;
import com.fryc.util.DateTimeUtil;

@Repository("employeeDAO")
public class EmployeeDAOImpl extends BaseDAOImpl implements EmployeeDAO {

	@Override
	public List<EmployeeBean> fetchEmployeeList(EmployeeInputBean empInputBean) throws Exception {
		String queryAllSQL = "SELECT EMPLOYEE_ID, USERNAME, PASSWORD, EMPLOYEE_NAME, EMPLOYEE_TYPE, PHONE, EMAIL, ACTIVE_FLAG, CREATED_TS, UPDATED_TS FROM EMPLOYEE WHERE 1";
		if (empInputBean == null) {
			return this.getNamedParameterJdbcTemplate().query(queryAllSQL, new EmployeeBeanMapper());
		} else {
			StringBuilder sb = new StringBuilder(queryAllSQL);
			if (empInputBean.getEmployeeId() != null && empInputBean.getEmployeeId().trim().length() > 0) {
				sb.append(" AND EMPLOYEE_ID=:employeeId");
			}
			if (empInputBean.getEmployeeName() != null && empInputBean.getEmployeeName().trim().length() > 0) {
				sb.append(" AND EMPLOYEE_NAME=:employeeName");
			}
			if (empInputBean.getEmail() != null && empInputBean.getEmail().trim().length() > 0) {
				sb.append(" AND EMAIL=:email");
			}
			if (empInputBean.getEmployeeType() != null && empInputBean.getEmployeeType().trim().length() > 0) {
				sb.append(" AND EMPLOYEE_TYPE=:employeeType");
			}
			if (empInputBean.getPhone() != null && empInputBean.getPhone().trim().length() > 0) {
				sb.append(" AND PHONE=:phone");
			}
			if (empInputBean.getUserName() != null && empInputBean.getUserName().trim().length() > 0) {
				sb.append(" AND USERNAME=:userName");
			}
			if (empInputBean.getPassword() !=null && empInputBean.getPassword().trim().length() > 0) {
				sb.append(" AND PASSWORD=:password");
			}
			if (empInputBean.getActiveFlag() != null && empInputBean.getActiveFlag().trim().length() > 0) {
				sb.append(" AND ACTIVE_FLAG=:activeFlag");
			}
			String finalSQL = sb.toString();
			return this.getNamedParameterJdbcTemplate().query(finalSQL, new BeanPropertySqlParameterSource(empInputBean), new EmployeeBeanMapper());
		}
	}

	@Override
	public void saveEmployee(EmployeeInputBean empInputBean) throws Exception {
		String sql = "INSERT INTO EMPLOYEE(EMPLOYEE_ID, USERNAME, PASSWORD, EMPLOYEE_NAME, EMPLOYEE_TYPE, PHONE, EMAIL, ACTIVE_FLAG, CREATED_TS)"
				+ " VALUES (:employeeId, :userName, :password, :employeeName, :employeeType, :phone, :email, :activeFlag, :createdTS)";
		this.getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(empInputBean));
	}

	@Override
	public void updateEmployee(EmployeeInputBean empInputBean) throws Exception {
		String sql = "UPDATE EMPLOYEE SET PASSWORD=:password, EMPLOYEE_TYPE=:employeeType, PHONE=:phone, EMAIL=:email, UPDATED_TS=:updatedTS WHERE EMPLOYEE_ID=:employeeId";
		this.getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(empInputBean));
	}

	@Override
	public void deleteEmployee(EmployeeInputBean empInputBean) throws Exception {
		String sql = "UPDATE EMPLOYEE SET ACTIVE_FLAG='F', UPDATED_TS=:updatedTS WHERE EMPLOYEE_ID=:employeeId";
		this.getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(empInputBean));
	}

	class EmployeeBeanMapper implements RowMapper<EmployeeBean> {
		@Override
		public EmployeeBean mapRow(ResultSet rs, int arg1) throws SQLException {
			EmployeeBean bean = new EmployeeBean();
			bean.setActiveFlag(rs.getString("ACTIVE_FLAG"));
			bean.setEmail(rs.getString("EMAIL"));
			bean.setEmployeeId(rs.getString("EMPLOYEE_ID"));
			bean.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
			bean.setEmployeeType(rs.getString("EMPLOYEE_TYPE"));
			bean.setUserName(rs.getString("USERNAME"));
			bean.setPassword(rs.getString("PASSWORD"));
			bean.setPhone(rs.getString("PHONE"));
			bean.setCreatedTS(DateTimeUtil.convertTimestamp(rs.getTimestamp("CREATED_TS"), null));
			bean.setUpdatedTS(DateTimeUtil.convertTimestamp(rs.getTimestamp("UPDATED_TS"), null));
			return bean;
		}
	}
	
}