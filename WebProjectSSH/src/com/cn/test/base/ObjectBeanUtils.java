package com.cn.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Locale;

import org.springframework.util.Assert;


public class ObjectBeanUtils {
	/**
	 * ֱ�Ӷ�ȡ��������ֵ,����private/protected���η�,������getter����.
	 */
	public static Object getFieldValue(final Object object,
			final String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");
		}

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("never happend exception!", e);
		}
		return result;
	}

	/**
	 * ֱ�����ö�������ֵ,����private/protected���η�,������setter����.
	 */
	public static void setFieldValue(final Object object,
			final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");
		}

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("never happend exception!", e);
		}
	}

	/**
	 * ѭ������ת��,��ȡ�����DeclaredField.
	 */
	protected static Field getDeclaredField(final Object object,
			final String fieldName) {
		Assert.notNull(object);
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * ѭ������ת��,��ȡ���DeclaredField.
	 */
	@SuppressWarnings("unchecked")
	protected static Field getDeclaredField(final Class clazz,
			final String fieldName) {
		Assert.notNull(clazz);
		Assert.hasText(fieldName);
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field���ڵ�ǰ�ඨ��,��������ת��
			}
		}
		return null;
	}

	/**
	 * ǿ��ת��fileld�ɷ���.
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers())
				|| !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	public static Object getSimpleProperty(Object bean, String propName)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return bean.getClass().getMethod(getReadMethod(propName)).invoke(bean);
	}

	private static String getReadMethod(String name) {
		return "get" + name.substring(0, 1).toUpperCase(Locale.ENGLISH)
				+ name.substring(1);
	}
	
	
	//caba new: ==========================================================||
	
	/**
	 * ��ӡjava������Ϣ��
	 */
	public static String printObject(Object object) {
		return JsonTransformer.serialize(object).replaceAll("\\{", "\r{")
				.replace("]", "\r]\r");
	}
	
	public static <T extends Object> String concat(Collection<T> objects,
			String separator, boolean isNeedLastSeparator) {
		if (null == objects || objects.isEmpty()) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		for (T object : objects) {
			stringBuffer.append(object.toString());
			stringBuffer.append(separator);
		}
		if (!isNeedLastSeparator) {
			for (int i = 0; i < separator.length(); i++) {
				stringBuffer.deleteCharAt(stringBuffer.length() - 1);
			}
		}
		return stringBuffer.toString();
	}
	
	
	/**
	 * ���л�java����ָ��·�����ļ�
	 * @param filePathName
	 */
	public static void objectSerializable(Object data,String filePath,String fileName) throws IOException, ClassNotFoundException{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try{
			File file = new File(filePath);
			// �ж�·���Ƿ���ڣ������ڴ���
			if (!file.exists()) {
				file.mkdirs();
			}
			fos = new FileOutputStream(filePath+fileName);
			oos = new ObjectOutputStream(fos); 
			oos.writeObject(data);
		}catch(IOException ie){
			throw ie;
		}finally{
			if(fos!=null){
				fos.close();
				fos = null;
			}
			if(oos!=null){
				oos.close();
				oos = null;
			}
		}
	}
	
	/**
	 * �����л�java����
	 * @param filePathName
	 * @return
	 @param <SuperClass>
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
	public static Object objectDeSerializable(String filePathName) throws IOException, ClassNotFoundException{
				
		Object obj = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
		 fis = new FileInputStream(filePathName); //"E:/tmp/XStream/8wp623av94qi2nj1h5lb.xml"
		 ois = new ObjectInputStream(fis); 
		obj = ois.readObject();
		}catch(IOException ie){
			throw ie;
		}catch(ClassNotFoundException cnfe){
			throw cnfe;
		}finally{
			if(fis!=null){
				fis.close();
				fis = null;
			}
			if(ois!=null){
				ois.close();
				ois = null;
			}
		}		
		
		//System.out.println("T��ʵ��������: " + obj.getClass().getName());
		 		
		return obj ;
	}

	
	/**
	 * �����л�java����
	 * @param filePathName
	 * @return
	 @param <SuperClass>
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
	public static Object objectDeSerializable(String filePathName,String  className) throws IOException, ClassNotFoundException{
				
		Object obj = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
		 fis = new FileInputStream(filePathName); //"E:/tmp/XStream/8wp623av94qi2nj1h5lb.xml"
		 ois = new ObjectInputStream(fis); 
		obj = ois.readObject();
		}catch(IOException ie){
			throw ie;
		}catch(ClassNotFoundException cnfe){
			throw cnfe;
		}finally{
			if(fis!=null){
				fis.close();
				fis = null;
			}
			if(ois!=null){
				ois.close();
				ois = null;
			}
		}		
		
		//---------------------------||
		
		//System.out.println("T��ʵ��������: " + obj.getClass().getName());
		 		
		return obj ;
	}
}
