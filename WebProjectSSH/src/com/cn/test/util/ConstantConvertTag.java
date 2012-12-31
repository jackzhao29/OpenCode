package com.cn.test.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class ConstantConvertTag extends ComponentTagSupport {

	// 需要转换显示的常量类型
	private String type;

	// 常量保存在数据库中的值
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		return new ConstantConvert(arg0);
	}

	@Override
	protected void populateParams() {
		// TODO Auto-generated method stub
		super.populateParams();
		
		ConstantConvert constantConvert = (ConstantConvert) component;
		constantConvert.setType(type);
		constantConvert.setValue(value);
		constantConvert.setConstants((Map)ServletActionContext.getServletContext().getAttribute("constants"));
	}
	
	

}
