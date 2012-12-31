package com.cn.test.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hichina.caba.common.util.pagination.PageQueryParam;
import com.hichina.caba.common.web.RequestUtils;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	protected transient final Log	log			= LogFactory.getLog(getClass());
	
	/**
	 * 分页使用的三个参数： currentPage为当前要显示的页，默认为第一页
	 * totalPage需要设置为DataPage.getTotalPageCount()方法返回的值
	 * pageSize为每页需要显示的记录数，默认为10
	 */
	protected String				currentPage	= "1";
	protected String				totalPage;
	protected int					pageSize	= 10;
	protected int					totalCount;
	protected PageQueryParam 		pageQueryParam ;
	
	
	

	protected String				mid;
	
	/**页面变量*/
	public String				messageOfPage;	
	public String 				jsFunctionName;//zyl:2012-01-11
	

	/**
	 * 程序用到的Service
	 */
	/**
	protected UserService userService;
	
	protected AuthorityBackendService authorityBackendService;
	protected AuthorityService authorityService;
	protected MenuService menuService;
	

	@Autowired
	protected UserOrganizationDao userOrganizationDao;
	
	public MenuService getMenuService() {
		return menuService;
	}
	
	@Autowired
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	protected OrganizationService organizationService;	
	protected SystemLogService systemLogService;
	protected SystemParameterService systemParameterService;

	

	
	public AuthorityService getAuthorityService() {
		return authorityService;
	}
	@Autowired
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}
	**/
	public PageQueryParam getPageQueryParam() {
		return pageQueryParam;
	}

	public void setPageQueryParam(PageQueryParam pageQueryParam) {
		this.pageQueryParam = pageQueryParam;
	}
	 
	public Log getLog()
	{
		return log;
	}
	public String getJsFunctionName() {
		return jsFunctionName;
	}

	public void setJsFunctionName(String jsFunctionName) {
		this.jsFunctionName = jsFunctionName;
	}
	public int getCurrentPage()
	{
		return Integer.parseInt(currentPage);
	}
	
	public void setCurrentPage(String currentPage)
	{
		this.currentPage = currentPage;
	}
	
	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage + "";
	}
	
	public int getTotalPage()
	{
		return Integer.parseInt(totalPage);
	}
	
	public void setTotalPage(String totalPage)
	{
		this.totalPage = totalPage;
	}
	
	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage + "";
	}
	
	public int getPageSize()
	{
		return pageSize;
	}
	
	public void setPageSize(int pageSize)
	{
		if(pageSize>0){
			this.pageSize = pageSize;
		}
		//this.pageSize = pageSize;
	}
	
	public int getTotalCount()
	{
		return totalCount;
	}
	
	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}
	
	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

    /**
	public UserService getUserService()
	{
		return userService;
	}
	
	@Autowired
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	
	
	public AuthorityBackendService getAuthorityBackendService() {
		return authorityBackendService;
	}
	@Autowired
	public void setAuthorityBackendService(
			AuthorityBackendService authorityBackendService) {
		this.authorityBackendService = authorityBackendService;
	}
	public OrganizationService getOrganizationService() {
		return organizationService;
	}
	@Autowired
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	public SystemLogService getSystemLogService() {
		return systemLogService;
	}
	
	@Autowired
	public void setSystemLogService(SystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}
	public SystemParameterService getSystemParameterService() {
		return systemParameterService;
	}
	@Autowired
	public void setSystemParameterService(
			SystemParameterService systemParameterService) {
		this.systemParameterService = systemParameterService;
	}
	**/
	/**
	 * 获取Request对象
	 * 
	 * @return current request
	 */
	protected HttpServletRequest getRequest()
	{
		return ServletActionContext.getRequest();
	}
	
	
	protected Cookie getCookie(String cookieName)
	{
		return RequestUtils.getCookie(getRequest(), cookieName);
	}
	
	/**
	 * 获得一个Session对象，如果没有则创建一个新的Session
	 * 
	 * @return the session from the request (request.getSession()).
	 */
	protected HttpSession getSession()
	{
		return getRequest().getSession();
	}
	
	/**
	 * 获取Response对象
	 * 
	 * @return current response
	 */
	protected HttpServletResponse getResponse()
	{
		return ServletActionContext.getResponse();
	}
	
	
	protected void setTipCookie(String tip) throws UnsupportedEncodingException
	{
		if(tip != null){
			tip = URLEncoder.encode(tip, "UTF-8");
		}
		
		
		//Cookie tipCookie = getCookie("tipCookie");
		
		/* 增加用户提示的Cookies */
		/**
		HttpServletRequest request = (HttpServletRequest) getRequest();
		tipCookie = new Cookie("tipCookie", tip);
		
		String servletPath = request.getServletPath();
		String requestURI = request.getRequestURI();
		String path = requestURI.substring(0, requestURI.indexOf(servletPath));
		tipCookie.setPath("/");
		
		getResponse().addCookie(tipCookie);
		**/
	}

	

	/**
	 * 方法描述：获得登录用户
	 * 备          注:
	 * 创  建   人: ZhaoYanLei
	 * 创建日期: 2011-10-20  下午12:15:16	 
	 */
	/**
	public User findLoginUser(){		
		
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		
		User loginUser = null;
		
		if(session != null ){			
			loginUser = (User) session.getAttribute(SystemConstantUtil.SESSION_LOGIN_USER);		
			
		}		
		return loginUser;
	}	
     **/
	/**
	 * 方法描述：获得登录用户ID
	 * 备          注:
	 * 创  建   人: ZhaoYanLei
	 * 创建日期: 2011-11-20  下午12:15:16	 
	 */
	/**
	public Integer findLoginUserId(){		
		User user = findLoginUser();
		if(user!=null){
			return user.getUserId();
		}else{
			return null;
		}
		
	}
	**/
	/**
	 * 方法描述：保存登录用户操作日志
	 * 备          注:
	 * 创  建   人: ZhaoYanLei
	 * 创建日期: 2011-10-29  上午09:42:58
	 * @param operationModule 
	 * 				操作模块
	 * @param operationContent
	 * 				操作内容
	 * @param isOperationSuccess
	 * 				操作结果：true=成功；fasle=失败
	 */
	/**
	public void saveSyetemLogForLoginUser(String  operationModule, String  operationContent
			,boolean isOperationSuccess){		
		
		System.out.println("BaseAction.saveSyetemLogForLoginUser()---------->>  "
				+ "    operationModule == " + operationModule		
				+ "    operationContent == " + operationContent	
				+ "    isOperationSuccess == " + isOperationSuccess		
		);
		
		User loginUser =this.findLoginUser();
		SystemLog systemLog = new SystemLog();
		systemLog.setOperationContent(operationContent);
		systemLog.setPermissionName(operationModule);
		
		if(isOperationSuccess==true){
			systemLog.setResult(systemLog.SYSTEMLOG_RESULT_SUCCESS);
		}else{
			systemLog.setResult(systemLog.SYSTEMLOG_RESULT_FAIL);
		}
		
		//systemLog.setIp(this.getRequest().getRemoteAddr());
		systemLog.setIp(""+IPUtils.ipToLong(RequestUtils.getRemoteAddr(this.getRequest())));
		
		systemLog.setUserId(loginUser.getUserId());
		systemLog.setUserName(loginUser.getUserName()+"["+ loginUser.getLoginName() +"]");
		
	    systemLogService.addSystemLog(systemLog);
	}
    **/
	
	public String getMessageOfPage() {
		return messageOfPage;
	}

	public void setMessageOfPage(String messageOfPage) {
		this.messageOfPage = messageOfPage;
	}

}
