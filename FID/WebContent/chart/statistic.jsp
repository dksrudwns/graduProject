<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="memberId" value="${sessionScope.mid}"></c:set>
<c:choose>
	<c:when test="${memberId != null}">
		<!DOCTYPE html>
		<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="../js/changeChart.js"></script>
<style>
.regionSelect {
	padding: 5px;
}

iframe {
	float: left;
	padding: 20px;
	border: none;
	border-radius: 5px;
	position: absolute;
	width: 800px;
	height: 650px;
	box-shadow: 10px 10px 5px #222222;
}

body {
	padding-top: 80px;
	background-image: url('../line_red.jpg');
	background-color: #eeeeee;
	background-size : cover;
	background-repeat: no-repeat;
}
</style>
</head>
<body >
	<div class="container">
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="../main.jsp">FID</a>
				</div>

				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">인적관리<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="../main/people/PeopleIn.jsp">등록</a></li>
								<li><a href="../peopleList.do">수정</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">사용자관리<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="../main/member/MemberRegister.jsp">등록</a></li>
								<li><a href="../memberList.do">수정/삭제</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">신상관리<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li role="presentation" class="dropdown-header" onclick="changeColumn()">등록</li>
								<li><a href="../main/etc/crimeRegister.jsp">범죄</a></li>
								<li><a href="../main/etc/missingReg.jsp">행방불명</a></li>
								<li><a href="../main/etc/traceRegister.jsp">기타수배</a></li>
								<li role="presentation" class="divider"></li>
								<li><a href="../peopleListforCMT.do">수정/삭제</a></li>
							</ul></li>
						<li><a href="../chart/statistic.jsp">통계</a></li>
					</ul>
					<form class="navbar-form navbar-right" role="search" method="post" action="../logout.jsp">
						<button type="submit" class="btn btn-danger">Logout</button>
					</form>
				</div>
			</div>
		</nav>
		<div class="list-group" style="width: 175px; display: inline-block;">
			<a href="#" class="list-group-item" onclick="changeColumn()">신상관리 세부 통계</a> 
			<a href="#" class="list-group-item" onclick="changePie()">신상관리  통계</a> 
			<a href="#" class="list-group-item" onclick="showMonthChart()">월별통계</a> 
			<a href="#" class="list-group-item" onclick="showAreaChart()">행방불명통계</a>
		</div>
		<iframe src="totalChart.jsp" id="chartFrame" ></iframe>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>
</body>
		</html>
	</c:when>
	<c:otherwise>
		<c:redirect url="../Login.jsp"></c:redirect>
	</c:otherwise>
</c:choose>