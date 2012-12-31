package com.cn.test.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class TextCutTag
extends ComponentTagSupport
{
	
	// 需要被截断的字符串
	private String	value;
	
	// 需要被保留的字数
	private String	remainNum	= "50";
	
	// 在被截断的末尾加上的符号，默认为省略号
	private String	symbol		= "...";
	
	// 判断是否去除图片之类或者样式，默认false为不去除,true为去除
	private boolean	cutPattern	= false;
	
	private boolean	escape		= true;
	
	public void setCutPattern(boolean cutPattern)
	{
		this.cutPattern = cutPattern;
	}
	
	public void setEscape(boolean escape)
	{
		this.escape = escape;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public void setRemainNum(String remainNum)
	{
		this.remainNum = remainNum;
	}
	
	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res)
	{
		return new TextCut(stack);
	}
	
	@Override
	protected void populateParams()
	{
		super.populateParams();
		TextCut textCut = (TextCut) component;
		textCut.setSymbol(symbol);
		textCut.setValue(value);
		textCut.setRemainNum(remainNum);
		textCut.setEscape(escape);
		textCut.setCutPattern(cutPattern);
	}
	
}
