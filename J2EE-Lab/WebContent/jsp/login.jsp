<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>login</title>
</head>
<body>
	<h3>课程分数查询</h3>
	<form method='POST' action='/J2EE-Lab/CheckServlet'>
		id: <input type='text' name='id' placeholder='请输入学号'>
		password: <input type='password' name='password'  placeholder='请输入密码'>
		<input type='submit' name='Submit' value='Submit'>
	</form>
	
	<%
		ServletContext Context = getServletContext();
		String total = (String) Context.getAttribute("total");
		String loginTotal = (String) Context.getAttribute("loginTotal");
		String visitorTotal = (String) Context.getAttribute("visitorTotal");
	%>
	<br>
	<div>
		<div>
			访客总人数：<%=total%></div>
		<div>
			在线人数：<%=loginTotal%></div>
		<div>
			游客人数：<%=visitorTotal%></div>
	</div>

</body>
</html>