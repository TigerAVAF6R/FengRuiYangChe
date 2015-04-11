package com.fryc.model.input;

import java.sql.Timestamp;

public class EmployeeInputBean {

	private String employeeId;
	private String userName;
	private String password;
	private String employeeName;
	private String employeeType;
	private String phone;
	private String email;
	private String activeFlag;
	private Timestamp createdTS;
	private Timestamp updatedTS;
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Timestamp getCreatedTS() {
		return createdTS;
	}
	public void setCreatedTS(Timestamp createdTS) {
		this.createdTS = createdTS;
	}
	public Timestamp getUpdatedTS() {
		return updatedTS;
	}
	public void setUpdatedTS(Timestamp updatedTS) {
		this.updatedTS = updatedTS;
	}
	
	@Override
	public String toString() {
		return "EmployeeInputBean [employeeId=" + employeeId + ", userName="
				+ userName + ", password=" + password + ", employeeName="
				+ employeeName + ", employeeType=" + employeeType + ", phone="
				+ phone + ", email=" + email + ", activeFlag=" + activeFlag
				+ ", createdTS=" + createdTS + ", updatedTS=" + updatedTS + "]\n";
	}
	
}
