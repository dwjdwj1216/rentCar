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
	<link href="css/signin.css" rel="stylesheet">
	<script type="text/javascript">
		function check(){
			var username = document.getElementsByName("username");
			var password = document.getElementsByName("password");
			if(username[0].value ==""){
				alert("用户名为空");
			}else if(password[0].value ==""){
				alert("密码为空");
			}else {
				document.forms[0].submit();
			}
		}
		function register(){
			window.location.href='register.jsp';
		}
	</script>
	
  </head>
  
  <body>
    <div class="signin">
		<div class="signin-head"><img src="images/test/head_120.png" alt="" class="img-circle"></div>
		<form class="form-signin" role="form" action="Login" method="post">
			<input type="text" class="form-control" placeholder="用户名(游客请用123)" required autofocus name="username"/>
			<input type="password" class="form-control" placeholder="密码（游客请用123）" required name="password"/>
			<button class="btn btn-lg btn-warning btn-block" type="button" name="login" onclick="check()">登录</button>
			<button class="btn btn-lg btn-warning btn-block" type="button" name="register" onclick="register()">注册</button>
		</form>
	</div>
  </body>
</html>
