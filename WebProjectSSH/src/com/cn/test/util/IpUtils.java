package com.cn.test.util;


/**
 * Script Name   : <b>IPUtils.java</b>.</br>
 * Description   : IP地址转换工具类.</br>
 * Type          : JAVA.</br>
 * Last Modified : <b>下午03:19:31</b>.</br>
 * 
 */
public class IpUtils {

	/**
	 * 将127.0.0.1 形式的IP地址转换成10进制整数，这里没有进行任何错误处理.<br>
	 *
	 * @param strIP
	 * @return 10进制整数
	 */
	public static long ipToLong(String strIP)
	{
		/*IPV6 不知道怎么转*/
		if(strIP.indexOf(":") != -1)
		{
			strIP = "127.0.0.1";
		}
		long[] ip = new long[4];
		int position1 = strIP.indexOf(".");
		int position2 = strIP.indexOf(".", position1 + 1);
		int position3 = strIP.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(strIP.substring(0, position1));
		ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIP.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}
	
	/**
	 * 将10进制整数形式转换成127.0.0.1形式的IP地址.<br>
	 *
	 * @param longIP
	 * @return 127.0.0.1形式的字符串
	 */
	public static String longToIP(long longIP)
	{
		StringBuffer sb = new StringBuffer("");
		
		/* 直接右移24位*/
		sb.append(String.valueOf(longIP >>> 24));
		sb.append(".");
		
		/* 将高8位置0，然后右移16位*/
		sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(longIP & 0x000000FF));
		return sb.toString();
	}
}
