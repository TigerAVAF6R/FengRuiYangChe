package com.fryc.model.output;

public class EmployeeBean {

	private String employeeId;
	private String userName;
	private String password;
	private String employeeName;
	private String employeeType;
	private String phone;
	private String email;
	private String activeFlag;
	private String createdTS;
	private String updatedTS;
	
	
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
	public String getCreatedTS() {
		return createdTS;
	}
	public void setCreatedTS(String createdTS) {
		this.createdTS = createdTS;
	}
	public String getUpdatedTS() {
		return updatedTS;
	}
	public void setUpdatedTS(String updatedTS) {
		this.updatedTS = updatedTS;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((employeeId == null) ? 0 : employeeId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeBean other = (EmployeeBean) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "EmployeeBean [employeeId=" + employeeId + ", userName="
				+ userName + ", password=" + password + ", employeeName="
				+ employeeName + ", employeeType=" + employeeType + ", phone="
				+ phone + ", email=" + email + ", activeFlag=" + activeFlag
				+ ", createdTS=" + createdTS + ", updatedTS=" + updatedTS + "]\n";
	}
	
}
