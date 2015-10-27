<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="memberId" value="${sessionScope.mid}"></c:set>
<c:choose>
	<c:when test="${memberId != null}">
		<c:redirect url="main.jsp" />
	</c:when>
	<c:otherwise>
		<!DOCTYPE html>
		<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Sign in</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	padding-top: 40px;
	background-color: #ccc;
	background-image: url('line_red.jpg');
	background-size: cover;
	background-repeat: no-repeat;
}

h1 {
	text-align: center;
	font-size: 80px;
	color: #265A88;
	text-shadow: 5px 5px 5px #222222;
	font-style: italic;
	font-weight: bolder;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
	background-color: #fff;
	border-radius: 6px;
	box-shadow: 5px 5px 5px #222222;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

.btn {
	border-color: red;
	background-color: #C12E2A;
}
</style>
<script src="js/ie-emulation-modes-warning.js"></script>

</head>

<body>
	<h1>F.I.D</h1>
	<div class="container center-block">

		<form class="form-signin" action="./loginCheck.do" method="post">
			<h2 class="form-signin-heading">Login</h2>
			<label for="inputId" class="sr-only">ID</label> <input type="text" id="inputId" name="id" class="form-control" placeholder="ID" required autofocus> <label for="inputPassword"
				class="sr-only">Password</label> <input type="password" id="inputPassword" name="pw" class="form-control" placeholder="PW" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me"> Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>

	</div>
	<script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
		</html>
	</c:otherwise>
</c:choose>
