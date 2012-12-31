package com.cn.test.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.cn.test.base.BaseAction;
import com.cn.test.model.UserInfo;
import com.cn.test.service.UserService;
import com.hichina.caba.common.util.pagination.PageQueryParam;
import com.hichina.caba.common.util.pagination.Pagination;

public class UserAction extends BaseAction {
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Resource //按名称注入
	 //@Autowired//按类型注入
	private UserService userService;
	
	
	
	//public void setUserService(UserService userService) {
		//this.userService = userService;
	//}

	private UserInfo userinfo;
	private List<UserInfo> userInfoList;
	private Pagination pagination;
	
	
	private Integer userId;
	private String loginName;
	private String password;
	private Integer pageNum;
	private Integer pageSum=10;
	private String startDate;
	private String endDate;
	
	public String addUser()
	{
		System.out.println("用户名："+userinfo.getUsername());
		System.out.println("密码："+userinfo.getUserpwd());
		//UserInfo user=new UserInfo();
		//user.setUsername(getLoginName());
		//user.setUserpwd(getPassword());
		userInfoList=userService.findUserInfoListByLoginName(userinfo.getUsername());
		System.out.println(userInfoList.size());
		if(null==userService.findUserInfoByLoginName(userinfo.getUsername()))
		{
			if(null==userService.saveUser(userinfo))
			{
				return ERROR;
			}
			else
			{
				userInfoList=userService.findUserInfoList();
				return SUCCESS;
			}
		}
		else
		{
			try {
				setTipCookie("此登录名已存在");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return SUCCESS;
		}
		
	}
	
	/**
	 * 数据分页显示(数据源是Pagination)
	 * @return
	 */
	public String showUserInfo()
	{
		UserInfo u=new UserInfo();
		try
		{
			if(pageQueryParam==null)
			{
				pageQueryParam = new PageQueryParam();
			}
			if(getLoginName()!=null)
			{
				u.setUsername(getLoginName());
			}
			pageQueryParam.setPageNo(getCurrentPage());
			pageQueryParam.setPageSize(pageSize);
			Pagination data=userService.findUserInfoAll(pageQueryParam, u);
			userInfoList=(List<UserInfo>)data.getList();
			setTotalPage(data.getTotalPage());
			totalCount=data.getTotalCount();
			pagination=data;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return SUCCESS;
		
	}
	
	/**
	 * 数据分页显示(数据源是List<UserInfo>)
	 * @return
	 */
	 public String showUserInfoList()
	 {
		 try
		 {
			 Pagination data=userService.findUserInfoList(getCurrentPage(), pageSum, getLoginName(), getStartDate(), getEndDate());
			 userInfoList=(List<UserInfo>)data.getList();
			 setTotalPage(data.getTotalPage());
			 totalCount=data.getTotalCount();
			 pagination=data;
			 
		 }
		 catch(Exception ex)
		 {
			 System.out.println("根据对象获取集合异常:"+ex);
			 ex.printStackTrace();
		 }
		 return SUCCESS;
	 }
	 
	 /**
	  * 根据userId删除数据
	  * @return
	  */
	 public String deleteUserInfo()
	 {
		 userService.deleteUserInfoByUserId(getUserId());
		 return SUCCESS;
	 }
	 
	 /**
	  * 更新数据
	  * @return
	  */
	 public String modifyUserInfoByUserId()
	 {
		 UserInfo u=new UserInfo();
		 userinfo.setUserid(userId);
		 u=userService.modifyUserInfoByUserId(userinfo);
		 if(null==u)
			 return ERROR;
		 else
		     return SUCCESS;
	 }
	 
	 /**
	  * 转发到更新页面
	  * @return
	  */
	 public String tooModifyUserInfo()
	 {
		 userinfo=userService.findUserInfoByUserId(userId);
		   return SUCCESS;
	 }
	 
	 /**
		 *提示信息
		 */
		protected void setTipCookie(String tip) throws UnsupportedEncodingException
		{
			if(tip != null){
				tip = URLEncoder.encode(tip, "UTF-8");
			}
			
			Cookie tipCookie = getCookie("tipCookie");
			
			/* 增加用户提示的Cookies */

			HttpServletRequest request = (HttpServletRequest) getRequest();
			tipCookie = new Cookie("tipCookie", tip);
			
			String servletPath = request.getServletPath();
			String requestURI = request.getRequestURI();
			String path = requestURI.substring(0, requestURI.indexOf(servletPath));
			tipCookie.setPath("/");
			
			getResponse().addCookie(tipCookie);
		}
	
	public UserInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
	
	
}
