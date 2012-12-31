package com.cn.test.base;

import static org.hibernate.EntityMode.POJO;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.cn.test.base.Updater;

import com.cn.test.base.GenericDaoHibernate;
import com.cn.test.base.ObjectBeanUtils;
import com.hichina.caba.common.util.pagination.Pagination;



/**
 * hibernate DAO基类
 * 
 * 提供hql分页查询，拷贝更新等一些常用功能。
 * 
 * @param <SuperClass>
 *            entity class
 * @param <ID>
 *            entity id
 */
public abstract class BaseDaoHibernate<SuperClass, ID extends Serializable> extends
		GenericDaoHibernate {
	/**
	 * @see Session.get(Class,Serializable)
	 * @param id
	 * @return 持久化对象
	 */
	protected SuperClass get(ID id) {
		return get(id, false);
	}
	/**
	 * @see Session.load(Class,Serializable)
	 * @param id
	 * @return 持久化对象
	 */
	protected SuperClass load(ID id) {
		return get(id, false);
	}

	/**
	 * @see Session.get(Class,Serializable,LockMode)
	 * @param id
	 *            对象ID
	 * @param lock
	 *            是否锁定，使用LockMode.UPGRADE
	 * @return 持久化对象
	 */
	@SuppressWarnings("unchecked")
	protected SuperClass get(ID id, boolean lock) {
		SuperClass entity;
		if (lock) {
			entity = (SuperClass) getSession().get(getEntityClass(), id,
					LockMode.UPGRADE);
		} else {
			entity = (SuperClass) getSession().get(getEntityClass(), id);
			
		}
		return entity;
	}

	/**
	 * @see Session.get(Class,Serializable,LockMode)
	 * @param id
	 *            对象ID
	 * @param lock
	 *            是否锁定，使用LockMode.UPGRADE
	 * @return 持久化对象
	 */
	@SuppressWarnings("unchecked")
	protected SuperClass load(ID id, boolean lock) {
		SuperClass entity;
		if (lock) {
			entity = (SuperClass) getSession().get(getEntityClass(), id,
					LockMode.UPGRADE);
		} else {
			
			entity = (SuperClass) getSession().load(getEntityClass(), id);
		}
		return entity;
	}
	/**
	 * 按属性查找对象列表
	 */
	@SuppressWarnings("unchecked")
	protected List<SuperClass> findByProperty(String property, Object value) {
		Assert.hasText(property);
		return createCriteria(Restrictions.eq(property, value)).list();
	}

	/**
	 * 按属性查找唯一对象
	 */
	@SuppressWarnings("unchecked")
	protected SuperClass findUniqueByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return (SuperClass) createCriteria(Restrictions.eq(property, value))
				.uniqueResult();
	}

	/**
	 * 按属性统计记录数
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	protected int countByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return ((Number) (createCriteria(Restrictions.eq(property, value))
				.setProjection(Projections.rowCount()).uniqueResult()))
				.intValue();
	}

	/**
	 * 按Criterion查询列表数据.
	 * 
	 * @param criterion
	 *            数量可变的Criterion.
	 */
	@SuppressWarnings("unchecked")
	protected List findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	/**
	 * 通过Updater更新对象
	 * 
	 * @param updater
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SuperClass updateByUpdater(Updater<SuperClass> updater) {
		ClassMetadata cm = sessionFactory.getClassMetadata(getEntityClass());
		SuperClass bean = updater.getBean();
		SuperClass po = (SuperClass) getSession().get(getEntityClass(),
				cm.getIdentifier(bean, POJO));
		updaterCopyToPersistentObject(updater, po, cm);
		return po;
	}

	/**
	 * 将更新对象拷贝至实体对象，并处理many-to-one的更新。
	 * 
	 * @param updater
	 * @param po
	 */
	private void updaterCopyToPersistentObject(Updater<SuperClass> updater, SuperClass po,
			ClassMetadata cm) {
		String[] propNames = cm.getPropertyNames();
		String identifierName = cm.getIdentifierPropertyName();
		SuperClass bean = updater.getBean();
		Object value;
		for (String propName : propNames) {
			if (propName.equals(identifierName)) {
				continue;
			}
			try {
				value = ObjectBeanUtils.getSimpleProperty(bean, propName);
				if (!updater.isUpdate(propName, value)) {
					continue;
				}
				cm.setPropertyValue(po, propName, value, POJO);
			} catch (Exception e) {
				throw new RuntimeException(
						"copy property to persistent object failed: '"
								+ propName + "'", e);
			}
		}
	}

	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
	 */
	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 获得Dao对于的实体类
	 * 
	 * @return
	 */
	abstract protected Class<SuperClass> getEntityClass();
	
	//zyl:caba:20111103============================================================\\
	/**
	 * zyl:
	 * @param obj
	 * @return
	 */
	public SuperClass insertObj(SuperClass obj) {
		getSession().save(obj);
		return obj;
	}
	
	public SuperClass updateObj(SuperClass obj) {
		return updateByUpdater(new Updater(obj));
	}
	
	
	public SuperClass findByObjId(ID id) {
		SuperClass baseUser = (SuperClass) get(id);
		
		//采用load方法加载对象: 20120822   zyl
		//SuperClass baseUser = (SuperClass) load(id);
		return baseUser;
	}
	
	public void deleteObjById(ID id) {
		SuperClass entity = get(id);
		if (entity != null) {
			getSession().delete(entity);
		}		
	}
	//mingming:gein_ba:20120719============================================================//
	/**
	 * 分页查询函数，使用sql.
	 * 
	 * @param pageNo
	 *            页号,从1开始.
	 */
	@SuppressWarnings("unchecked" )
	public Pagination pagedSqlQuery(String sql, Integer pageNo, Integer pageSize, Object ... values)
	{
		if(pageNo == null || pageSize == null)
		{
			List list = createSqlQuery(sql, values).list();
			if(list == null || list.size() == 0)
			{
				return new Pagination();
			}
			else
			{
				return new Pagination(0, list.size(), list.size(), list);
			}
		}
		Assert.hasText(sql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count(*) " + removeSelect(sql);
		
		int count = 0;
		
		List countList;
		// 如果有order 且order后 存在参数;
		if(sql.indexOf("order") != -1 && (sql.substring(sql.indexOf("order")).indexOf("?")) != -1)
		{
			String sqlTemp = sql.substring(sql.indexOf("order"));
			// 数出 order 后面参数的个数;
			while (sqlTemp.indexOf("?") >= 0)
			{
				sqlTemp = sqlTemp.substring(sqlTemp.indexOf("?") + 1);
				count++;
			}
			Object[] valuesCountParam = new Object[values.length - count];
			// 生成select count(*)的参数;
			for (int i = 0; i < values.length - count; i++)
			{
				valuesCountParam[i] = values[i];
			}
			countList = findBySql(countQueryString, valuesCountParam);
		}
		else
		{
			countList = findBySql(countQueryString, values);
		}
		// int totalCount = ((Long) countlist.get(0)).intValue();
		int totalCount = Integer.parseInt(countList.get(0).toString());
		if(totalCount < 1)
			return new Pagination();
		// 实际查询返回分页对象
		int startIndex = (pageNo - 1) * pageSize;
		SQLQuery sqlQuery = createSqlQuery(sql, values);
		sqlQuery.setFirstResult(startIndex);
		sqlQuery.setMaxResults(pageSize);
		List<SuperClass> list = sqlQuery.list();
		
		return new Pagination(startIndex, pageSize,totalCount, list);
	}
	
	public SQLQuery createSqlQuery(String sql, Object ... values)
	{
		Assert.hasText(sql);
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		for (int i = 0; i < values.length; i++)
		{
			sqlQuery.setParameter(i, values[i]);
		}
		return sqlQuery;
	}
	
	/**
	 * 关于 sql的findby 方法; pagedSqlQuery 调用;
	 * 
	 * @param countQueryString
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	public List findBySql(final String countQueryString, final Object[] values) throws DataAccessException
	{
		Assert.hasText(countQueryString, "countQueryString must not be null");
		Assert.hasText(countQueryString);
		Query queryObject = getSession().createSQLQuery(countQueryString);
		if(values != null)
		{
			for (int i = 0; i < values.length; i++)
			{
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.list();
	}
	
	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql)
	{
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find())
		{
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery. 如果有“fetch”，则去掉
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql)
	{
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos).replace("fetch", "");
	}
}

