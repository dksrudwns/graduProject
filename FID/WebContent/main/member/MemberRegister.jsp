<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="memberId" value="${sessionScope.mid}"></c:set>
<c:choose>
	<c:when test="${memberId != null}">
	<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="euc-kr"> <!--UTF-8-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Member Register</title>
	<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script>

	var checkId = "0";
	var checkPn = "0";

	function inputCheck(f) {
	
		if (f.repassword.value != f.password.value) {
			alert("비밀번호가 일치하지 않습니다.");
			f.repassword.focus();
			return;
		}

		if(checkId == "0"){
			alert("아이디 중복을 확인해주세요");
			f.id.focus();
			return;
		}
		if(checkPn == "0"){
			alert("주민번호 중복을 확인해주세요");
			f.password.focus();
			return;
		}
		
		f.action='/FID/memberInrol.do';
		f.submit();
	}
	function passCheck(){
		   var pass1 = document.getElementById("password");
		   var pass2 = document.getElementById("repassword");
		   var msg = document.getElementById("checkSame");
		   if(pass1.value!=pass2.value){
		      msg.innerText="Incorrect!";
		      msg.style.color="red";
		      msg.style.opacity=1;
		   }
		   else{
		      msg.innerText="Correct!";
		      msg.style.color="green";
		      msg.style.opacity=1;
		      }
		}
	
	
	function idCheck(id) {
		checkId = 1;
		var wx = 300; //open 할 윈도우 크기 width
		var wy = 150; //height
		var sx = screen.width;
		var sy = screen.height;
		var x = (sx - wx) / 2;
		var y = (sy - wy) / 2;
		if (id == "") {
			window.alert("아이디를 입력해주세요");
			document.regForm.id.focus();
		} else {
			var url = "/FID/id_check.jsp?id=" + id;//URL Get 방식 전송
			var wr = window.open(url, "아이디검색", "width=300,height=250");
			wr.moveTo(x, y);
		}
	}

	function numCheck(num) {
		checkPn = 1;
		var tm =0;
		var wx = 300; //open 할 윈도우 크기 width
		var wy = 150; //height
		var sx = screen.width;
		var sy = screen.height;
		var x = (sx - wx) / 2;
		var y = (sy - wy) / 2;
		if (num == "") {
			window.alert("주민번호를 입력해주세요");
			document.regForm.idNum1.focus();
		} else {
			var url = "/FID/num_check.jsp?num=" + num+"&tm="+tm;//URL Get 방식 전송
			var wr = window.open(url, "주민번호 검색", "width=300,height=250");
			wr.moveTo(x, y);
		}
	}

	</script>
	<script src="../../js/checkinfo.js"></script>
	<link href="../../css/bootstrap.min.css" rel="stylesheet">
	<style>
	body{
		padding-top:80px;
		padding-bottom:30px;
		background-image:url('../../line_red.jpg');
		background-size:cover;
		background-repeat:no-repeat;
	}
	.container{
		background-color:#ddd;
		background-image:url('../../bg_metal.jpg');
		background-size:cover;
		background-repeat:no-repeat;
		border-radius:5px;
		box-shadow:10px 10px 5px #222222;
	}
	form{
		margin-top:20px;
		margin-bottom:20px;
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
			      <a class="navbar-brand" href="../../main.jsp">FID</a>
			    </div>
			
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">인적관리<span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			            <li><a href="../../main/people/PeopleIn.jsp">등록</a></li>
			            <li><a href="../../peopleList.do">수정</a></li>
			          </ul>
			        </li>
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">사용자관리<span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			            <li><a href="../../main/member/MemberRegister.jsp">등록</a></li>
			            <li><a href="../../memberList.do">수정/삭제</a></li>
			          </ul>
			        </li>
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">신상관리<span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			            <li role="presentation" class="dropdown-header">등록</li>
			            <li><a href="../../main/etc/crimeRegister.jsp">범죄</a></li>
			            <li><a href="../../main/etc/missingReg.jsp">행방불명</a></li>
			            <li><a href="../../main/etc/traceRegister.jsp">기타수배</a></li>
			            <li role="presentation" class="divider"></li>
			            <li><a href="../../peopleListforCMT.do">수정/삭제</a></li>
			          </ul>
			        </li>
			        <li><a href="../../chart/statistic.jsp">통계</a></li>
			      </ul>
			      <form class="navbar-form navbar-right" role="search" method="post" action="../../logout.jsp">
			        <button type="submit" class="btn btn-danger">Logout</button>
			      </form>
			    </div>
			  </div>
			</nav>
         <form class="form-horizontal" action="/FID/memberInrol.do" method="post" name="regForm">
            <div class="form-group form-inline">
                  <label for="id" class="control-label col-sm-2">ID</label>
                  <input type="text" class="form-control" id="id" name="id" required="required"/>
                  <input type="button" onclick="idCheck(this.form.id.value)" value="ID중복확인">
            </div>
            <div class="form-group form-inline">
                  <label for="password" class="control-label col-sm-2">비밀번호</label>
                  <input type="password" class="form-control" id="password" name="password" required="required"/>
            </div>
            <div class="form-group form-inline">
                  <label for="repassword" class="control-label col-sm-2">비밀번호확인</label>
                  <input type="password" class="form-control" id="repassword" name="repassword" required="required" onkeyup="passCheck()"/>
            	  <span id="checkSame" style="opacity:0;"></span>
            </div>
            <div class="form-group form-inline">
                  <label for="idNum1" class="control-label col-sm-2">주민등록번호</label>
                  <input type="text" pattern="[0-9]{6}" title="6자리 입력" maxlength="6" required="required"class="form-control" id="idNum1" name="idNum1" size="6" onkeypress="onlyNumber()" onkeyup="checkNum(this)"/> - <input type="text" maxlength="7" pattern="[0-9]{7}" title="7자리 입력" required="required" class="form-control" id="idNum2" name="idNum2" size="7" onkeypress="onlyNumber()" onkeyup="checkNum(this)"/>
                  <input type="button" required="required" value="중복확인" onclick="numCheck(this.form.idNum1.value+this.form.idNum2.value)" />
            </div>
	        <div class="form-group form-inline">
	            <label for="rank" class="control-label col-sm-2">계정 등급</label>
	            <select class="form-control" name="rank" id="rank" required="required">
	            	<option value="0" selected="">선택하세요</option>
	            	<option value="admin">관리자</option>
	            	<option value="user">사용자</option>
	            </select>
            </div>
            <div>
            <button class="btn btn-success btn-lg" type="submit" onclick="inputCheck(this.form);">확인</button>
            </div>
         </form>
      </div>
   	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</body>
</html>
	</c:when>
	<c:otherwise>
		<c:redirect url="../../Login.jsp"></c:redirect>	
	</c:otherwise>
</c:choose>