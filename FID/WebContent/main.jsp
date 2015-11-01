<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="memberId" value="${sessionScope.mid}"></c:set>
<c:choose>
	<c:when test="${memberId != null}">
		<!DOCTYPE html>
		<html lang="ko">
<head>
<meta charset="utf-8">
<!-- UTF-8 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script src="./js/jquery-2.1.4.min.js"></script>
<script>
	$(function() {
		//테이블 리스트 get
		$
				.ajax({
					url : "./main.do",
					dataType : "json",
					success : function(data) {
						$(data.Crime).each(
								function(index, item) {
									$("#crime tbody:last").append(
											"<tr id="+item.num+" class=crimeG><td>"
													+ item.name + "</td><td>"
													+ item.adr + "</td></tr>");
								});
						$(data.Missing)
								.each(
										function(index, item) {
											$("#missing-indicators")
													.append(
															"<li data-target=#carousel-example-generic data-slide-to="+index+"></li>");

											if (index == 0) {
												$('#missing-inner')
														.append(
																"<div class=item active><img src=./Images/"+item.num+".jpg id="+item.num+" class=img-responsive innerimg missingG><div class=carousel-caption><h3>"+item.name+"</h3><p>"+item.adr+"</p></div></div>");
											}
											else{
												$('#missing-inner')
												.append(
														"<div class=item><img src=./Images/"+item.num+".jpg id="+item.num+" class=img-responsive innerimg missingG><div class=carousel-caption><h3>"+item.name+"</h3><p>"+item.adr+"</p></div></div>");
									}

										});
						$(data.Trace).each(
								function(index, item) {
									$("#trace tbody:last").append(
											"<tr id="+item.num+" class=traceG><td>"
													+ item.name + "</td><td>"
													+ item.adr + "</td></tr>");
								});

					}
				});

		$('#crime').on('click', '.crimeG', function() {
			var id = $(this).attr('id');
			var $form = $('<form></form>');
			$form.attr('action', './detailInforServletForCMT.do');
			$form.attr('method', 'post');
			$form.appendTo('body');

			var num = $('<input type="hidden" value= '+id+' name="num">');

			$form.append(num)
			$form.submit();
		});

		$('#missing').on('click', '.missingG', function() {
			var id = $(this).attr('id');
			var $form = $('<form></form>');
			$form.attr('action', './detailInforServletForCMT.do');
			$form.attr('method', 'post');
			$form.appendTo('body');

			var num = $('<input type="hidden" value= '+id+' name="num">');

			$form.append(num)
			$form.submit();
		});

		$('#trace').on('click', '.traceG', function() {
			var id = $(this).attr('id');
			var $form = $('<form></form>');
			$form.attr('action', './detailInforServletForCMT.do');
			$form.attr('method', 'post');
			$form.appendTo('body');

			var num = $('<input type="hidden" value= '+id+' name="num">');

			$form.append(num)
			$form.submit();
		});

	});
</script>




<title>F.I.D</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	padding-top: 50px;
	background-image: url('line_red.jpg');
	background-size: cover;
	background-repeat: no-repeat;
}

.col-md-4 {
	background-color: #fff;
	border: 1px solid black;
}
</style>
<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!--[if lt IE 9]>
		      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		    <![endif]-->
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="main.jsp">FID</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">인적관리<span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="main/people/PeopleIn.jsp">등록</a></li>
								<li><a href="./peopleList.do">수정</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">사용자관리<span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="main/member/MemberRegister.jsp">등록</a></li>
								<li><a href="./memberList.do">수정/삭제</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">신상관리<span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li role="presentation" class="dropdown-header">등록</li>
								<li><a href="main/etc/crimeRegister.jsp">범죄</a></li>
								<li><a href="main/etc/missingReg.jsp">행방불명</a></li>
								<li><a href="main/etc/traceRegister.jsp">기타수배</a></li>
								<li role="presentation" class="divider"></li>
								<li><a href="./peopleListforCMT.do">수정/삭제</a></li>
							</ul></li>
						<li><a href="chart/statistic.jsp">통계</a></li>
					</ul>
					<form class="navbar-form navbar-right" role="search" method="post"
						action="logout.jsp">
						<button type="submit" class="btn btn-danger">Logout</button>
					</form>
				</div>
			</div>
		</nav>
		<div class="container" style="padding-top: 50px;">
			<!-- Example row of columns -->
			<div class="row" style="width: 1300px">
				<div class="col-md-4" style="width: 410px">
					<h2>범죄</h2>
					<table class="table table-hover" id="crime">
						<thead>
							<tr>
								<th>범죄자</th>
								<th>거주 지역</th>
							</tr>
						</thead>
						<tbody>
						</tbody>

					</table>
				</div>
				<div class="col-md-4" style="width: 410px">
					<h2>실종</h2>
					<table class="table table-hover" id="missing">
						<table class="table table-hover">
							<div id="carousel-example-generic" class="carousel slide"
								data-ride="carousel">
								<!-- Indicators -->
								<ol class="carousel-indicators" id="missing-indicators">
								</ol>

								<!-- Wrapper for slides -->
								<div class="carousel-inner" role="listbox" id="missing-inner">
								</div>

								<!-- Controls -->
								<a class="left carousel-control"
									href="#carousel-example-generic" role="button"
									data-slide="prev"> <span
									class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
									<span class="sr-only">Previous</span>
								</a> <a class="right carousel-control"
									href="#carousel-example-generic" role="button"
									data-slide="next"> <span
									class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
									<span class="sr-only">Next</span>
								</a>
							</div>
						</table>
					</table>
				</div>
				<div class="col-md-4" style="width: 410px">
					<h2>수배</h2>
					<table class="table table-hover" id="trace">
						<thead>
							<tr>
								<th>수배자</th>
								<th>거주지역</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
</body>
		</html>
	</c:when>
	<c:otherwise>
		<c:redirect url="Login.jsp"></c:redirect>
	</c:otherwise>
</c:choose>