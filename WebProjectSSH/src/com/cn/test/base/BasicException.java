package com.cn.test.base;

import java.util.HashMap;
import java.util.Map;


/**
 * java�쳣����
 * @author jackzhao
 *
 */
public class BasicException extends RuntimeException{
public final static long	serialVersionUID				= 12345678;
	
	private boolean				debug							= true;
	private String				trace;
	private int					code							;//= MSG_ID_SYSTEM_ERROR;	
	
	public static final Map<String,String>		EXCEPTION_CONFIG_MAP	= new HashMap();	
	
	/**
	 * initExceptionConfig
	 */
	private void initExceptionConfig()
	{
		if(EXCEPTION_CONFIG_MAP!=null && EXCEPTION_CONFIG_MAP.size()<=0){
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_"+BasicException.MSG_ID_SYSTEM_ERROR, "ϵͳ����");
				
			/**ϵͳ�쳣-90�쳣=======================================================*/
			/*��ͨ�쳣*/
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_SYSTEM_NORMALEXCEPTION	, "��ͨ�쳣");
			/* ϵͳ���� */
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_SYSTEM_ERROR, "ϵͳ����");
			
			/* ��ݲ����� */
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_DATA_NOTFOUND, "��ݲ�����");
			/* ����Ѵ��� */
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_DATA_EXIST	, "����Ѵ��� ");
			
			/* ������ݿ�sql�쳣 */
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_DATABASE_SQL_EXCEPTION	, "������ݿ�sql�쳣 ");		
			
			/* ��֧�ֵı���ת���쳣 */
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_ERROR_UNSUPPORTED_ENCODING , "��֧�ֵı���ת���쳣");
			
			/**ϵͳ����-10�쳣===================================================*/
			
			/* Ȩ������֮�����Ľ�ɫ��������ɾ�� */
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_OPERATION_CAN_NOT_DELETE	, "Ȩ������֮�����Ľ�ɫ��������ɾ�� ");
			/* ��ɫ����֮�������û���������ɾ�� */
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_ROLE_CAN_NOT_DELETE	, "��ɫ����֮�������û���������ɾ��");
			
					
			/**�������-40�쳣=======================================================*/
			
			/* �ϴ������쳣 */
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_UPLOAD_ERROR	, "�ϴ������쳣");
			/* ���ظ����쳣 */
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_ERROR_DOWN	, "���ظ����쳣");
			/* �����ʼ��쳣 */
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_ERROR_MESSAGE_MAIL, "�����ʼ��쳣");
			/* ���Ͷ����쳣 */
			EXCEPTION_CONFIG_MAP.put("MSG_KEY_" + MSG_ID_ERROR_MESSAGE_SMS, "���Ͷ����쳣");
			/**=====================================================================*/
			
			
			
		}else{
			//EXCEPTION_CONFIG_MAP	= new HashMap();	
		}
		
	}
	/**ϵͳ�쳣-90�쳣=======================================================*/
	/*��ͨ�쳣*/
	public static final int		MSG_ID_SYSTEM_NORMALEXCEPTION	= 9000;	
	/* ϵͳ���� */
	public static final int		MSG_ID_SYSTEM_ERROR				= 9001;
	
	/* ��ݲ����� */
	public static final int		MSG_ID_DATA_NOTFOUND			= 9002;
	/* ����Ѵ��� */
	public static final int		MSG_ID_DATA_EXIST				= 9003;	
	
	/* ������ݿ�sql�쳣 */
	public static final int		MSG_ID_DATABASE_SQL_EXCEPTION	= 9004;		
	
	/* ��֧�ֵı���ת���쳣 */
	public static final int	  MSG_ID_ERROR_UNSUPPORTED_ENCODING = 9005;	
	
	/**ϵͳ����-10�쳣===================================================*/
	
	/* Ȩ������֮�����Ľ�ɫ��������ɾ�� */
	public static final int		MSG_ID_OPERATION_CAN_NOT_DELETE	= 1001;
	/* ��ɫ����֮�������û���������ɾ�� */
	public static final int		MSG_ID_ROLE_CAN_NOT_DELETE		= 1002;
	
	
	
	/**�������-40�쳣=======================================================*/
	/* �ϴ������쳣 */
	public static final int		MSG_ID_UPLOAD_ERROR				= 4001;	
	/* ���ظ����쳣 */
	public static final int		MSG_ID_ERROR_DOWN				= 4002;	
	/* �����ʼ��쳣 */
	public static final int		MSG_ID_ERROR_MESSAGE_MAIL		= 4003;
	/* ���Ͷ����쳣 */
	public static final int		MSG_ID_ERROR_MESSAGE_SMS		= 4004;
	/**=====================================================================*/
	
	/* IP��Դ����֮������IP���䣬������ɾ�� */
	public static final int		MSG_IPORIGIN_CAN_NOT_DELETE	= 5001;
	
	/* �����Ѿ�����Ķ������������ٴη��� */
	public static final int		MSG_BATSK_CAN_NOT_DISTRIBUTE	= 6001;
	/* δ���������Ķ�����������ȡ����� */
	public static final int		MSG_BATSK_CAN_NOT_CANCELDISTRIBUTE	= 6002;
	
	/**
	 * getExceptionConfig
	 */
	public static String getExceptionConfig(int messageKey){
		
		return EXCEPTION_CONFIG_MAP.get("MSG_KEY_" + messageKey);
		
	}
	
	public BasicException()
	{
		super();
		init();
	}
	
	public BasicException(String s)
	{
		super(s);
		init();
	}
	
	public BasicException(int code, String s)
	{
		super(s);
		this.code = code;
		init();
	}
	
	public int getCode()
	{
		return this.code;
	}
	
	public String getMessage()
	{
//		return (debug ? trace : "") + super.getMessage();
		return  super.getMessage();
	}
	
	public String getMessageForDebug()
	{
		return (debug ? trace : "") + super.getMessage();
	}
	
	private void init()
	{
		
		StackTraceElement traces[] = getStackTrace();
		String className = traces[0].getClassName();
		int n = className.lastIndexOf('.');
		if(n > 0)
			className = className.substring(n + 1);
		trace = className + "." + traces[0].getMethodName() + "[line: " + traces[0].getLineNumber() + "]: ";
	
		/*initExceptionConfig*/
		initExceptionConfig();
	}

}
