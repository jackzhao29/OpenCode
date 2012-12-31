package com.cn.test.util;

import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.components.Component;

//import com.opensymphony.xwork2.util.TextUtils;
import com.opensymphony.xwork2.util.ValueStack;

public class TextCut extends Component
{
	
	// 需要被截断的字符串
	private String	value;
	
	// 需要被保留的字数
	private String	remainNum;
	
	// 在被截断的末尾加上的符号，默认为省略号
	private String	symbol;
	
	private boolean	escape	= true;
	
	private boolean	cutPattern;
	
	public void setCutPattern(boolean cutPattern)
	{
		this.cutPattern = cutPattern;
	}
	
	public void setEscape(boolean escape)
	{
		this.escape = escape;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public String getRemainNum()
	{
		return remainNum;
	}
	
	public void setRemainNum(String remainNum)
	{
		this.remainNum = remainNum;
	}
	
	public String getSymbol()
	{
		return symbol;
	}
	
	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}
	
	public TextCut(ValueStack stack)
	{
		super(stack);
	}
	
	@Override
	public boolean end(Writer writer, String body)
	{
		boolean result = super.start(writer);
		
		boolean isValid = true;
		
		if(isValid)
		{
			if(value.startsWith("%{") && value.endsWith("}"))
			{
				value = value.substring(2, value.length() - 1);
				value = (String) getStack().findValue(value, String.class);
				isValid = value == null ? false : true;
			}
			else if(value != null)
			{
				isValid = true;
			}
			else
			{
				isValid = false;
			}
		}
		
		if(isValid)
		{
			if(remainNum.startsWith("%{") && remainNum.endsWith("}"))
			{
				remainNum = value.substring(2, remainNum.length() - 1);
				remainNum = (String) getStack().findValue(remainNum, String.class);
				isValid = remainNum == null ? false : true;
			}
			else if(remainNum != null)
			{
				isValid = true;
			}
			else
			{
				isValid = false;
			}
		}
		
		if(isValid)
		{
			if(symbol.startsWith("%{") && symbol.endsWith("}"))
			{
				symbol = value.substring(2, symbol.length() - 1);
				symbol = (String) getStack().findValue(symbol, String.class);
				isValid = symbol == null ? false : true;
			}
			else if(symbol != null)
			{
				isValid = true;
			}
			else
			{
				isValid = false;
			}
		}
		
		if(isValid)
		{
			
			try
			{
				StringBuilder outputStringBuilder = new StringBuilder();
				int remainNumInt = Integer.parseInt(remainNum);
				if(cutPattern)
				{
					Pattern p = Pattern.compile("<([^><]*)\\>");
					Matcher m = p.matcher(value);
					StringBuffer stringbuffer = new StringBuffer();
					while (m.find())
					{
						m.appendReplacement(stringbuffer, "");
					}
					m.appendTail(stringbuffer);
					value = stringbuffer.toString();
				}
				
				if(value.length() > 0)
				{
					if(value.length() <= remainNumInt)
					{
						outputStringBuilder.append(prepare(value));
					}
					else
					{
						outputStringBuilder.append(prepare(value.substring(0, remainNumInt)));
						outputStringBuilder.append(symbol);
					}
				}
				
				writer.write(outputStringBuilder.toString());
			}
			catch (IOException e)
			{
				Logger.getLogger(TextCut.class.getName()).log(Level.SEVERE, null, e);
			}
			
		}
		return super.end(writer, body);
		
	}
	
	private String prepare(String value)
	{
		if(escape)
		{
			//struts2-core-2.0.14  升级到  struts2-core-2.3.4.1
			//return TextUtils.htmlEncode(value);
			
			//升级到  struts2-core-2.3.4.1
			return value;
		}
		else
		{
			return value;
		}
	}
	
}
