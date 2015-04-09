package com.fryc.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fryc.model.ResponseEntity;
import com.fryc.model.input.TestInputBean;
import com.fryc.model.output.TestBean;
import com.fryc.service.TestService;
import com.fryc.util.AppConstant;
import com.fryc.util.AppLogger;
import com.fryc.util.MessageTranslater;
import com.fryc.util.SpringContextUtil;
import com.fryc.util.WebUtil;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/service/test")
public class TestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private TestService testService;
	
       
    public TestServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		testService = SpringContextUtil.getBean("testService", TestService.class);
	}

	public void destroy() {
		testService = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppLogger.enterMethod("TestServlet", "doGet");
		ResponseEntity entity = ResponseEntity.getDefaultEntity();
		try {
			List<TestBean> list = testService.fetchAllTestBean();
			Map<String, Object> data = new LinkedHashMap<String, Object>();
			data.put(AppConstant.RESPONSE_DEFAULT_JSON_KEY, list);
			entity.setData(data);
		} catch (Exception e) {
			entity.setSuccessFlag(AppConstant.RESPONSE_FLAG_ERROR);
			entity.setMessage(MessageTranslater.translateExp(e));
		}
		WebUtil.sendJSONResponse(entity, request, response);
		AppLogger.exitMethod("TestServlet", "doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponseEntity entity = ResponseEntity.getDefaultEntity();
		try {
			TestInputBean inputBean = new TestInputBean();
			String value = "test value 测试" + UUID.randomUUID().toString();
			System.out.println("value to be saved: " + value);
			inputBean.setValue(value);
			testService.saveNewTest(inputBean);
		} catch (Exception e) {
			entity.setSuccessFlag(AppConstant.RESPONSE_FLAG_ERROR);
			entity.setMessage(MessageTranslater.translateExp(e));
		}
		WebUtil.sendJSONResponse(entity, request, response);
	}

}
