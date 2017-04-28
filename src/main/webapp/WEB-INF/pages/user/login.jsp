<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
	${msg }
	<form action="" method="post">
		请输入您的邮箱或手机号码：<br>
		<input type="text" name="account"><br>
		密码：<br>
		<input type="password" name="password"><br>
		<input type="submit" value="登录">
	</form>
</body>
</html>