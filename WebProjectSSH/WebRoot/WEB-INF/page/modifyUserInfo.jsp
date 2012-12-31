<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'modifyUserInfo.jsp' starting page</title>
    
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
    <form id="submintForm" action="modifyUserInfo.action" method="post" >
    <input type="hidden" id="userId" name="userId" value='<s:property value="userId"/>'/>
     <table>
       <tr><td colspan="2">更新信息</td></tr>
       <tr><td>用户名：</td>
        <td>
          <input type="text" name="userinfo.username" value='<s:property value="userinfo.username"/>'>
        </td>
        </tr>
        <tr><td>密码：</td>
          <td>
           <input type="text" name="userinfo.userpwd" value='<s:property value="userinfo.userpwd"/>'/>
          </td>
        </tr>
        <tr>
         <td colspan="2">
           <input type="submit" value="保存">
         </td>
        </tr>
     </table>
     </form>
  </body>
</html>
