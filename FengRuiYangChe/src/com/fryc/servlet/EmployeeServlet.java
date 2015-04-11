package com.fryc.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fryc.model.ResponseEntity;
import com.fryc.model.input.EmployeeInputBean;
import com.fryc.model.output.EmployeeBean;
import com.fryc.service.EmployeeService;
import com.fryc.util.AppConstant;
import com.fryc.util.ApplicationUtil;
import com.fryc.util.MessageTranslater;
import com.fryc.util.SpringContextUtil;
import com.fryc.util.WebUtil;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/service/employee")
public class EmployeeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private EmployeeService employeeService;
	
       
    public EmployeeServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		employeeService = SpringContextUtil.getBean("employeeService", EmployeeService.class);
	}

	public void destroy() {
		employeeService = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponseEntity entity = ResponseEntity.getDefaultEntity();
		try {
			EmployeeInputBean empInputBean = populateEmpInputBean(request);
			List<EmployeeBean> list = employeeService.fetchEmployeeList(empInputBean);
			entity.addDataEntry(AppConstant.RESPONSE_DEFAULT_JSON_KEY, list);
		} catch (Exception e) {
			entity.setSuccessFlag(AppConstant.RESPONSE_FLAG_ERROR);
			entity.setMessage(MessageTranslater.translateExp(e));
		}
		WebUtil.sendJSONResponse(entity, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponseEntity entity = ResponseEntity.getDefaultEntity();
		try {
			EmployeeInputBean empInputBean = populateEmpInputBean(request);
			String action = request.getParameter(AppConstant.REQUEST_ACTION_KEY);
			if (AppConstant.REQUEST_ACTION_CREATE.equals(action)) {
				empInputBean.setEmployeeId(ApplicationUtil.generateNewID());
				empInputBean.setCreatedTS(new Timestamp(System.currentTimeMillis()));
				employeeService.saveEmployee(empInputBean);
			} else if (AppConstant.REQUEST_ACTION_UPDATE.equals(action)) {
				empInputBean.setUpdatedTS(new Timestamp(System.currentTimeMillis()));
				employeeService.updateEmployee(empInputBean);
			} else if (AppConstant.REQUEST_ACTION_DELETE.equals(action)) {
				employeeService.deleteEmployee(empInputBean);
			}
		} catch (Exception e) {
			entity.setSuccessFlag(AppConstant.RESPONSE_FLAG_ERROR);
			entity.setMessage(MessageTranslater.translateExp(e));
		}
		WebUtil.sendJSONResponse(entity, request, response);
	}

	/**
	 * private String employeeId;
	 * private String userName;
	 * private String password;
	 * private String employeeName;
	 * private String employeeType;
	 * private String phone;
	 * private String email;
	 * private String activeFlag;
	 * private Timestamp createdTS;
	 * private Timestamp updatedTS;
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private EmployeeInputBean populateEmpInputBean(HttpServletRequest request) throws Exception {
		EmployeeInputBean inputBean = null;
		Enumeration<String> paramNames = request.getParameterNames();
		if (paramNames != null && paramNames.hasMoreElements()) {
			inputBean = new EmployeeInputBean();
			
			while (paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				switch (paramName) {
				case "employeeId":
					inputBean.setEmployeeId(paramValue);
					break;
				case "userName":
					inputBean.setUserName(paramValue);
					break;
				case "password":
					inputBean.setPassword(paramValue);
					break;
				case "employeeName":
					inputBean.setEmployeeName(paramValue);
					break;
				case "employeeType":
					inputBean.setEmployeeType(paramValue);
					break;
				case "phone":
					inputBean.setPhone(paramValue);
					break;
				case "email":
					inputBean.setEmail(paramValue);
					break;
				case "activeFlag":
					inputBean.setActiveFlag(paramValue);
					break;
				}
			}
		}
		return inputBean;
	}
	
}
