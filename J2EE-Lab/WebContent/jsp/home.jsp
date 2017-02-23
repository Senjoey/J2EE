<%@page import="edu.nju.factory.ServiceFactory"%>
<%@page import="edu.nju.models.Course"%>
<%@page import="edu.nju.models.Selection"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="MyTag" uri="/WEB-INF/tlds/CheckLogin.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>home</title>
</head>

<MyTag:checkLogin/>

<body>
	<h3>
		你好，<%
		String studentName = (String) session.getAttribute("studentName");
		out.print(studentName);
	%>
		同学！你的测验成绩如下：
	</h3>

	<table>
		<tr>
			<th>课程号</th>
			<th>课程名</th>
			<th>成绩</th>
			<th>&nbsp;</th>
		</tr>
		<%
			ArrayList<Selection> selections =  (ArrayList<Selection>)session.getAttribute("selections");
		%>
		<%
			for (Selection select : selections) {
		%>
		<tr>
			<% 
			Integer examTaken = select.getExamTaken(); 
			Course course = ServiceFactory.getCourseService().getCourse(select.getCourseId());
			%>
			<td><%=select.getCourseId()%></td>
			<td><%=course.getName()%></td>
			<td><%=select.getScore()%></td>
			<td style ="color:#FF0000">
			<% if(0==examTaken) 
					out.print("你的这门课程尚未参加考试！");
			%>
			</td>
		</tr>
		
		<% } %>

	</table>
	
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
	<br>
	<form method = 'GET' action='/J2EE-Lab/CheckServlet'>
		<input type='submit' name='logout' value='登出'>
	</form>
</body>
</html>