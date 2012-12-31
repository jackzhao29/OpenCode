package com.cn.test.util;

import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class DateTimeFormat extends Component
{
	
	// 需要被转换的日期值
	private String	value;
	
	// 日期值的类型（long,date)
	private String	type	= "long";
	
	// 格式化的模式字符串
	private String	pattern	= "";
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getPattern()
	{
		return pattern;
	}
	
	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}
	
	public DateTimeFormat(ValueStack stack)
	{
		super(stack);
	}
	
	@Override
	public boolean end(Writer writer, String body)
	{
		
		boolean result = super.start(writer);
		boolean isValid = true;
		
		if(StringUtils.isEmpty(type))
			type = "long";
		if(StringUtils.isEmpty(pattern))
			pattern = "";
		
		
		type = parse(type);
		pattern = parse(pattern);
		
		/*
		 * 解析value的值，如果为null输出一个空字符串
		 */
		long timeValue = 0;
		Object actualValue = null;
		
		if(altSyntax())
		{
			if(value.startsWith("%{") && value.endsWith("}"))
			{
				value = value.substring(2, value.length() - 1);
			}
		}
		
		actualValue = findValue(value);
		if(actualValue == null) {
			return super.end(writer, body);
		}
		
		if("long".equals(type)) {
			timeValue = (Long) actualValue;
		}
		else if("date".equals(type))
		{
			timeValue = ((Date) findValue(value)).getTime();
		}
		else if("timestamp".equals(type))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			try
			{
				timeValue = sdf.parse(findValue(value).toString()).getTime();
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		
		if(timeValue == 0) {
			return super.end(writer, body);
		}
		
		if(isValid)
		{
			
			try
			{
				StringBuilder outputStringBuilder = new StringBuilder();
				if(StringUtils.isEmpty(pattern))
				{
					Calendar calendarNow = Calendar.getInstance();
					calendarNow.setTimeInMillis(System.currentTimeMillis());
					
					Calendar calendarValue = Calendar.getInstance();
					calendarValue.setTimeInMillis(timeValue);
					
					// System.out.println("当前时间：==================");
					// System.out.println("年份：" +
					// calendarNow.get(Calendar.YEAR));
					// System.out.println("月份：" +
					// calendarNow.get(Calendar.MONTH));
					// System.out.println("日期：" +
					// calendarNow.get(Calendar.DAY_OF_MONTH));
					//
					// System.out.println("24小时：：" +
					// calendarNow.get(Calendar.HOUR_OF_DAY));
					// System.out.println("12小时：：" +
					// calendarNow.get(Calendar.HOUR));
					// System.out.println("分钟：" +
					// calendarNow.get(Calendar.MINUTE));
					// System.out.println("当前时间：" + calendarNow.getTime());
					// System.out.println("ZONE：" + calendarNow.getTimeZone());
					//					
					//					
					// System.out.println("待转换时间：==================");
					// System.out.println("年份：" +
					// calendarValue.get(Calendar.YEAR));
					// System.out.println("月份：" +
					// calendarValue.get(Calendar.MONTH));
					// System.out.println("日期：" +
					// calendarValue.get(Calendar.DAY_OF_MONTH));
					//
					// System.out.println("24小时：：" +
					// calendarValue.get(Calendar.HOUR_OF_DAY));
					// System.out.println("12小时：：" +
					// calendarValue.get(Calendar.HOUR));
					// System.out.println("分钟：" +
					// calendarValue.get(Calendar.MINUTE));
					// System.out.println("当前时间：" + calendarValue.getTime());
					// System.out.println("ZONE：" +
					// calendarValue.getTimeZone());
					
					if((calendarNow.get(Calendar.YEAR) - calendarValue.get(Calendar.YEAR)) > 2)
					{
						outputStringBuilder.append(calendarValue.get(Calendar.YEAR));
					}
					
					else if((calendarNow.get(Calendar.YEAR) - calendarValue.get(Calendar.YEAR)) == 2)
					{
						outputStringBuilder.append("前年");
					}
					
					else if((calendarNow.get(Calendar.YEAR) - calendarValue.get(Calendar.YEAR)) == 1)
					{
						outputStringBuilder.append("去年");
					}
					
					else if(calendarNow.get(Calendar.YEAR) == calendarValue.get(Calendar.YEAR))
					{
						if(calendarNow.get(Calendar.DAY_OF_YEAR) == calendarValue.get(Calendar.DAY_OF_YEAR))
						{
							int offHour = calendarNow.get(Calendar.HOUR_OF_DAY) - calendarValue.get(Calendar.HOUR_OF_DAY);
							if(offHour >= 1)
							{
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
								outputStringBuilder.append(simpleDateFormat.format(calendarValue.getTime()));
							}
							else
							{
								int offMinute = calendarNow.get(Calendar.MINUTE) - calendarValue.get(Calendar.MINUTE);
								if(offMinute >= 1)
								{
									outputStringBuilder.append(offMinute + "分钟前");
								}
								else
								{
									int offSecond = calendarNow.get(Calendar.SECOND) - calendarValue.get(Calendar.SECOND);
									outputStringBuilder.append(offSecond + "秒前");
								}
							}
							
						}
						else if((calendarNow.get(Calendar.DAY_OF_YEAR) - calendarValue.get(Calendar.DAY_OF_YEAR)) == 1)
						{
							
							outputStringBuilder.append("昨天");
							
						}
						else if((calendarNow.get(Calendar.DAY_OF_YEAR) - calendarValue.get(Calendar.DAY_OF_YEAR)) == 2)
						{
							
							outputStringBuilder.append("前天");
							
						}
						else
						{
							
							outputStringBuilder.append(calendarValue.get(Calendar.MONTH) + 1 + "月" + calendarValue.get(Calendar.DAY_OF_MONTH) + "日");
							
						}
						
					}
					
				}
				else
				{
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					outputStringBuilder.append(simpleDateFormat.format(new Date(timeValue)));
					
				}
				
				writer.write(outputStringBuilder.toString());
			}
			catch (IOException e)
			{
				Logger.getLogger(ConstantConvert.class.getName()).log(Level.SEVERE, null, e);
			}
			
		}
		
		return super.end(writer, body);
	}
	
	private String parse(String value)
	{
		
		String actualValue = null;
		
		if(value == null)
		{
			value = "top";
		}
		
		if(altSyntax())
		{
			if(value.startsWith("%{") && value.endsWith("}"))
			{
				value = value.substring(2, value.length() - 1);
			}
		}
		
		actualValue = (String) findValue(value, String.class);
		if(actualValue == null)
		{
			actualValue = value;
		}
		return actualValue;
	}
	
}
