package com.cn.test.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.cn.test.base.BaseDaoHibernate;
import com.cn.test.base.BasicException;
import com.cn.test.base.Finder;
import com.cn.test.base.Updater;
import com.cn.test.dao.UserDao;
import com.cn.test.model.UserInfo;
import com.hichina.caba.common.util.pagination.PageQueryParam;
import com.hichina.caba.common.util.pagination.Pagination;

public class UserDaoImpl extends BaseDaoHibernate<UserInfo,Integer> implements UserDao {

	Timestamp currentTime=new Timestamp(System.currentTimeMillis());
	/**
	 * 保存
	 */
	public UserInfo saveUser(UserInfo userinfo) throws BasicException
	{
		//UserInfo users=new UserInfo();
		try
		{
			
			userinfo.setDatecreate(currentTime);
			userinfo=super.insertObj(userinfo);
			//SessionFactory sf=new Configuration().configure().buildSessionFactory();
			//Session session=sf.openSession();
			//Session session=sessionFactory.getCurrentSession();
			//Transaction tx=session.beginTransaction();
			//session.save(userinfo);
			//tx.commit();
			//session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	    return userinfo;
	}
	
	/**
	 * 根据登录名查询是否存在返回对象(按属性查找)
	 */
	public UserInfo findUserInfoByLoginName(String loginName) throws BasicException
	{
		if(null==loginName)
			return null;
		return findUniqueByProperty("username",loginName);
	}
	
	/**
	 * 根据userid查询数据
	 */
	public UserInfo findUserInfoByUserId(Integer userId) throws BasicException
	{
		if(0==userId)
			return null;
		return findUniqueByProperty("userid",userId);
	}
	
	/**
	 * 根据域名查询是否存在返回对象集合
	 */
	public List<UserInfo> findUserInfoListByLoginName(String loginName) throws BasicException
	{
		if(null==loginName)
			return null;
		String hql="select bean from UserInfo bean where bean.username='"+loginName+"'";
		return find(hql);
	}
	
	/**
	 * 查询
	 */
	public List<UserInfo> findUserInfoList() throws BasicException
	{
		List<UserInfo> listUserInfo=null;
		try
		{
			String hql="select userinfobean from UserInfo userinfobean";
			listUserInfo=find(hql);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return listUserInfo;
	}
	
	/**
	 * 分页查询(数据源Pagination)
	 */
	public Pagination findUserInfoAll(PageQueryParam pageQueryParam,UserInfo userinfo) throws BasicException
	{
		Finder f=Finder.create("select bean from UserInfo bean where 1=1");
		if(null!=userinfo)
		{
			if(userinfo.getUsername()!=null)
			{
				f.append(" and bean.username=:username");
				f.setParam("username", userinfo.getUsername());
			}
			if(userinfo.getUserpwd()!=null)
			{
				f.append(" and bean.userpwd=:userpwd");
				f.setParam("userpwd", userinfo.getUserpwd());
			}
		}
		f.append(" order by bean.datecreate desc");
	    return find(f,pageQueryParam.pageNo,pageQueryParam.pageSize);
		

	}
	
	/**
	 * 查询分页数据(数据源List<UserInfo>)
	 * @param pageNo
	 * @param pageSize
	 * @param userName
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws BasicException
	 */
	public Pagination findUserInfoList(Integer pageNo,Integer pageSize,String userName,String startDate,String endDate) throws BasicException
	{
		StringBuffer sb=new StringBuffer();
		sb.append("select * from userinfo bean where 1=1");
	   
          if(userName!=null)
          {
        	  sb.append(" and bean.username='"+userName+"'");
          }
          if(startDate!=null)
          {
        	  sb.append(" and bean.datecreate >='"+startDate+"'");  
          }
          if(endDate!=null)
          {
        	  sb.append(" and bean.datecreate <='"+endDate+"'");
          }
	    sb.append(" order by bean.datecreate desc");
	    Pagination p = pagedSqlQuery(sb.toString(), pageNo, pageSize);
		return convertPaginationToPaginationUserInfo(p);
	  
	}
	
	/**
	 * 根据userId删除信息
	 */
	public void deleteUserInfoByUserId(Integer userId) throws BasicException
	{
		if(0!=userId)
		{
		  String hql="delete from UserInfo bean where bean.userid="+userId;
		  Query query=getSession().createQuery(hql);
		  query.executeUpdate();
		}
	}
	
	/**
	 * 更新信息
	 */
	public UserInfo modifyUserInfoByUserId(UserInfo userinfo) throws BasicException
	{
		userinfo.setDateupdate(currentTime);
		//String hql="update UserInfo set username=?,userpwd=? where userid=?";
		//Query query=getSession().createQuery(hql);
		//query.executeUpdate();
		Updater<UserInfo> updater=new Updater<UserInfo>(userinfo);
		UserInfo u=super.updateByUpdater(updater);
		return u;
	}
	
	private Pagination convertPaginationToPaginationUserInfo(Pagination pagination)
	{
		Pagination paginationUserInfo=new Pagination();
		paginationUserInfo.setPageNo(pagination.getPageNo());
		paginationUserInfo.setPageSize(pagination.getPageSize());
		paginationUserInfo.setTotalCount(pagination.getTotalCount());
		
		List listData=pagination.getList();
		if(listData!=null)
		{
			ArrayList<UserInfo> userInfoList=new ArrayList<UserInfo>(listData.size());
			for(int i=0;i<listData.size();i++)
			{
				Object[] row=(Object[])listData.get(i);
				UserInfo userInfo=new UserInfo();
				
					if(null!=row[0])
					{
						userInfo.setUserid(Integer.parseInt(row[0].toString()));
					}
					if(null!=row[1])
					{
						userInfo.setUsername(String.valueOf(row[1]));
					}
					if(null!=row[2])
					{
						userInfo.setUserpwd(row[2].toString());
					}
					if(null!=row[3])
					{
						userInfo.setDatecreate(Timestamp.valueOf(row[3].toString()));
					}
					if(null!=row[4])
					{
						userInfo.setDateupdate(Timestamp.valueOf(row[4].toString()));
					}
					userInfoList.add(userInfo);
			}
			paginationUserInfo.setList(userInfoList);
		}
		return paginationUserInfo;
	}

	@Override
		protected Class<UserInfo> getEntityClass() {
			
			return UserInfo.class;
		}
}
