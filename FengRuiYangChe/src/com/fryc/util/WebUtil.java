package com.fryc.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fryc.model.ResponseEntity;

public class WebUtil {
	
	private static JsonFactory jf = null;
	private static ObjectMapper mapper = null;
	
	static {
		jf = new JsonFactory();
		mapper = new ObjectMapper();
	}
	
	private WebUtil() {}

	public synchronized static void sendJSONResponse(ResponseEntity entity, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType(AppConstant.RESPONSE_DEFAULT_CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringWriter sw = new StringWriter();
	    JsonGenerator gen = null;
		try {
			if (jf == null)
				jf = new JsonFactory();
			gen = jf.createJsonGenerator(sw);
			gen.enable(JsonGenerator.Feature.QUOTE_FIELD_NAMES);
		    gen.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
		    gen.enable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		try {
			if (mapper == null)
				mapper = new ObjectMapper();
			mapper.writeValue(gen, entity);
			String jsonStr = sw.toString();
			out.write(jsonStr);
			out.flush();
			return;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sw != null)
				try {
					sw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (out != null) {
				out.close();
			}
		}
	}

}
