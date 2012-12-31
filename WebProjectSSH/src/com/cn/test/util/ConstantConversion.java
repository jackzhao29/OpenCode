package com.cn.test.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class ConstantConversion extends Component
{
	private String								constantType;
	private String								value;
	
	private Map<Integer, Map<Integer, String>>	constants;
	
	public ConstantConversion(ValueStack stack)
	{
		super(stack);
	}
	
	@Override
	public boolean start(Writer writer)
	{
		boolean result = super.start(writer);
		try
		{
			String actualValue=parse(value);
			if(!StringUtils.isBlank(actualValue)){
				value = constants.get(constantType).get(Integer.parseInt(actualValue));
				StringBuilder str = new StringBuilder();
				str.append(value);
				writer.write(str.toString());
			}
			
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	private String parse(String value) throws Exception
	{
		
		String actualValue = null;
		
		if(value == null)
		{
			throw new Exception("标签属性不能为空！");
		}
		
		if(altSyntax())
		{
			if(value.startsWith("%{") && value.endsWith("}"))
			{
				value = value.substring(2, value.length() - 1);
			}
		}
		
		actualValue = (String) getStack().findValue(value, String.class);
//		if(actualValue == null)
//		{
//			actualValue = value;
//		}
		return actualValue;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public String getConstantType()
	{
		return constantType;
	}
	
	public void setConstantType(String constantType)
	{
		this.constantType = constantType;
	}
	
	public Map<Integer, Map<Integer, String>> getConstants()
	{
		return constants;
	}
	
	public void setConstants(Map<Integer, Map<Integer, String>> constants)
	{
		this.constants = constants;
	}
	
}
