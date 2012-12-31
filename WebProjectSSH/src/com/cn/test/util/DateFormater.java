package com.cn.test.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateFormater {
	
	/**
	 * * ������ת��Ϊ�ַ� �ַ��ʽ("YYYY-MM-DD")��Сʱ���֡��뱻���ԡ�
	 */
	public static String DateToString(Date date)
	{
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String strDateTime = "";
		if (date != null)
		{
			strDateTime = formater.format(date);
		}
		return strDateTime;
	}

	/**
	 * * ������ת��Ϊ�ַ�
	 * 
	 * @param Date
	 *            ����
	 * @param pattern
	 *            ���ڸ�ʽ
	 * @return String ����
	 */
	public static String DateToString(Date date, String pattern) throws Exception
	{
		String strDateTime = null;
		try
		{
			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(pattern);
			strDateTime = formater.format(date);
		} catch (Exception ex)
		{
			throw ex;
		}
		return strDateTime;
	}

	/**
	 * �������������ת��ΪDate����
	 * 
	 * @param year
	 *            ��
	 * @param month
	 *            ��
	 * @param day
	 *            ��
	 * @return Date����
	 */
	public static Date YmdToDate(int year, int month, int day)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar.getTime();
	}

	/**
	 * ������ת��Ϊ�ַ� �ַ��ʽ("YYYY-MM-DD")��Сʱ���֡��뱻���ԡ�
	 */
	public static String communityDateToString(Date date)
	{
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("MM/dd HH:mm:ss");
		String strDateTime = formater.format(date);
		return strDateTime;
	}

	/**
	 * ���ַ�ת��Ϊ���ڡ� �ַ��ʽ("YYYY-MM-DD")��
	 * ���磺"2002-07-01"����"2002-7-1"����"2002-7-01"����"2002-07-1"�ǵȼ۵ġ�
	 */
	public static Date StringToDate(String str)
	{
		Date dateTime = null;
		try
		{
			if (!(str == null || str.equals("")))
			{
				java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("yyyy-MM-dd");
				dateTime = formater.parse(str);
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return dateTime;

	}

	/**
	 * ����ʱ���ʱ�����Timestamp��ʾ
	 * 
	 * @return Timestamp
	 */
	public static Timestamp StringToDateHMS(String str) throws Exception
	{
		Timestamp time = null;
		try
		{
			time = java.sql.Timestamp.valueOf(str);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw ex;
		}

		return time;

	}

	/**
	 * ȡ��һ��date�����Ӧ�����ڵ�0��0��0��ʱ�̵�Date����
	 * 
	 * @param date
	 *            һ������
	 * @return Date����
	 */
	public static Date getMinDateOfDay(Date date)
	{
		if (date == null)
		{
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

		return calendar.getTime();
	}

	/**
	 * ȡ��һ��date�����Ӧ�����ڵ�23��59��59��ʱ�̵�Date����
	 * 
	 * @param date
	 *            һ������
	 * @return Date����
	 */
	public static Date getMaxDateOfDay(Date date)
	{
		if (date == null)
		{
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

		return calendar.getTime();
	}

	/**
	 * ���մ����ʽת����Date
	 * @param str
	 * @param dateFormat
	 * @return
	 * @author 
	 */
	public static Date StringToDateByFormat(String str,String dateFormat)
	{
		Date dateTime = null;
		try
		{
			if (!(str == null || str.equals("")))
			{
				java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(dateFormat);
				dateTime = formater.parse(str);
			}
		} catch (Exception ex)
		{
			System.err.println("#####DateFormater$StringToDateByFormat:��鴫������ڸ�ʽ�Ƿ���ȷ");
			ex.printStackTrace();
		}

		return dateTime;

	}

}
