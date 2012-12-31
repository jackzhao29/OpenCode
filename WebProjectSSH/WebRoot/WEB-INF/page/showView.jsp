<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="p" uri="/signsv-tags" %>
<%@ include file="/WEB-INF/page/util/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showView.jsp' starting page</title>
    
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
     数据显示
        <form id="form1" action="selectUserInfo.action" method="post">
         输入用户名：<input type="text" name="loginName" value='<s:property value="loginName"/>'>
         <input type="button" value="查询" onclick="javascript:document.getElementById('form1').submit();">
        </form>
     <table width="100%">
     <tr>
       <td width="4%">UserID</td>
       <td width="10%">用户名</td>
       <td width="10%">密码</td>
       <td width="30%">创建时间</td>
       <td width="30%">修改时间</td>
       <td width="8%">修改</td>
       <td width="8%">删除</td>
     </tr>
    
      <s:if test="#request.userInfoList!=null">
      <s:iterator value="userInfoList" id="bw1">
      <tr>
       <td width="4%" title="${userid}">
        <p:textCut value="${userid}"></p:textCut>
       </td>
       <td width="10%" title="${username}">
        <p:textCut value="${username}"></p:textCut>
       </td>
       <td width="10%" title="${userpwd}">
        <p:textCut value="${userpwd}"></p:textCut>
       </td>
       <td width="30%" title="${datecreate}">
        <s:date name="datecreate" format="yyyy-MM-dd HH:mm:ss"/>
       </td>
       <td width="30%" title="${dateupdate}">
        <s:date name="dateupdate" format="yyyy-MM-dd HH:mm:ss"/>
       </td>
       <td width="8%">
       <a href="to_ModifyUserInfo.action?userId=${userid}" class="color_aa" ><img src="images/bianji.gif" border="0" alt="编辑" title="编辑"/></a>
       </td>
       <td width="8%">
         <a href="javascript:void(0);" onclick="deleteCofirm(${userid})" id="deleteCofirm" ><img src="images/dele.gif" border="0" alt="删除" title="删除"/></a>
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
			<s:param name="loginName" value="loginName"></s:param>
           </p:pages>
         </td>
       </tr>
      </table>
      <script type="text/javascript">
      jumpPage();
        function deleteCofirm(userId)
	    {
	    alert(userId);
			nfschinaDialogConfirm("确认删除吗?",function(){
				window.location = 'delUserInfo.action?userId=' + userId;
			})
		
	    }
      </script>
  </body>
</html>
