<%@page import="daoto.peopleInfoDTO"%>
<%@page import="java.util.Vector"%>
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
<!--UTF-8-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Missing Register</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="../../js/checkinfo.js"></script>
<script type="text/javascript">
		var InputImage = (function loadImageFile() {
			if (window.FileReader) {
				var ImagePre;
				var ImgReader = new window.FileReader();
				var fileType = /^(?:image\/bmp|image\/gif|image\/jpeg|image\/png|image\/x\-xwindowdump|image\/x\-portable\-bitmap)$/i;
	
				ImgReader.onload = function(Event) {
					if (!ImagePre) {
						var newPreview = document.getElementById("imagePreview");
						ImagePre = new Image();
						ImagePre.style.width = "140px";
						ImagePre.style.height = "200px";
						newPreview.appendChild(ImagePre);
					}
					ImagePre.src = Event.target.result;
	
				};
	
				return function() {
	
					var img = document.getElementById("image").files;
	
					if (!fileType.test(img[0].type)) {
						alert("이미지 파일을 업로드 하세요");
						return;
					}
	
					ImgReader.readAsDataURL(img[0]);
				}
	
			}
	
			document.getElementById("imagePreview").src = document
					.getElementById("image").value;
	
		})();
	</script>
<script type="text/javascript">
		var checkPn = "0";
	
		function inputCheck(f) {
	
			if (f.name.value == "") {
				alert("이름을 입력해주세요");
				f.name.focus();
				return;
			}
	
			if (f.zip_code.value == "") {
				alert("지역정보를 입력해주세요");
				f.zip_code.focus();
				return;
			}
			if (f.idNum1.value == "" || f.idNum2.value == "") {
				alert("주빈번호를 입력하세요");
				f.idNum1.focus();
				return;
			}
			if (f.pro_idNum1.value == "" || f.pro_idNum2.value == "") {
				alert("보호자 주빈번호를 입력하세요");
				f.pro_idNum1.focus();
				return;
			}
			if (f.pro_phon.value == "") {
				alert("보호자 전화번호를 입력하세요");
				f.pro_phon.focus();
				return;
			}
			if (f.rank.value == "0") {
				alert("실종 종류를 선택해 주세요");
				f.rank.focus();
				return;
			}
			if (checkPn== "0") {
				alert("주민번호 중복을 확인해주세요");
				f.idNum1.focus();
				return;
			}
		
			f.action = '/FID/missingRegister.do';
			f.submit();
		}
	
		function numCheck(num) {
			checkPn = 1;
			tm = 1;
			var wx = 300; //open 할 윈도우 크기 width
			var wy = 150; //height
			var sx = screen.width;
			var sy = screen.height;
			var x = (sx - wx) / 2;
			var y = (sy - wy) / 2;
			if (num == "") {
				window.alert("주민번호를 입력해주세요");
				document.registerForm.idNum1.focus();
			} else {
				var url = "/FID/num_check.jsp?num=" + num+"&tm="+tm;//URL Get 방식 전송
				var wr = window.open(url, "주민번호 검색", "width=300,height=250");
				wr.moveTo(x, y);
			}
		}
	</script>


<link href="../../css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	padding-top: 80px;
	padding-bottom: 30px;
	background-image: url('../../line_red.jpg');
	background-size: cover;
	background-repeat: no-repeat;
}

