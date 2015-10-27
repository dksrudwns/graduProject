<%@page import="com.mysql.fabric.xmlrpc.base.Member"%>
<%@page import="daoto.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");//get id value

	MemberDAO mDAO = new MemberDAO();
	int check = mDAO.confirmId(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복 체크</title>
</head>
<body bgcolor="#ffffcc">
	<br>
	<center>
		<b><%=id%></b>
		<%
			if (check == 0) {
				out.println("는 이미 존재하는 ID입니다.<br><br>사용할 수 없습니다.<p>");
			} else {
				out.println("는 사용가능 합니다.");
			}
		%>
		<a id="click" href="javascript:window.close()">닫기</a>
	</center>
</body>
</html>