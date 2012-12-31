package com.cn.test.base;

import java.util.HashSet;
import java.util.Set;



/**
 * ���¶�����
 * 
 * �ṩ���ָ���ģʽ��MAX, MIN, MIDDLE
 * <ul>
 * <li>MIDDLE��Ĭ��ģʽ������null�⣬�����¡�exclude��include���⡣</li>
 * <li>MAX����󻯸���ģʽ�������ֶζ����£�����null����exclude���⡣</li>
 * <li>MIN����С������ģʽ�������ֶζ������¡�include���⡣</li>
 * </ul>

 * 
 */
public class Updater<T> {
	/**
	 * ������
	 * 
	 * @param bean
	 *            ����¶���
	 */
	public Updater(T bean) {
		this.bean = bean;
	}

	/**
	 * ������
	 * 
	 * @param bean
	 *            ����¶���
	 * @param mode
	 *            ����ģʽ
	 * @return
	 */
	public Updater(T bean, UpdateMode mode) {
		this.bean = bean;
		this.mode = mode;
	}

	/**
	 * ���ø���ģʽ
	 * 
	 * @param mode
	 * @return
	 */
	public Updater<T> setUpdateMode(UpdateMode mode) {
		this.mode = mode;
		return this;
	}

	/**
	 * ������µ��ֶ�
	 * 
	 * @param property
	 * @return
	 */
	public Updater<T> include(String property) {
		includeProperties.add(property);
		return this;
	}

	/**
	 * �����µ��ֶ�
	 * 
	 * @param property
	 * @return
	 */
	public Updater<T> exclude(String property) {
		excludeProperties.add(property);
		return this;
	}

	/**
	 * ĳһ�ֶ��Ƿ����
	 * 
	 * @param name
	 *            �ֶ���
	 * @param value
	 *            �ֶ�ֵ�����ڼ���Ƿ�ΪNULL
	 * @return
	 */
	public boolean isUpdate(String name, Object value) {
		if (this.mode == UpdateMode.MAX) {
			return !excludeProperties.contains(name);
		} else if (this.mode == UpdateMode.MIN) {
			return includeProperties.contains(name);
		} else if (this.mode == UpdateMode.MIDDLE) {
			if (value != null) {
				return !excludeProperties.contains(name);
			} else {
				return includeProperties.contains(name);
			}
		} else {
			// never reach
		}
		return true;
	}

	private T bean;

	private Set<String> includeProperties = new HashSet<String>();

	private Set<String> excludeProperties = new HashSet<String>();

	private UpdateMode mode = UpdateMode.MIDDLE;

	// private static final Logger log = LoggerFactory.getLogger(Updater.class);

	public static enum UpdateMode {
		MAX, MIN, MIDDLE
	}

	public T getBean() {
		return bean;
	}

	public Set<String> getExcludeProperties() {
		return excludeProperties;
	}

	public Set<String> getIncludeProperties() {
		return includeProperties;
	}

}
