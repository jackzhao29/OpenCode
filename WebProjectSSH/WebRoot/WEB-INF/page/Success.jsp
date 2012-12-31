<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    
    <title>My JSP 'Success.jsp' starting page</title>
    
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
    保存成功 <br>
    用户名：${userinfo.username}<br>
    密码：${userinfo.userpwd }<br>
    时间：${userinfo.datecreate }<br/>
    <s:if test="#request.userInfoList!=null">
      <table>
       <tr>
        <td>用户名</td>
        <td>密码</td>
        <td>创建时间</td>
        <td>修改时间</td>
       </tr>
       <s:iterator value="userInfoList">
         <tr>
            <td width="20%" title="${username}">
            <p:textCut value="${username}" remainNum="3"></p:textCut>
            </td>
            <td width="20%" title="${userpwd}">
             <p:textCut value="${userpwd}" ></p:textCut>
            </td>
            <td width="30%" title="${datecreate}">
            <s:date name="datecreate" format="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td width="30%" title="${dateupdate}">
               <s:date name="dateupdate" format="yyyy-MM-dd HH:mm:ss"/> 
            </td>
         </tr>
       </s:iterator>
      </table>
    </s:if>
    <s:else>
      无数据
    </s:else>
  </body>
</html>
