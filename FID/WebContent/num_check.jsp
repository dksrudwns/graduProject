<%@page import="daoto.peopleInfoDAO"%>
<%@page import="com.mysql.fabric.xmlrpc.base.Member"%>
<%@page import="daoto.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String num = request.getParameter("num");//get id value
	String tm = request.getParameter("tm");//get id value
	MemberDAO mDAO = new MemberDAO();
	int checkm = mDAO.confirmPN(num);

	peopleInfoDAO pDAO = new peopleInfoDAO();
	int checkp = pDAO.confirmPN(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주민번호 중복 체크</title>
</head>
<body bgcolor="#ffffcc">
		<b><%=num%></b>
		<%
			if (tm.equals("1")) {//단순 주민번호 유효 확인
				if (checkp == 0) {//존재하는 주민번호 확인
					out.println("는 등록가능 합니다.");
				} else {
					out.println("존재하지 않는 주민번호 입니다.");
				}
			} else {//멤버 등록때 사용
				if (checkp == 0) {//존재하는 주민번호 확인
					if (checkm == 0) {//해당 주민번호 멤버에 있는지 확인
						out.println("는 이미 사용자에 등록된 주민번호입니다.<br><br>사용할 수 없습니다.<p>");
					} else {
						out.println("는 등록가능 합니다.");
					}
				} else {
					out.println("존재하지 않는 주민번호 입니다.");
				}
			}
		%>
		<a id="click" href="javascript:window.close()">닫기</a>
</body>
</html>