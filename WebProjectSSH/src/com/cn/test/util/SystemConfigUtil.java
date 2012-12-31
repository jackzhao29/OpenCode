package com.cn.test.util;
/**
 * Script Name   : <b>SystemConfigUtil.java</b>.</br>
 * Description   : 系统配置参数工具类.</br>
 * Type          : JAVA.</br>
 * Last Modified : <b>上午11:02:13</b>.</br>
 * 
 * @since  2011-10-29.</br>
 * @author ZhaoYanLei.</br>
 */
public class SystemConfigUtil {

	/**系统调试参数：================================================================*/	
	public static final boolean IS_SYSTEM_OUT = 				true;	
	/*发布时需要设置为false：*/
	public static final boolean IS_SYSTEM_OUT_EXCEPTION = 		true;
	
	/**系统参数：====================================================================*/	
	/*系统是否支持邮件发送*/
	public static final boolean IS_SEND_MEIL = 					true;
	
	/*系统是否支持短信发送*/
	public static final boolean IS_SEND_SMS = 					true;
	
	/*是否授予admin系统管理权限*/
	public static final boolean IS_ADMIN_AUTHORITY  = 			true;
	

	
	/**系统操作日志保存设置情况：=====================================================*/	
	/*是否记录系统登录日志*/
	public static final boolean IS_SAVE_SYSTEMLOG_LOGIN = 		false;

	/*是否记录系统退出日志*/
	public static final boolean IS_SAVE_SYSTEMLOG_LOGOUT =		false;
	

	/**如下参数,发布时需要设置为true,开发时需要设置为false：============================*/
	/*是否开启按钮权限*/
	public static final boolean IS_open_authority_button 		= 		false;	
	/*是否统一配置了编码格式*/
	public static final boolean IS_CHAR_ENCODING_CONFIG         = 		false;	
		
	
	/** 下列参数正式发布时，需重新设置： ===============================================*/
	//是否进行工商信息自动审核，否则由工商信息审核专员进行审核：	
	public static boolean isAliavTaskNode_AutoAudit_business_license = 				false;//正式发布：false
	
	/** webbash 工程正式发布时，下列参数必须重新设置： ===============================================*/
	
	//阿里webservice是否进行本地测试：	
	public static boolean CaSoapConfig_isLocalTest = 				false;//正式发布：false
	//是否调用阿里webservice，上报阿里已认证完毕的AV数据：	
	public static boolean CaSoapConfig_isUploadCaAvTaskFinish = 	true;//正式发布：true
	
	//是否调用AliSendAVInfo webservice，验证header信息：	
	public static boolean isAliSendAVInfo_ValidateHeader = 			true;//正式发布：true
	
	//是否调用阿里AliReceiveAVInfo webservice，验证header信息：	
	public static boolean isAliReceiveAVInfo_ValidateHeader = 		true;//正式发布：true
	
	/** 下列参数正式发布时，需重新设置： ===============================================*/
	public static final boolean IS_DEPLOY_OS_LINUX = 				true;//正式发布：true
	
	//阿里下单时调用AliSendAVInfo webservice接口，是否进行重复订单拒收核验：	
	public static boolean isAliSendAVInfo_Check_MergeOrder = 	false;//正式发布：false(应该为true)
	//是否向阿里上报重复订单（在上报列表中是否显示重复订单）：	
	public static boolean isAliReceiveAVInfo_Upload_MergeOrder = 	true;//正式发布：true(应该为false)
}
