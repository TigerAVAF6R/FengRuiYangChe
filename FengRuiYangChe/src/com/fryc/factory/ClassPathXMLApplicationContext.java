package com.fryc.factory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ClassPathXMLApplicationContext {

	private List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();
	private Map<String, Object> sigletons = new HashMap<String, Object>();

	
	public ClassPathXMLApplicationContext(String filename) throws Throwable {
		this.readXML(filename);
		this.instanceBeans();
		this.injectObject();
		this.annotationInject();
	}

	private void readXML(String filename) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		/*String pa = this.getClass().getClassLoader().getResource(filename).getPath();
		System.out.println(pa);
		String path = this.getClass().getClassLoader().getResource("").getPath();
		Document doc = db.parse(path + filename);*/
		
		InputStream inStream = this.getClass().getResourceAsStream("/" + filename);
		Document doc = db.parse(inStream);
		
		NodeList rootNodeList = doc.getElementsByTagName("beans");
		Element root = (Element)rootNodeList.item(0);
		
		NodeList beanNodeList = root.getElementsByTagName("bean");
		if (beanNodeList != null) {
			int beanCount = beanNodeList.getLength();
			for (int i=0; i<beanCount; i++) {
				Element beanElement = (Element)beanNodeList.item(i);
				String id = beanElement.getAttribute("id");
				String clazz = beanElement.getAttribute("class");
				BeanDefinition beanDefinition = new BeanDefinition(id, clazz);
				
				NodeList propBeanList = beanElement.getElementsByTagName("property");
				if (propBeanList != null) {
					int propCount = propBeanList.getLength();
					for (int k=0; k<propCount; k++) {
						Element propElement = (Element)propBeanList.item(k);
						String propName = propElement.getAttribute("name");
						String propRef = propElement.getAttribute("ref");
						String propValue = propElement.getAttribute("value");
						PropertyDefinition propertyDefinition = new PropertyDefinition(propName, propRef, propValue);
						beanDefinition.getPropertyDefinitions().add(propertyDefinition);
					}
				}
				beanDefinitions.add(beanDefinition);
			}
		}
	}

	/**
	 * Bean initialization
	 */
	private void instanceBeans() {
		try {
			for (BeanDefinition beanDefinition : beanDefinitions) {
				if (beanDefinition.getClazz() != null
						&& !"".equals(beanDefinition.getClazz().trim())) {
					sigletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClazz()).newInstance());
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bean injection
	 * 
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private void injectObject() {
		try {
			for (BeanDefinition beanDefinition : beanDefinitions) {
				Object bean = sigletons.get(beanDefinition.getId());
				if (bean != null) {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
					for (PropertyDefinition propertyDefinition : beanDefinition.getPropertyDefinitions()) {
						for (PropertyDescriptor propertyDescriptor : ps) {
							if (propertyDefinition.getName().equals(propertyDescriptor.getName())) {
								Method setterMethod = propertyDescriptor.getWriteMethod();
								if (setterMethod != null) {
									Object temp = null;
									if (propertyDefinition.getRef() != null	&& !"".equals(propertyDefinition.getRef().trim())) {
										temp = sigletons.get(propertyDefinition.getRef());
									} 
									// not support "value" attribute in <property> configuration now
									/*else if (propertyDefinition.getValue() != null && !"".equals(propertyDefinition.getValue().trim())) {
										temp = ConvertUtils.convert(propertyDefinition.getValue(), propertyDescriptor.getPropertyType());
									}*/
									setterMethod.setAccessible(true);
									setterMethod.invoke(bean, temp);
								}
								break;
							}
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bean injection based on annotation
	 * 
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private void annotationInject() {
		try {
			for (String beanName : sigletons.keySet()) {
				Object bean = sigletons.get(beanName);
				if (bean != null) {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
					for (PropertyDescriptor propertyDescriptor : ps) {
						Method setterMethod = propertyDescriptor.getWriteMethod();
						if (setterMethod != null && setterMethod.isAnnotationPresent(Autowired.class)) {
							Autowired userDefinedResource = setterMethod.getAnnotation(Autowired.class);
							Object temp = null;
							if (userDefinedResource.name() != null && !"".equals(userDefinedResource.name())) {
								temp = sigletons.get(userDefinedResource.name());
							} else {
								temp = sigletons.get(propertyDescriptor.getName());
								if (temp == null) {
									for (String key : sigletons.keySet()) {
										if (propertyDescriptor.getPropertyType().isAssignableFrom(sigletons.get(key).getClass())) {
											temp = sigletons.get(key);
											break;
										}
									}
								}
							}
							setterMethod.setAccessible(true);
							setterMethod.invoke(bean, temp);
						}
					}

					Field[] fields = bean.getClass().getDeclaredFields();
					for (Field field : fields) {
						if (field.isAnnotationPresent(Autowired.class)) {
							Autowired userDefinedResource = field.getAnnotation(Autowired.class);
							Object temp = null;
							if (userDefinedResource.name() != null && !"".equals(userDefinedResource.name())) {
								temp = sigletons.get(userDefinedResource.name());
							} else {
								temp = sigletons.get(field.getName());
								if (temp == null) {
									for (String key : sigletons.keySet()) {
										if (field.getType().isAssignableFrom(sigletons.get(key).getClass())) {
											temp = sigletons.get(key);
											break;
										}
									}
								}
							}
							field.setAccessible(true);
							field.set(bean, temp);
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		if (beanDefinitions != null)
			beanDefinitions.clear();
		
		if (sigletons != null)
			sigletons.clear();
			
		beanDefinitions = null;
		sigletons = null;
	}
	
	/**
	 * get bean
	 * 
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName, Class<T> clazz) {
		return (T) this.sigletons.get(beanName);
	}

}
