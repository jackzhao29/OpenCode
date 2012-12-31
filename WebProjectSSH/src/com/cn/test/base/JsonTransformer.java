package com.cn.test.base;

import java.util.Date;

import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;

import com.cn.test.util.DateFormater;





public class JsonTransformer {
	private static final Log log = LogFactory.getLog(JsonTransformer.class);
	/**
	 * ��object���л���Json
	 * @param object
	 * @return
	 */
	public static String serialize(Object object) {
		try{
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(java.util.Date.class,
					new DatetimeProcess());
			config.registerJsonValueProcessor(java.sql.Timestamp.class,
					new DatetimeProcess());
			config.registerJsonValueProcessor(java.sql.Date.class,
					new DateProcess());
			//������ѭ��
			config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			config.addIgnoreFieldAnnotation(Ignore.class);
			return JSONSerializer.toJSON(object, config).toString();
		}catch(Exception e) {
			log.error("JsonTransformer.serialize(Object object)---->>   ��ӡ��־ʧ��",e);
			System.out.println("JsonTransformer.serialize(Object object)---->>   ��ӡ��־ʧ��");
			//e.printStackTrace();
		}
		
		return null;
	}

	public static class DatetimeProcess implements JsonValueProcessor {
		public Object processObjectValue(String key, Object value,
				JsonConfig config) {
			return DateFormater.DateToString((Date) value);
		}

		public Object processArrayValue(Object value, JsonConfig config) {
			return DateFormater.DateToString((Date) value);
		}
	};

	public static class DateProcess implements JsonValueProcessor {

		public Object processObjectValue(String key, Object value,
				JsonConfig config) {
			return DateFormater.DateToString((Date) value);
		}

		public Object processArrayValue(Object value, JsonConfig config) {
			return DateFormater.DateToString((Date) value);
		}
	};

}
