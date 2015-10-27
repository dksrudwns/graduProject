<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="memberId" value="${sessionScope.mid}"></c:set>
<c:choose>
	<c:when test="${memberId != null}">
		<!DOCTYPE html>
	<html>
	<head>
	    <meta charset="utf-8"> <!-- 인코딩 UTF-8 ...?-->
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>사람 정보 수정/삭제</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
		<script src="./js/jquery-2.1.4.min.js"></script>
	<script>
	function myUp(form) {
		var t = form;
	      t.action='/FID/memberUp.do';
	      t.submit();
	  }
	
	  function myDel(form) {
		  var t = form;
	      t.action='/FID/memberDel.do';
	      t.submit();
	  }
	</script>
	<style>
				.container{
					border:1px solid white;
					background-color:#fff;
					box-shadow:10px 10px 5px #222222;
					border-radius:5px;
				}
				body{
					padding-top:80px;
					background-image:url('line_red.jpg');
					background-size:cover;
					background-repeat:no-repeat;
					background-color:red;
				}
			</style>
		    
		  </head>
		  <body>
		  		<div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top">
			  <div class="container-fluid">
			    <div class="navbar-header">
			      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      </button>
			      <a class="navbar-brand" href="./main.jsp">FID</a>
			    </div>
			
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">시민등록<span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			            <li><a href="./main/people/PeopleIn.jsp">등록</a></li>
			            <li><a href="./peopleList.do">수정</a></li>
			          </ul>
			        </li>
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">멤버등록<span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			            <li><a href="./main/member/MemberRegister.jsp">등록</a></li>
			            <li><a href="./memberList.do">수정/삭제</a></li>
			          </ul>
			        </li>
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">특이사항<span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			            <li role="presentation" class="dropdown-header">등록</li>
			            <li><a href="./main/etc/crimeRegister.jsp">범죄</a></li>
			            <li><a href="./main/etc/missingReg.jsp">행방불명</a></li>
			            <li><a href="./main/etc/traceRegister.jsp">기타수배</a></li>
			            <li role="presentation" class="divider"></li>
			            <li><a href="./peopleListforCMT.do">수정/삭제</a></li>
			          </ul>
			        </li>
			        <li><a href="./chart/statistic.jsp">통계</a></li>
			      </ul>
			      <form class="navbar-form navbar-right" role="search" method="post" action="logout.jsp">
			        <button type="submit" class="btn btn-danger">Logout</button>
			      </form>
			    </div>
			  </div>
			</nav>	
	<table class="table table-hover">
		<tr>
			<th>계정</th>
			<th>비밀번호</th>
			<th>등급</th>
			<th>주민번호</th>
			<th>수정/삭제</th>
		</tr>
		
		<c:set var="list" value="${requestScope.list}"></c:set>
			<c:forEach var="member" items="${list}">
			<tr>
				<form method="post" id= "${member.id}">
					<td>${member.id}<input name="id" style='visibility:hidden;display: none;' value="${member.id}"></td>
					<td><input name="pw" value="${member.pw }" required></td>
					<td><select name="rank" id="rank">
					<c:choose>
					<c:when test="${member.lv == 1}">
						<option value="0">선택하세요
						<option value="admin" selected>관리자
						<option value="user">사용자
					</c:when>
					<c:otherwise>
						<option value="0">선택하세요
						<option value="admin">관리자
						<option value="user" selected>사용자
					</c:otherwise>
					</c:choose>
					</select></td>
					<td>${member.pn}</td>
					<td><input type="button" id="up" name="up" value="up" onclick="myUp(this.form)" /> <input type="button" id="del"name="del" value="del" onclick='myDel(this.form)'/></td>
				</form>
			</tr>
			</c:forEach>	
			
		</table>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	    <script src="js/bootstrap.min.js"></script>
	</body>
	</html>
	</c:when>
	<c:otherwise>
		<c:redirect url="Login.jsp"></c:redirect>	
	</c:otherwise>
</c:choose>