.container {
	background-color: #ddd;
	background-image: url('../../bg_metal.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	border-radius: 5px;
	box-shadow: 10px 10px 5px #222222;
}

form {
	margin-top: 20px;
	margin-bottom: 20px;
}
</style>
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
					<a class="navbar-brand" href="../../main.jsp">FID</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">인적관리<span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="../../main/people/PeopleIn.jsp">등록</a></li>
								<li><a href="../../peopleList.do">수정</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">사용자관리<span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="../../main/member/MemberRegister.jsp">등록</a></li>
								<li><a href="../../memberList.do">수정/삭제</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">신상관리<span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li role="presentation" class="dropdown-header">등록</li>
								<li><a href="../../main/etc/crimeRegister.jsp">범죄</a></li>
								<li><a href="../../main/etc/missingReg.jsp">행방불명</a></li>
								<li><a href="../../main/etc/traceRegister.jsp">기타수배</a></li>
								<li role="presentation" class="divider"></li>
								<li><a href="../../peopleListforCMT.do">수정/삭제</a></li>
							</ul></li>
						<li><a href="../../chart/statistic.jsp">통계</a></li>
					</ul>
					<form class="navbar-form navbar-right" role="search" method="post"
						action="../../logout.jsp">
						<button type="submit" class="btn btn-danger">Logout</button>
					</form>
				</div>
			</div>
		</nav>
		<form class="form-horizontal" action="/FID/missingRegister.do"
			method="post" name="registerForm" enctype="multipart/form-data">
			<div class="form-group">
				<label for="image" class="control-label col-sm-2">사진
					갱신/추가(선택)</label>
				<div class="col-sm-2">
					<div id="imagePreview">
						<img class="img-thumbnail" id="blah" src="#"
							style="width: 130px; height: 160px;" />
					</div>
					<div class="filebox col-md-12 col-md-offset-1" style="top: 5px">
						<label for="image">업로드</label> <input type="file" id="image"
							name="image">
					</div>
					<!-- <div id="imagePreview">
							 	<img class="img-thumbnail" id="blah" src="#"
									style="width: 130px; height: 160px;" />
							</div>
							<div class="filebox col-md-12 col-md-offset-1" style="top: 5px">
								<label for="image">업로드</label> <input type="file" id="image"
									name="image">
							</div> -->
				</div>
			</div>
			<div class="form-group form-inline">
				<label for="name" class="control-label col-sm-2">이름</label> <input
					type="text" class="form-control" id="name" name="name" required="required"/>
			</div>
			<div class="form-group form-inline">
				<label for="idNum1" class="control-label col-sm-2">주민등록번호</label> <input
					type="text" class="form-control" id="idNum1" name="idNum1" size="6" maxlength="6" onkeypress="onlyNumber()" required="required" onkeyup="checkNum(this)"/>
				- <input type="text" class="form-control" id="idNum2" name="idNum2"
					size="7" maxlength="7" onkeypress="onlyNumber()" required="required" onkeyup="checkNum(this)"/> <input type="button" value="유효확인"
					onclick="numCheck(this.form.idNum1.value+this.form.idNum2.value)" />
			</div>

			<div class="form-group form-inline">
				<label for="rank" class="control-label col-sm-2">실종 유형</label> <select
					class="form-control" name="rank" id="rank" required="required">
					<option value="0" selected="">선택하세요</option>
					<option value="1">미아</option>
					<option value="2">청소년가출</option>
					<option value="3">실종</option>
					<option value="4">노인실종</option>
				</select>
			</div>
			<div class="form-group form-inline">
				<label for="missingDate" class="control-label col-sm-2">실종날짜</label>
				<input type="date" class="form-control" name="missingDate"
					id="missingDate" placeholder="연도-월-일" required="required">
			</div>
			<div class="form-group form-inline">
				<label for="zip_code" class="control-label col-sm-2">실종 장소</label> <input
					type="text" class="form-control" name="zip_code" id="zip_code"
					placeholder="우편번호" readonly="readonly" required="required"> <input type="button"
					onclick="Postcode()" value="우편번호 찾기">
			</div>
			<div class="form-group form-inline">
				<label class="control-label col-sm-2"> </label> <input type="text"
					class="form-control" name="sido" id="sido" placeholder="도/시" readonly="readonly">
				<input type="text" class="form-control" name="sigungu" id="sigungu"
					placeholder="군/구" readonly="readonly"> <input type="text" class="form-control"
					name="detail_1" id="detail_1" placeholder="건물명/동" readonly="readonly"> <input
					type="text" class="form-control" name="detail_2" id="detail_2"
					placeholder="세부 주소"><br>
			</div>
			<div class="form-group form-inline">
				<label for="name" class="control-label col-sm-2">보호자 전화번호</label> <input
					type="text" class="form-control" id="pro_phon" name="pro_phon" required="required"/>
			</div>
			<div class="form-group form-inline">
				<label for="pro_idNum1" class="control-label col-sm-2">보호자 주민번호</label>
				<input type="text" class="form-control" id="pro_idNum1"
					name="pro_idNum1" size="6" maxlength="6" onkeypress="onlyNumber()" required="required" onkeyup="checkNum(this)"/> - <input type="text"
					class="form-control" id="pro_idNum2" name="pro_idNum2"
					maxlength="7" size="7" onkeypress="onlyNumber()" required="required" onkeyup="checkNum(this)"/> <input type="button" value="유효확인"
					onclick="numCheck(this.form.pro_idNum1.value+this.form.pro_idNum2.value)" />
			</div>
			<div>
				<button class="btn btn-success btn-lg" type="submit"
					onclick="inputCheck(this.form);">확인</button>
			</div>
		</form>
	</div>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
	
			function Postcode() {
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
	
						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('zip_code').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('sido').value = data.sido;
						document.getElementById('sigungu').value = data.sigungu;
						document.getElementById('detail_1').value = extraAddr;
					}
				}).open();
			}
		</script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../../js/bootstrap.min.js"></script>
</body>
		</html>
	</c:when>
	<c:otherwise>
		<c:redirect url="../../Login.jsp"></c:redirect>
	</c:otherwise>
</c:choose>




