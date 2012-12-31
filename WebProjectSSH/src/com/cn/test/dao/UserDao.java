package com.cn.test.dao;

import java.util.List;

import com.cn.test.base.BasicException;
import com.cn.test.model.UserInfo;
import com.hichina.caba.common.util.pagination.PageQueryParam;
import com.hichina.caba.common.util.pagination.Pagination;

public interface UserDao {
	
	public UserInfo saveUser(UserInfo userinfo)throws BasicException;
	
	public List<UserInfo> findUserInfoList() throws BasicException;
	
	public Pagination findUserInfoAll(PageQueryParam pageQueryParam,UserInfo userinfo) throws BasicException;
	
	public Pagination findUserInfoList(Integer pageNo,Integer pageSize,String userName,String startDate,String endDate) throws BasicException;
	
	public UserInfo findUserInfoByLoginName(String loginName) throws BasicException;
	
	public List<UserInfo> findUserInfoListByLoginName(String loginName) throws BasicException;
	
	public void deleteUserInfoByUserId(Integer userId) throws BasicException;
	
	public UserInfo modifyUserInfoByUserId(UserInfo userinfo) throws BasicException;
	
	public UserInfo findUserInfoByUserId(Integer userId) throws BasicException;


}
