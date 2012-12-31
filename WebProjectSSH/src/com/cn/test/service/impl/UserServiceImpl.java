package com.cn.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.cn.test.base.BasicException;
import com.cn.test.dao.UserDao;
import com.cn.test.model.UserInfo;
import com.cn.test.service.UserService;
import com.hichina.caba.common.util.pagination.PageQueryParam;
import com.hichina.caba.common.util.pagination.Pagination;

public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	//public void setUserDao(UserDao userDao) {
		//this.userDao = userDao;
	//}



    
	public UserInfo saveUser(UserInfo userinfo) throws BasicException
	{
		return userDao.saveUser(userinfo);
	}

	public Pagination findUserInfoAll(PageQueryParam pageQueryParam,UserInfo userinfo) throws BasicException
	{
		return userDao.findUserInfoAll(pageQueryParam, userinfo);
	}
	public List<UserInfo> findUserInfoList() throws BasicException
	{
		return userDao.findUserInfoList();
	}
	public Pagination findUserInfoList(Integer pageNo,Integer pageSize,String userName,String startDate,String endDate)
	{
		return userDao.findUserInfoList(pageNo, pageSize, userName, startDate, endDate);
	}
    
	public UserInfo findUserInfoByLoginName(String loginName) throws BasicException
	{
		return userDao.findUserInfoByLoginName(loginName);
	}
	
	public List<UserInfo> findUserInfoListByLoginName(String loginName) throws BasicException
	{
		return userDao.findUserInfoListByLoginName(loginName);
	}
	
	public void deleteUserInfoByUserId(Integer userId) throws BasicException
	{
		userDao.deleteUserInfoByUserId(userId);
	}
	public UserInfo modifyUserInfoByUserId(UserInfo userinfo) throws BasicException
	{
		return userDao.modifyUserInfoByUserId(userinfo);	
	}

	public UserInfo findUserInfoByUserId(Integer userId) throws BasicException
	{
		return userDao.findUserInfoByUserId(userId);
	}
	
}
