package com.cn.test.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class DateTimeFormatTag extends ComponentTagSupport {

	// 需要被转换的日期值
	private String value;

	// 日期值的类型（long,date)
	private String type;

	// 格式化的模式字符串
	private String pattern;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return new DateTimeFormat(stack);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		DateTimeFormat dateTimeFormat = (DateTimeFormat) component;
		dateTimeFormat.setPattern(pattern);
		dateTimeFormat.setType(type);
		dateTimeFormat.setValue(value);
	}
	
	

}
