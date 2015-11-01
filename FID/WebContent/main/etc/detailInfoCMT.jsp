<%@page import="daoto.PeopleInDTO"%>
<%@page import="daoto.PeopleInDAO"%>
<%@page import="daoto.MissingpeDAO"%>
<%@page import="daoto.MissingpeDTO"%>
<%@page import="daoto.AddressCodeDTO"%>
<%@page import="daoto.AddressCodeDAO"%>
<%@page import="daoto.TraceDTO"%>
<%@page import="daoto.MissingDTO"%>
<%@page import="daoto.CrimeDTO"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="../../js/checkinfo.js"></script>
<script type="text/javascript">
	function updateCrime(date) {
		var t = date;
		t.action = './updateCrime.do';
		t.submit();
	}
	function updateTrace(date) {
		var t = date;
		t.action = '/FID/updateTrace.do';
		t.submit();
	}
	function updateMissing(date) {
		var t = date;
		t.action = '/FID/updateMissing.do';
		t.submit();
	}

	function deleteCrime(date) {
		var t = date;
		t.action = '/FID/deleteCrime.do';
		t.submit();
	}

	function deleteMissing(date) {
		var t = date;
		t.action = '/FID/deleteMissing.do';
		t.submit();
	}

	function deleteTrace(date) {
		var t = date;
		t.action = '/FID/deleteTrace.do';
		t.submit();
	}
</script>

<style type="text/css">
.sub {
	display: none;
	visibility: hidden;
}
</style>
<style type="text/css">
.sub {
	display: none;
	visibility: hidden;
}

.container {
	border: 1px solid white;
	background-color: #fff;
	box-shadow: 10px 10px 5px #222222;
	border-radius: 5px;
}

