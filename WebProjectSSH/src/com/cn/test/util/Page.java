package com.cn.test.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * * 分页逻辑Bean *
 * 
 * @author tangs
 */
public class Page extends Component {
	private String currentPage;
	private String totalPage;
	private String totalCount;
	private String pageSize;
	private String url;
	private String cssClass;
	private String showPageNumber;

	// 定义分页标签的显示类型
	private String type;

	// 指定首页，末页，" + nextPage + "，" + endPage + "在页面上显示时的字符串
	private String firstPage;
	private String endPage;
	private String prePage;
	private String nextPage;

	// 标识记录在数据库中的位置
	private String index;
	// 总记录条数
	private String total;
	
	//分页标签的标识，当在同一个页面上有多个标签是用于区分不同的标签
	private String id;

	
	
	
	public void setId(String id)
	{
		this.id = id;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getTotalPage() {
		
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		try{
			Integer.parseInt(totalPage);
			}catch(Exception e){
			totalPage = "0";
		}
			
		this.totalPage = totalPage;
	}
	
	//totalCount
	public void setTotalCount(String totalCount) {
		try{
			Integer.parseInt(totalCount);
			}catch(Exception e){
				totalCount = "0";
		}
			
		this.totalCount = totalCount;
	}
	
	
	//pageSize
	public void setPageSize(String pageSize) {
		try{
			Integer.parseInt(pageSize);
			}catch(Exception e){
				pageSize = "0";
		}
			
		this.pageSize = pageSize;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getShowPageNumber() {
		return showPageNumber;
	}

	public void setShowPageNumber(String showPageNumber) {
		this.showPageNumber = showPageNumber;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public void setEndPage(String endPage) {
		this.endPage = endPage;
	}

	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public Page(ValueStack arg0) {
		super(arg0);
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		if (showPageNumber == null)
			showPageNumber = "5";
		if (cssClass == null)
			cssClass = "pagination";
		if (type == null)
			type = "multi";
		if (firstPage == null)
			firstPage = "第一页";
		if (endPage == null)
			endPage = "最后页";
		if (prePage == null)
			prePage = "上一页";
		if (nextPage == null)
			nextPage = "下一页";
		if (index == null)
			index = "1";
		if (currentPage == null)
			currentPage = "1";
		if(totalPage == null)
			totalPage = "1";
		
		if(totalCount == null){
			totalCount = "0";
		}
		//pageSize
		if(pageSize == null){
			pageSize = "15";
		}
		if(total == null) 
			total = "1"	;

		boolean isValid = true;

		totalPage = parse(totalPage);
		totalCount = parse(totalCount);
		pageSize = parse(pageSize);
		
		currentPage = parse(currentPage);
		index = parse(index);
		total = parse(total);
		cssClass = parse(cssClass);
		showPageNumber = parse(showPageNumber);
		endPage = parse(endPage);
		firstPage = parse(firstPage);
		type = parse(type);
		prePage = parse(prePage);
		nextPage = parse(nextPage);
		if(id == null) {
			id = "";
		}

		return result;
	}
	
	private String parse(String value) {

		String actualValue = null;

		if (value == null) {
			value = "top";
		}

		if (altSyntax()) {
			if (value.startsWith("%{") && value.endsWith("}")) {
				value = value.substring(2, value.length() - 1);
			}
		}

		actualValue = (String) getStack().findValue(value, String.class);
		if(actualValue == null) {
			actualValue = value;
		}
		return actualValue;
	}

	/*
	private String parse1(String param) {
		if (param.startsWith("%{") && param.endsWith("}")) {
			param = param.substring(2, param.length() - 1);
			param = (String) getStack().findValue(param, String.class);
			return param;
		} else if (param != null) {
			return param;
		} else {
			return param;
		}
	}
*/
	@Override
	public boolean end(Writer writer, String body) {
		try {
			StringBuilder outputStringBuilder = new StringBuilder();
			StringBuilder paramStringBuilder = new StringBuilder();
			boolean isValid = true;

			// 从ValueStack中取出数值

			if (isValid) {
				Set<String> keys = getParameters().keySet();

				for (String key : keys) {
					paramStringBuilder.append("&");
					paramStringBuilder.append(key);
					paramStringBuilder.append("=");
					paramStringBuilder.append(java.net.URLEncoder.encode(getParameters().get(key).toString(),"utf-8"));
				}
			}

			if (isValid) {
				Integer currentPageInt = Integer.valueOf(currentPage);
				Integer totalPageInt = Integer.valueOf(totalPage);
				Integer totalCountInt = Integer.valueOf(totalCount);
				
				Integer totalInt = Integer.valueOf(total);
				Integer showPageNumberInt = Integer.valueOf(showPageNumber);
				Integer indexInt = Integer.valueOf(index);
				String current = "";
				String param = paramStringBuilder.toString();

				if (type.equals("multi")) {

					if (totalPageInt != 0) {

						if (totalPageInt <= showPageNumberInt) { // 总的页数小于可以被显示的页码数
							if (currentPage.equals(totalPage)) { // 当前页等于总数页
								if ("1".equals(totalPage)) {
									// 如果totalPage = 1，则无需分页,不显示分页信息
								} else { // 总数页大于1
									outputStringBuilder.append("<ol class='" + cssClass + "'>");
									outputStringBuilder.append("<span>");
									outputStringBuilder.append("<a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=1" + param);
									outputStringBuilder.append("'>" + firstPage + "</a></span><span><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt - 1) + param);
									outputStringBuilder.append("'>" + prePage + "</a></span>");
									for (int i = 1; i <= totalPageInt; i++) {
										current = "";
										if (i == currentPageInt) {
											current = " class='current' ";
										}
										outputStringBuilder.append("<span" + current + "><a href='");
										outputStringBuilder.append(url);
										outputStringBuilder.append("?" + id + "currentPage=" + i + param);
										outputStringBuilder.append("'>" + i + "</a></span>");
									}
									outputStringBuilder.append("  <span> 共<font class='s_font'><span style='color:red'>"+totalCount+"</span>条 <span style='color:red' id='totalPage'>"+totalPage+"</span></font>页");
									outputStringBuilder.append("  到第<font><input name='' type='text' class='pag_input' id='jPage'/></font>页</span>");
									outputStringBuilder.append("<span><a href='#' url='"+url+"' param='"+param+"' id='jumpButton'>确定</a></span>");
									outputStringBuilder.append("</ol>");
								}
							} else { // 当前页不等于总数页
								if ("1".equals(currentPage)) { // 当前页为第一页
									outputStringBuilder.append("<ol class='" + cssClass + "'>");
									for (int i = 1; i <= totalPageInt; i++) {
										current = "";
										if (i == currentPageInt) {
											current = " class='current' ";
										}
										outputStringBuilder.append("<span" + current + "><a href='");
										outputStringBuilder.append(url);
										outputStringBuilder.append("?" + id + "currentPage=" + i + param);
										outputStringBuilder.append("&pageSize="+ pageSize +"'>" + i + "</a></span>");
									}
									outputStringBuilder.append("<span><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt + 1) + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + nextPage + "</a></span><span> <a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + totalPage + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + endPage + "</a></span>");
									outputStringBuilder.append("  <span> 共<font class='s_font'><span style='color:red'>"+totalCount+"</span>条 <span style='color:red' id='totalPage'>"+totalPage+"</span></font>页");
									
									outputStringBuilder.append("  到第<font><input name='' type='text' class='pag_input' id='jPage'/></font>页</span>");
									outputStringBuilder.append("<span><a href='#' url='"+url+"' param='"+param+"' id='jumpButton'>确定</a></span>");
									outputStringBuilder.append("</ol>");
								} else { // 当前页不为第一页
									outputStringBuilder.append("<ol class='" + cssClass + "'>");
									outputStringBuilder.append("<span>");
									outputStringBuilder.append("<a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=1" + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + firstPage + "</a></span><span><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt - 1) + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + prePage + "</a></span>");
									for (int i = 1; i <= totalPageInt; i++) {
										current = "";
										if (i == currentPageInt) {
											current = " class='current' ";
										}
										outputStringBuilder.append("<span" + current + "><a href='");
										outputStringBuilder.append(url);
										outputStringBuilder.append("?" + id + "currentPage=" + i + param);
										outputStringBuilder.append("&pageSize="+ pageSize +"'>" + i + "</a></span>");
									}
									outputStringBuilder.append("<span><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt + 1) + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + nextPage + "</a></span><span> <a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + totalPage + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + endPage + "</a></span>");
									outputStringBuilder.append("  <span> 共<font class='s_font'><span style='color:red'>"+totalCount+"</span>条 <span style='color:red' id='totalPage'>"+totalPage+"</span></font>页");
								
									outputStringBuilder.append("  到第<font><input name='' type='text' class='pag_input' id='jPage'/></font>页</span>");
									outputStringBuilder.append("<span><a href='#' url='"+url+"' param='"+param+"' id='jumpButton'>确定</a></span>");
									outputStringBuilder.append("</ol>");
								}
							}
						} else { // 总页数大于可以显示的页码数
							if (currentPage.equals(totalPage)) { // 当前页等于最后页
								outputStringBuilder.append("<ol class='" + cssClass + "'>");
								outputStringBuilder.append("<span>");
								outputStringBuilder.append("<a href='");
								outputStringBuilder.append(url);
								outputStringBuilder.append("?" + id + "currentPage=1" + param);
								outputStringBuilder.append("&pageSize="+ pageSize +"'>" + firstPage + "</a></span><span><a href='");
								outputStringBuilder.append(url);
								outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt - 1) + param);
								outputStringBuilder.append("&pageSize="+ pageSize +"'>" + prePage + "</a></span>");
								for (int i = totalPageInt - showPageNumberInt; i <= totalPageInt; i++) {
									current = "";
									if (i == currentPageInt) {
										current = " class='current' ";
									}
									outputStringBuilder.append("<span" + current + "><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + i + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + i + "</a></span>");
								}
								outputStringBuilder.append("  <span> 共<font class='s_font'><span style='color:red'>"+totalCount+"</span>条 <span style='color:red' id='totalPage'>"+totalPage+"</span></font>页");
							
								outputStringBuilder.append("  到第<font><input name='' type='text' class='pag_input' id='jPage'/></font>页</span>");
								outputStringBuilder.append("<span><a href='#' url='"+url+"' param='"+param+"' id='jumpButton'>确定</a></span>");
								outputStringBuilder.append("</ol>");
							} else { // 当前页不等于最后页
								if (currentPage.equals("1")) { // 当前页为第一页
									outputStringBuilder.append("<ol class='" + cssClass + "'>");
									for (int i = 1; i <= showPageNumberInt; i++) {
										current = "";
										if (i == currentPageInt) {
											current = " class='current' ";
										}
										outputStringBuilder.append("<span" + current + "><a href='");
										outputStringBuilder.append(url);
										outputStringBuilder.append("?" + id + "currentPage=" + i + param);
										outputStringBuilder.append("&pageSize="+ pageSize +"'>" + i + "</a></span>");
									}
									outputStringBuilder.append("<span><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt + 1) + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + nextPage + "</a></span><span> <a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + totalPage + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + endPage + "</a></span>");
									outputStringBuilder.append("  <span> 共<font class='s_font'><span style='color:red'>"+totalCount+"</span>条 <span style='color:red' id='totalPage'>"+totalPage+"</span></font>页");
								
									outputStringBuilder.append("  到第<font><input name='' type='text' class='pag_input' id='jPage'/></font>页</span>");
									outputStringBuilder.append("<span><a href='#' url='"+url+"' param='"+param+"' id='jumpButton'>确定</a></span>");
									outputStringBuilder.append("</ol>");
								} else if ((currentPageInt - showPageNumberInt / 2) >= 1 && (currentPageInt + showPageNumberInt / 2) <= totalPageInt) {
									outputStringBuilder.append("<ol class='" + cssClass + "'>");
									outputStringBuilder.append("<span>");
									outputStringBuilder.append("<a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=1" + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + firstPage + "</a></span><span><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt - 1) + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + prePage + "</a></span>");
									for (int i = currentPageInt - showPageNumberInt / 2; i <= (currentPageInt + showPageNumberInt / 2); i++) {
										current = "";
										if (i == currentPageInt) {
											current = " class='current' ";
										}
										outputStringBuilder.append("<span" + current + "><a href='");
										outputStringBuilder.append(url);
										outputStringBuilder.append("?" + id + "currentPage=" + i + param);
										outputStringBuilder.append("&pageSize="+ pageSize +"'>" + i + "</a></span>");
									}
									outputStringBuilder.append("<span><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt + 1) + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + nextPage + "</a></span><span> <a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + totalPage + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + endPage + "</a></span>");
									outputStringBuilder.append("  <span> 共<font class='s_font'><span style='color:red'>"+totalCount+"</span>条 <span style='color:red' id='totalPage'>"+totalPage+"</span></font>页");
								
									outputStringBuilder.append("  到第<font><input name='' type='text' class='pag_input' id='jPage'/></font>页</span>");
									outputStringBuilder.append("<span><a href='#' url='"+url+"' param='"+param+"' id='jumpButton'>确定</a></span>");
									outputStringBuilder.append("</ol>");
								} else if ((currentPageInt - showPageNumberInt / 2) < 1) {
									outputStringBuilder.append("<ol class='" + cssClass + "'>");
									outputStringBuilder.append("<span>");
									outputStringBuilder.append("<a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=1" + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + firstPage + "</a></span><span><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt - 1) + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + prePage + "</a></span>");
									for (int i = 1; i <= showPageNumberInt; i++) {
										current = "";
										if (i == currentPageInt) {
											current = " class='current' ";
										}
										outputStringBuilder.append("<span" + current + "><a href='");
										outputStringBuilder.append(url);
										outputStringBuilder.append("?" + id + "currentPage=" + i + param);
										outputStringBuilder.append("&pageSize="+ pageSize +"'>" + i + "</a></span>");
									}
									outputStringBuilder.append("<span><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt + 1) + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + nextPage + "</a></span><span> <a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + totalPage + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + endPage + "</a></span>");
									outputStringBuilder.append("  <span> 共<font class='s_font'><span style='color:red'>"+totalCount+"</span>条 <span style='color:red' id='totalPage'>"+totalPage+"</span></font>页");
								
									outputStringBuilder.append("  到第<font><input name='' type='text' class='pag_input' id='jPage'/></font>页</span>");
									outputStringBuilder.append("<span><a href='#' url='"+url+"' param='"+param+"' id='jumpButton'>确定</a></span>");
									outputStringBuilder.append("</ol>");
								} else {
									outputStringBuilder.append("<ol class='" + cssClass + "'>");
									outputStringBuilder.append("<span>");
									outputStringBuilder.append("<a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=1" + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + firstPage + "</a></span><span><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt - 1) + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + prePage + "</a></span>");
									for (int i = totalPageInt - showPageNumberInt; i <= totalPageInt; i++) {
										current = "";
										if (i == currentPageInt) {
											current = " class='current' ";
										}
										outputStringBuilder.append("<span" + current + "><a href='");
										outputStringBuilder.append(url);
										outputStringBuilder.append("?" + id + "currentPage=" + i + param);
										outputStringBuilder.append("&pageSize="+ pageSize +"'>" + i + "</a></span>");
									}
									outputStringBuilder.append("<span><a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + (currentPageInt + 1) + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + nextPage + "</a></span><span> <a href='");
									outputStringBuilder.append(url);
									outputStringBuilder.append("?" + id + "currentPage=" + totalPage + param);
									outputStringBuilder.append("&pageSize="+ pageSize +"'>" + endPage + "</a></span>");
									
									outputStringBuilder.append("  <span> 共<font class='s_font'><span style='color:red'>"+totalCount+"</span>条 <span style='color:red' id='totalPage'>"+totalPage+"</span></font>页");
									
									outputStringBuilder.append("  到第<font><input name='' type='text' class='pag_input' id='jPage'/></font>页</span>");
									outputStringBuilder.append("<span><a href='#' url='"+url+"' param='"+param+"' id='jumpButton'>确定</a></span>");		
									outputStringBuilder.append("</ol>");
									
								}
							}
						}
					}
				} else if (type.equals("single")) {
					
					int preIndex = 1;
					int nextIndex = 1;
					
					if(indexInt == 1 && indexInt == totalInt) {
						preIndex = 1;
						nextIndex = 1;
					}
					if(indexInt == 1) {
						if(totalInt != 1) {
							preIndex = totalInt;
							nextIndex = indexInt + 1;
						} else {
							preIndex = 1;
							nextIndex = 1;
						}
					} else if((indexInt-totalInt) == 0) {
						preIndex = indexInt - 1;
						nextIndex = 1;
					} else {
						preIndex = indexInt - 1;
						nextIndex = indexInt + 1;
					}
					
					outputStringBuilder.append("<ol class='" + cssClass + "'>");
					outputStringBuilder.append("<span>");
					outputStringBuilder.append("<a href='");
					outputStringBuilder.append(url);
					outputStringBuilder.append("?index=" + preIndex + param);
					outputStringBuilder.append("&pageSize="+ pageSize +"'>" + prePage + "</a></span>");
					outputStringBuilder.append("<span><a href='");
					outputStringBuilder.append(url);
					outputStringBuilder.append("?index=" + nextIndex + param);
					outputStringBuilder.append("&pageSize="+ pageSize +"'>" + nextPage + "</a></span>");
					
					outputStringBuilder.append("  <span> 共<font class='s_font'><span style='color:red'>"+totalCount+"</span>条 <span style='color:red' id='totalPage'>"+totalPage+"</span></font>页");
				
					outputStringBuilder.append("  到第<font><input name='' type='text' class='pag_input' id='jPage'/></font>页</span>");
					outputStringBuilder.append("<span><a href='#' url='"+url+"' param='"+param+"' id='jumpButton'>确定</a></span>");
					outputStringBuilder.append("</ol>");
				}
			}

			writer.write(outputStringBuilder.toString());

		} catch (IOException ex) {
			Logger.getLogger(Page.class.getName()).log(Level.SEVERE, null, ex);
		}
		return super.end(writer, body);
	}

}
