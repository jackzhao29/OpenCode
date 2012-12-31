<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="p" uri="/signsv-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'show.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    使用pagedSqlQuery返回Pagination数据
     <table width="100%">
     <tr>
       <td width="5%">UserID</td>
       <td width="20%">用户名</td>
       <td width="15%">密码</td>
       <td width="30%">创建时间</td>
       <td width="30%">修改时间</td>
     </tr>
    
      <s:if test="#request.userInfoList!=null">
      <s:iterator value="userInfoList" id="bw1">
      <tr>
       <td width="5%" title="${userid}">
        <p:textCut value="${userid}"></p:textCut>
       </td>
       <td width="20%" title="${username}">
        <p:textCut value="${username}"></p:textCut>
       </td>
       <td width="15%" title="${userpwd}">
        <p:textCut value="${userpwd}"></p:textCut>
       </td>
       <td width="30%" title="${datecreate}">
        <s:date name="datecreate" format="yyyy-MM-dd HH:mm:ss"/>
       </td>
       <td width="30%" title="${dateupdate}">
        <s:date name="dateupdate" format="yyyy-MM-dd HH:mm:ss"/>
       </td>
      </tr>
      </s:iterator>
      </s:if>
       <s:else>
       <tr><td colspan="4">无数据</td></tr>
      </s:else>
     </table>

      <table>
       <tr>
         <td align="center">
           <s:if test="totalPage==1">
                            共${totalCount}条${totalPage}页               
           </s:if>
           <p:pages url="vShowUserInfo.action"
            currentPage="${currentPage}" totalPage="${totalPage}"
			totalCount="${totalCount}" showPageNumber="10" > 
           </p:pages>
         </td>
       </tr>
      </table>
    
  </body>
</html>