body {
	padding-top: 80px;
	background-image: url('line_red.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	background-color: red;
}
</style>
<title>대상 세부 정보</title>
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
					<a class="navbar-brand" href="./main.jsp">FID</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">인적관리<span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="./main/people/PeopleIn.jsp">등록</a></li>
								<li><a href="./peopleList.do">수정</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">사용자관리<span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="./main/member/MemberRegister.jsp">등록</a></li>
								<li><a href="./memberList.do">수정/삭제</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">신상관리<span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li role="presentation" class="dropdown-header">등록</li>
								<li><a href="./main/etc/crimeRegister.jsp">범죄</a></li>
								<li><a href="./main/etc/missingReg.jsp">행방불명</a></li>
								<li><a href="./main/etc/traceRegister.jsp">기타수배</a></li>
								<li role="presentation" class="divider"></li>
								<li><a href="./peopleListforCMT.do">수정/삭제</a></li>
							</ul></li>
						<li><a href="./chart/statistic.jsp">통계</a></li>
					</ul>
					<form class="navbar-form navbar-right" role="search" method="post"
						action="logout.jsp">
						<button type="submit" class="btn btn-danger">Logout</button>
					</form>
				</div>
			</div>
		</nav>

		<table class="table">
			<tr>
				<td>
					<div id="imagePreview">
						<img class="img-thumbnail" id="blah"
							src="./Images/${requestScope.peopleNum}.jpg"
							style="width: 130px; height: 160px;" />
					</div>
				</td>
				<td>
					<div>
						<table class="table">
							<tr>
								<td>이 름 : ${requestScope.name }</td>
							</tr>
							<tr>
								<td>주민번호 : ${requestScope.peopleNum }</td>
							</tr>
							<tr>
								<td>주 소 : ${requestScope.zipcode } ${requestScope.address }</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>

		<fieldset id="crime">
			<table class="table table-bordered crimetable">
				<tr style="background-color: cyan">
					<th>범죄 종류</th>
					<th>범죄 지역</th>
					<th>범죄 발생일</th>
					<th>수정/삭제</th>
				</tr>
				<c:set var="vCDTO" value="${requestScope.CrimeArea}"></c:set>
				<c:forEach var="crime" items="${vCDTO}" varStatus="status">
					<tr>
						<form name="${crime.crimeDate}" method="post">
							<input type="text" class="sub" name="num"
								value="${crime.peopleNum}"> <input type="text"
								class="sub" name="pri" value="${crime.pri}">
							<td><select class="form-control" name="rank" id="rank">
									<c:choose>
										<c:when test="${crime.crimeCode == 1}">
											<option value="1" selected>살인
											<option value="2">절도
											<option value="3">폭행
											<option value="4">성폭행
										</c:when>
										<c:when test="${crime.crimeCode == 2}">
											<option value="1">살인
											<option value="2" selected>절도
											<option value="3">폭행
											<option value="4">성폭행
										</c:when>
										<c:when test="${crime.crimeCode == 3}">
											<option value="1">살인
											<option value="2">절도
											<option value="3" selected>폭행
											<option value="4">성폭행
										</c:when>
										<c:when test="${crime.crimeCode == 4}">
											<option value="1">살인
											<option value="2">절도
											<option value="3">폭행
											<option value="4" selected>성폭행
										</c:when>
									</c:choose>
							</select></td>
							<td><input type="text" class="form-control"
								name="c_zip_code" id="c_zip_code" value="${crime.crimeArea}"
								onclick="Postcode(this.form,'c')"></td> <input type="text"
								class="sub" name="c_sido" id="c_sido" placeholder="도/시">
							<input type="text" class="sub" name="c_sigungu" id="c_sigungu"
								placeholder="군/구"> <input type="text" class="sub"
								name="c_detail_1" id="c_detail_1" placeholder="건물명/동">
							<td><input class="form-control" type="date" id="date"
								name="date" value="${crime.crimeDate}"
								pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))
		       		"></td>
							<td><input type="button" class="btn btn-default" value="수정"
								onclick="updateCrime(this.form)"> <input type="button"
								value="삭제" class="btn btn-default"
								onclick='deleteCrime(this.form)'></td>
						</form>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
		<br>
		<fieldset id="trace">
			<table class="table table-bordered tracetable">
				<tr style="background-color: cyan">
					<th>수배 종류</th>
					<th>수배 금액</th>
					<th>수배 상태</th>
					<th>수배 등록일</th>
					<th>수정/삭제</th>
				</tr>
				<c:set var="vTDTO" value="${requestScope.TraceArea}"></c:set>
				<c:forEach var="trace" items="${vTDTO }">
					<tr>
						<form name="${trace.traceDate }" method="post">
							<input type="text" class="sub" name="num"
								value=${trace.peopleNum }> <input type="text"
								class="sub" name="pri" value=${trace.pri }>
							<td><select class="form-control" name="rank" id="rank">
									<c:choose>
										<c:when test="${'지명수배' eq trace.traceCode }">
											<option value="a" selected>지명수배</option>
											<option value="b">벌금수배</option>
											<option value="c">지명통보</option>
										</c:when>
										<c:when test="${'벌금수배' eq trace.traceCode}">
											<option value="a">지명수배</option>
											<option value="b" selected>벌금수배</option>
											<option value="c">지명통보</option>
										</c:when>
										<c:when test="${'지명통보' eq trace.traceCode}">
											<option value="a">지명수배</option>
											<option value="b">벌금수배</option>
											<option value="c" selected>지명통보</option>
										</c:when>
									</c:choose>
							</select></td>
							<td><input class="form-control" type="T_money"
								name="T_money" value="${trace.t_Money }" size="6"></td>
							<td><select class="form-control" name="traceCondition"
								id="traceCondition">
									<c:choose>
										<c:when test="${'Y' eq trace.traceCondition}">
											<option value="Y" selected>진행상태</option>
											<option value="N">종료상태</option>
										</c:when>
										<c:when test="${'N' eq trace.traceCondition}">
											<option value="Y">진행</option>
											<option value="N" selected>종료</option>
										</c:when>
									</c:choose>
							</select></td>
							<td><input class="form-control" type="date" name="date"
								value="${trace.traceDate }"
								pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))
		       		"></td>
							<td><input type="button" value="수정" class="btn btn-default"
								onclick="updateTrace(this.form)"> <input type="button"
								value="삭제" onclick='deleteTrace(this.form)' class="btn btn-default"></td>
						</form>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
		<br>
		<fieldset id="missing">
			<form action="./updatePro.do">
				<c:set var="phon" value="${requestScope.proPhon }"></c:set>
				<c:set var="Name" value="${requestScope.proName }"></c:set>
				<table>
					<tr>
						<th>보호자 이름</th>
						<td>${Name}</td>
						<th>보호자 수정</th>
						<td>&nbsp;&nbsp;</td>
						<td>보호자 주민번호</td>
						<td><input type="text"
							style="width: 110px; display: inline-block; height: 25px;"
							class="form-control" id="proNum" name="proNum"
							onfocus="defoultPropn()" size="6" pattern="[0-9]{13}"
							maxlength="13" onkeypress="onlyNumber()" required="required"
							placeholder="ex)1111111235435" onkeyup="checkNum(this)" /></td>
						<td><input type="text" class="sub" name="num"
							value="${requestScope.peopleNum }"></td>
					</tr>
					<tr>
						<th>보호자 전화번호</th>
						<td><a data-rel="external" href="tel:${phon}" target="_blank">${phon}</a></td>
						<td></td>
						<td></td>
						<td>보호자 전화번호</td>
						<td><input type="text" class="form-control" id="proPhon"
							style="width: 110px; display: inline-block; height: 25px;"
							name="proPhon"></td>
						<td><input type="submit" class="btn btn-default" value="수정"></td>
					</tr>
				</table>

			</form>
			<table class="table table-bordered missingtable">
				<tr style="background-color: cyan">
					<th>실종 종류</th>
					<th>실종 지역</th>
					<th>실종 등록일</th>
					<th>발견 지역</th>
					<th>발견 등록일</th>
					<th>수정/삭제</th>
				</tr>
				<c:set var="vMDTO" value="${requestScope.MissingArea}"></c:set>
				<c:forEach var="missing" items="${vMDTO }">
					<tr>
						<form name="${missing.missingDate}" method="post">
							<input type="text" class="sub" name="pri" value=${missing.pri }>
							<input type="text" class="sub" name="num"
								value="${missing.peopleNum}">
							<td><select class="form-control" name="mrank" id="mrank">
									<c:choose>
										<c:when test="${'미아' eq missing.missingCode}">
											<option value="1" selected>미아
											<option value="2">청소년 가출
											<option value="3">실종
											<option value="4">노인 실종
										</c:when>
										<c:when test="${'청소년 가출' eq missing.missingCode}">
											<option value="1">미아
											<option value="2" selected>청소년 가출
											<option value="3">실종
											<option value="4">노인 실종
										</c:when>
										<c:when test="${'실종' eq missing.missingCode}">
											<option value="1">미아
											<option value="2">청소년 가출
											<option value="3" selected>실종
											<option value="4">노인 실종
										</c:when>
										<c:when test="${'노인 실종' eq missing.missingCode}">
											<option value="1">미아
											<option value="2">청소년 가출
											<option value="3">실종
											<option value="4" selected>노인 실종
										</c:when>
									</c:choose>
							</select></td>
							<td><input type="text" class="form-control"
								name="m_zip_code" id="m_zip_code"
								value="${missing.missingAddress}"
								onclick="Postcode(this.form,'m')"></td> <input type="text"
								class="sub" name="m_sido" id="m_sido" placeholder="도/시">
							<input type="text" class="sub" name="m_sigungu" id="m_sigungu"
								placeholder="군/구"> <input type="text" class="sub"
								name="m_detail_1" id="m_detail_1" placeholder="건물명/동">
							<td><input type="date" name="mdate" class="form-control"
								value="${missing.missingDate}"
								pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))
		       		"></td>
							<td><input type="text" class="form-control"
								name="f_zip_code" id="f_zip_code" value="${missing.findAddress}"
								onclick="Postcode(this.form,'f')"></td> <input type="text"
								class="sub" name="f_sido" id="f_sido" placeholder="도/시">
							<input type="text" class="sub" name="f_sigungu" id="f_sigungu"
								placeholder="군/구"> <input type="text" class="sub"
								name="f_detail_1" id="f_detail_1" placeholder="건물명/동">
							<td><input type="date" name="fdate" class="form-control"
								value="${missing.findDate}"
								pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))
		       		"></td>
							<td><input type="button" value="수정"
								onclick="updateMissing(this.form)"> <input type="button"
								value="삭제" onclick='deleteMissing(this.form)'></td>
						</form>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
		<br>
	</div>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
		function Postcode(tform, kind) {
			new daum.Postcode({
				oncomplete : function(data) {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

					// 각 주소의 노출 규칙에 따라 주소를 조합한다.
					// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					var fullAddr = ''; // 최종 주소 변수
					var extraAddr = ''; // 조합형 주소 변수

					// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
					if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
						fullAddr = data.sido + ' ' + data.sigungu;

					} else { // 사용자가 지번 주소를 선택했을 경우(J)
						fullAddr = data.sido + ' ' + data.sigungu;
					}

					// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
					if (data.userSelectedType === 'R') {
						//법정동명이 있을 경우 추가한다.
						if (data.bname !== '') {
							extraAddr += data.bname;
						}
						// 건물명이 있을 경우 추가한다.
						if (data.buildingName !== '') {
							extraAddr += (extraAddr !== '' ? ' '
									+ data.buildingName : data.buildingName);
						}

					}
					if (data.userSelectedType === 'J') {
						//법정동명이 있을 경우 추가한다.
						if (data.bname !== '') {
							extraAddr += data.bname;
						}
						// 건물명이 있을 경우 추가한다.
						if (data.postcode !== '') {
							extraAddr += (extraAddr !== '' ? ' '
									+ data.postcode : data.postcode);
						}

					}
					var zipcode = $(tform).closest("tr");
					console.log(zipcode.find('#' + kind + '_zip_code').val());
					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					zipcode.find('#' + kind + '_zip_code').val(data.zonecode);
					zipcode.find('#' + kind + '_sido').val(data.sido);
					zipcode.find('#' + kind + '_sigungu').val(data.sigungu);
					zipcode.find('#' + kind + '_detail_1').val(data.extraAddr);

				}
			}).open();
		}
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>

