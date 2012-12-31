package com.cn.test.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("unchecked")
public class ConstantConversionTag extends ComponentTagSupport
{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private String				constantType;
	private String				value;
	
	public void setConstantType(String constantType)
	{
		this.constantType = constantType;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2)
	{
		// TODO Auto-generated method stub
		return new ConstantConversion(arg0);
	}
	
	@Override
	protected void populateParams()
	{
		// TODO Auto-generated method stub
		super.populateParams();
		ConstantConversion constantConversion = (ConstantConversion) component;
		constantConversion.setConstantType(constantType);
		constantConversion.setValue(value);
		constantConversion.setConstants((Map<Integer, Map<Integer,String>>)ServletActionContext.getServletContext().getAttribute("allConstants"));
	}
	
}
