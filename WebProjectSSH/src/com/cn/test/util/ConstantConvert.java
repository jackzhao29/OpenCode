package com.cn.test.util;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class ConstantConvert extends Component
{
	
	// 需要转换显示的常量类型
	private String	type;
	
	// 常量保存在数据库中的值
	private String	value;
	
	private Map		constants	= new HashMap();
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public Map getConstants()
	{
		return constants;
	}
	
	public void setConstants(Map constants)
	{
		this.constants = constants;
	}
	
	public ConstantConvert(ValueStack stack)
	{
		super(stack);
	}
	
	@Override
	public boolean start(Writer writer)
	{
	    boolean result = super.start(writer);

        String actualValue = null;

        if (value == null) {
            value = "top";
        }
        else if (altSyntax()) {
            // the same logic as with findValue(String)
            // if value start with %{ and end with }, just cut it off!
            if (value.startsWith("%{") && value.endsWith("}")) {
                value = value.substring(2, value.length() - 1);
            }
        }

        // exception: don't call findString(), since we don't want the
        //            expression parsed in this one case. it really
        //            doesn't make sense, in fact.
        actualValue = (String)constants.get(type + value) ;

        try {
            if (actualValue != null) {
                writer.write(actualValue);
            } else {
                writer.write("");
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }

        return result;
		
	}
	
}
