package com.fryc.factory;

public class PropertyDefinition {
	
	private String name;
	private String ref;
	private String value;
	
	
	public PropertyDefinition(String name, String ref,String value) {
		super();
		this.name = name;
		this.ref = ref;
		this.value=value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
