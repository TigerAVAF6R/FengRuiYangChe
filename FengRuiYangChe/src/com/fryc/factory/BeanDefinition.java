package com.fryc.factory;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
	
	private String id;
	private String clazz;
	private List<PropertyDefinition> propertyDefinitions=new ArrayList<PropertyDefinition>();
	
	
	public BeanDefinition(String id, String clazz) {
		super();
		this.id = id;
		this.clazz = clazz;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public List<PropertyDefinition> getPropertyDefinitions() {
		return propertyDefinitions;
	}
	public void setPropertyDefinitions(List<PropertyDefinition> propertyDefinitions) {
		this.propertyDefinitions = propertyDefinitions;
	}
	
}