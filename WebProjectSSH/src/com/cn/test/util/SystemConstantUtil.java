package com.cn.test.util;

import java.io.File;
import java.util.Map;

/**
 * Script Name   : <b>SystemConstantUtil.java</b>.</br>
 * Description   : 系统常量工具类.</br>
 * Type          : JAVA.</br>
 * Last Modified : <b>下午12:27:28</b>.</br>
 * 
 * @since  2011-10-20.</br>
 * @author ZhaoYanLei.</br>
 */
public class SystemConstantUtil {
	
	/**session常量:=================================================================*/
	/*当前登录用户session常量：*/
	public static final String SESSION_LOGIN_USER				= "loginUser";		

	
	/**普通应用常量:=================================================================*/
	/*公文类别树根节点常量*/
	public static final String DOC_CATEGORY_TRER_ROOT_ID 		= "-1";
	public static final String DOC_CATEGORY_TRER_ROOT_NAME 		= "根类别";
	public static final String Organization_TRER_ROOT_NAME 		= "组织机构";
	
	public static final String LeftFrame_TRER_ROOT_ID 		= "0";
	public static final String LeftFrame_TRER_ROOT_NAME 		= "系统菜单";
	

	
	/**发送信息类型：邮件、短信=======================================================*/
	public static final int SEND_MESSAGE_TYPE_MAIL_SMS 			= 1;
	public static final int SEND_MESSAGE_TYPE_MAIL 				= 2;
	public static final int SEND_MESSAGE_TYPE_SMS 				= 3;
	/**/
	
	
	/**
	 * 获得系统日志的业务模块下拉列表的查询条件：
	 */
	public static Map<String,String> getPermissionNameMapData(){
		java.util.HashMap<String,String> map = new java.util.LinkedHashMap<String,String>();	
			
		map.put("用户管理","用户管理");
		map.put("机构管理","机构管理");
		map.put("角色管理","角色管理");
		map.put("参数设置","参数设置");
		map.put("发送邮件","发送邮件");
		map.put("阿里信息", "阿里信息");
//		map.put("接收邮件","接收邮件");
		
		if(SystemConfigUtil.IS_SAVE_SYSTEMLOG_LOGIN == true){
			map.put("系统登录","系统登录");
		}
		if(SystemConfigUtil.IS_SAVE_SYSTEMLOG_LOGOUT == true){
			map.put("系统退出","系统退出");
		}
		return map;
	}
	
	/**
	 * 获得系统日志的业务模块下拉列表的查询条件：
	 */
	public static Map<String,String> getAuditTaskDistributeStatus(){
		java.util.HashMap<String,String> map = new java.util.LinkedHashMap<String,String>();	
			
		map.put("用户管理","用户管理");
		map.put("机构管理","机构管理");
		map.put("角色管理","角色管理");
		map.put("参数设置","参数设置");
		
		
		return map;
	}
}
