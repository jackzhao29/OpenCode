<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'Login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <s:form action="addUserInfo.action" method="post">
     <input type="text" name="userinfo.username" value='<s:property value="userinfo.username"/>'/>
     <input type="text" name="userinfo.userpwd" value='<s:property value="userinfo.userpwd"/>'/>
      <s:submit value="确定"></s:submit>
    </s:form>
      <s:a href="addUserInfo.action?userinfo.username=sasa&userinfo.userpwd=123456">添加</s:a>
     <s:a href="vShowUserInfo.action">查询数据</s:a>
     <s:a href="vShow.action">查询数据</s:a>
     <script type="text/javascript">
     </script>
  </body>
</html>
