package com.cn.test.util;

import com.opensymphony.xwork2.util.ValueStack;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

/**
 * 分页标签
 * 
 * @author tangs
 */
public class PageTag extends ComponentTagSupport
{
	private String	currentPage;	// 当前页
	private String	totalPage;		// 总页数
	private String 	totalCount;		// 总记录数  zyl: 2011-12-29
	private String  pageSize;
	
	private String	url;			// 请求地址
	private String	cssClass;		// CSS样式类
	private String	showPageNumber; // 需要显示的页码数
									
	// 定义分页标签的显示类型
	private String	type;
	
	

	// 指定首页，末页，上一页，下一页在页面上显示时的字符串
	private String	firstPage;
	private String	endPage;
	private String	prePage;
	private String	nextPage;
	
	// 标识记录在数据库中的位置
	private String	index;
	// 总记录条数
	private String	total;
	
	// 分页标签的标识，当在同一个页面上有多个标签是用于区分不同的标签
	private String	id;
	
	
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public void setCurrentPage(String currentPage)
	{
		this.currentPage = currentPage;
	}
	
	public void setTotalPage(String totalPage)
	{
		this.totalPage = totalPage;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public void setCssClass(String cssClass)
	{
		this.cssClass = cssClass;
	}
	
	public void setShowPageNumber(String showPageNumber)
	{
		this.showPageNumber = showPageNumber;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setFirstPage(String firstPage)
	{
		this.firstPage = firstPage;
	}
	
	public void setEndPage(String endPage)
	{
		this.endPage = endPage;
	}
	
	public void setPrePage(String prePage)
	{
		this.prePage = prePage;
	}
	
	public void setNextPage(String nextPage)
	{
		this.nextPage = nextPage;
	}
	
	public void setIndex(String index)
	{
		this.index = index;
	}
	
	public void setTotal(String total)
	{
		this.total = total;
	}
	
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		if(pageSize!=null && Integer.parseInt(pageSize)>0){
			this.pageSize = pageSize;
		}
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2)
	{
		return new Page(arg0); // 返回Pages Component，分页的逻辑处理都在这个Component中
	}
	
	// 获得参数
	protected void populateParams()
	{
		super.populateParams();
		
		Page pages = (Page) component;
		pages.setCssClass(cssClass);
		pages.setCurrentPage(currentPage);
		pages.setShowPageNumber(showPageNumber);
		pages.setTotalPage(totalPage);
		pages.setTotalCount(totalCount);//totalCount
		pages.setPageSize(pageSize);//pageSize
		
		pages.setUrl(url);
		pages.setFirstPage(firstPage);
		pages.setEndPage(endPage);
		pages.setNextPage(nextPage);
		pages.setType(type);
		pages.setPrePage(prePage);
		pages.setIndex(index);
		pages.setTotal(total);
		pages.setId(id);
	}
}
