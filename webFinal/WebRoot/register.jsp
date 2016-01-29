<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>login</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/register.css" rel="stylesheet">
	
  </head>
  
  <body>
    <div class="signin">
		<div class="signin-head"><img src="images/test/head_120.png" alt="" class="img-circle"></div>
		<form class="form-signin" role="form" action="Register" method="post">
			用户名：<span>&nbsp;</span><input class="form-control" type="text" name="username" autofocus>
   			密码:<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><input class="form-control" type="password" name="password">
   			确认密码:<input class="form-control" type="password" name="confirmPassword">
   			姓名:<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><input class="form-control" type="text" name="realName">
			<input type="submit" name="Submit" class="btn btn-lg btn-warning btn-block" value="提交">
			<input type="reset" name="Reset" class="btn btn-lg btn-warning btn-block" value="重置">
			
		</form>
	</div>
  </body>
</html>
