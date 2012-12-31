package com.cn.test.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cn.test.util.SystemConfigUtil;


public class StringUtil {
	/*
	 * ~ Static fields/initializers
	 * =============================================
	 */

	private final static Log log = LogFactory.getLog(StringUtil.class);

	/*
	 * ~ Methods
	 * ================================================================
	 */

	/**
	 * Encode a string using algorithm specified in web.xml and return the
	 * resulting encrypted password. If exception, the plain credentials string
	 * is returned
	 * 
	 * @param password
	 *            Password or other credentials to use in authenticating this
	 *            username
	 * @param algorithm
	 *            Algorithm used to do the digest
	 * 
	 * @return encypted password based on the algorithm.
	 */
	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try {
			/* first create an instance, given the provider */
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			log.error("Exception: " + e);

			return password;
		}

		md.reset();

		/* call the update method one or more times */
		/* (useful when you don't know the size of your data, eg. stream) */
		md.update(unencodedPassword);

		/* now calculate the hash */
		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	/**
	 * Encode a string using Base64 encoding. Used when storing passwords as
	 * cookies.
	 * 
	 * This is weak encoding in that anyone can use the decodeString routine to
	 * reverse the encoding.
	 * 
	 * @param str
	 * @return String
	 */
	public static String encodeString(String str) {
		return str;
	}

	/**
	 * Decode a string using Base64 encoding.
	 * 
	 * @param str
	 * @return String
	 */
	public static String decodeString(String str) {
		return str;
	}

	public static String trafficParam(String parameter) {
		if (StringUtils.isNotBlank(parameter)) {
			parameter = parameter.replaceAll("%", "\\\\\\%");
			parameter = parameter.replaceAll("_", "\\\\\\_");
		}

		return parameter;
	}

	/**
	 * 
	 * @param parameter
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String charEncodingTransform(String parameter)
			throws UnsupportedEncodingException {
		if (!SystemConfigUtil.IS_CHAR_ENCODING_CONFIG) {
			if (!StringUtil.isNullOrEmpty(parameter)) {
				parameter = new String(parameter.trim().getBytes("ISO-8859-1"),
						"UTF-8");
				// parameter = new
				// String(parameter.trim().getBytes("ISO-8859-1"),"GBK");
			}
		}
		return parameter;
	}

	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 * 
	 * 方法：String s = "你要去除的字符串"; 1.去除空格：s = s.replace('\\s',''); 2.去除回车：s =
	 * s.replace('\n','');
	 * 
	 * 注：\n 回车( ) \t 水平制表符( ) \s 空格(\u0008) \r 换行( )
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}

		return dest;
	}

	// ================================================||
	/**
	 * ZhaoYanLei
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(Object str) {
		if (str == null || str.toString().trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ZhaoYanLei
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNullOrEmpty(Object str) {
		if (str == null || str.toString().trim().length() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static String change(String str) {
		String tempStr = null;
		try {
			tempStr = new String(str.getBytes(), "ISO-8859-1");
			tempStr = tempStr.trim();
			tempStr = new String(str.getBytes(), "gb2312");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempStr;

	}


	/**
	 * 方法描述：<b>	 最底层字符串编码转换的实现方法 .</b></br>
	 * 备          注:  oldCharset 为"GB2312";	newCharset 为“ISO-8859-1” 	</br>
	 * 创  建   人: yanlei.zhao</br>
	 * 创建日期: 2012-1-12</br>
	 * @param str
	  *            待转换的字符串
	  * @param oldCharset
	  *            源字符集
	  * @param newCharset
	  *            目标字符集
	  * @return 转换后的字符串
	 * @return
	 */
	 public static String changeCharset(String str, String oldCharset, String newCharset) {
	  if (str != null) {
	   // 用源字符编码解码字符串
	   try {
	    return new String(str.getBytes(oldCharset), newCharset);
	   } catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	   }
	  }
	  return null;
	 }
	 /**
	  * 方法描述：<b>.</b></br>
	  * 备          注: </br>
	  * 创  建   人: zyl</br>
	  * 创建日期: 2012-2-21</br>
	  * @return
	  */
	public static boolean isEquals(String str1,String str2)
	{
		boolean isEqu = false;
		
		if(StringUtil.isNullOrEmpty(str1) && StringUtil.isNullOrEmpty(str2)){
			isEqu = true;
		}else if(StringUtil.isNotNullOrEmpty(str1) && StringUtil.isNotNullOrEmpty(str2)
				&&(str1.equals(str2))){
			isEqu = true;
		}else{
			isEqu = false;
		}
		
		return isEqu;
	}
	 /**
	  * 方法描述：<b>.</b></br>
	  * 备          注: </br>
	  * 创  建   人: zyl</br>
	  * 创建日期: 2012-2-21</br>
	  * @return
	  */
	public static boolean isEqualsIgnoreCase(String str1,String str2)
	{
		boolean isEqu = false;
		
		if(StringUtil.isNullOrEmpty(str1) && StringUtil.isNullOrEmpty(str2)){
			isEqu = true;
		}else if(StringUtil.isNotNullOrEmpty(str1) && StringUtil.isNotNullOrEmpty(str2)
				&&(str1.equalsIgnoreCase(str2))){
			isEqu = true;
		}else{
			isEqu = false;
		}
		
		return isEqu;
	}
	
	/**
	 * 方法描述：<b>验证字符串是否在字符串集合中.</b></br>
	 * 备          注: </br>
	 * 创  建   人: zyl</br>
	 * 创建日期: 2012-3-16</br>
	 * @param str:10.10.30.57
	 * @param strSet：System_AuthorityFilter_Client_ip=114.255.88.90,10.10.30.*,10.10.30.57
	 * @param strSeparator：,
	 * @return
	 */
	public static boolean isStringInList(String str ,String strSet,String strSeparator){
		boolean isContain = false;
		
		if(null != strSet) {			
			String [] strArrays ;
			
			if(StringUtil.isNotNullOrEmpty(strSeparator)){
				strArrays = strSet.split(strSeparator);
			}else{
				strArrays = strSet.split(",");
			}
			
			List<String> strList = null;
			if(null != strArrays) {
				strList = Arrays.asList(strArrays);				
			}
						
			if(StringUtil.isNotNullOrEmpty(str) && strList!=null){					
				isContain = isStringInList( str ,strList);
			}		
		}		
		return isContain;		
	}
	
	/**
	 * 方法描述：<b>验证字符串是否在字符串集合中.</b></br>
	 * 备          注: </br>
	 * 创  建   人: zyl</br>
	 * 创建日期: 2012-3-16</br>
	 * @param str:10.10.30.57
	 * @param strSet：System_AuthorityFilter_Client_ip=114.255.88.90,10.10.30.*,10.10.30.57
	 * @param strSeparator：,
	 * @return
	 */
	public static boolean isStringInList(String str ,List<String> strSet){
		boolean isContain = false;		
		if(null != strSet) {									
			if(StringUtil.isNotNullOrEmpty(str) && strSet!=null){					
					for(String strTmp:strSet){
						//119.38.217.*
						if(strTmp.indexOf(".*")>0){
							String strSub = str.substring(0, strTmp.lastIndexOf(".*"));
							String strTmpSub = strTmp.substring(0, strTmp.lastIndexOf("."));
							
							if(strTmpSub.equalsIgnoreCase(strSub)){
								isContain = true;
							}					
						}else{
							if(StringUtil.isEqualsIgnoreCase(strTmp, str)){
								isContain = true;
							}
						}
				}

			}		
		}		
		return isContain;		
	}
}
