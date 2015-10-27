<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="memberId" value="${sessionScope.mid}"></c:set>

<%
		String name = (String) request.getAttribute("name");
				String num = (String) request.getAttribute("num");
				String num1 = num.substring(0, 6);
				String num2 = num.substring(6, 13);
				String zipcode = (String) request.getAttribute("zipcode");
				String deAd = (String) request.getAttribute("deAd");
				String[] ad = deAd.split(" ");
				String lastad = null;
				if (ad.length == 4) {
					lastad = ad[3];
					ad[3] = " ";
				} else {
					lastad = ad[4];
					for (int i = 0; i < ad.length; i++) {
						if (i > 4) {
							lastad += ad[i];
						}
					}
				}
	%>
<c:choose>
	<c:when test="${memberId != null}">
		<!DOCTYPE html>
		<html lang="ko">
<head>
<meta charset="euc-kr">
<!--UTF-8-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Register</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script>
	$(document).ready(function() {
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
				reader.onload = function(e) {
					//파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
					$('#blah').attr('src', e.target.result);
					//이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
					//(아래 코드에서 읽어들인 dataURL형식)
				}
				reader.readAsDataURL(input.files[0]);
				//File내용을 읽어 dataURL형식의 문자열로 저장
			}
		}//readURL()--

		//file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
		$("#image").change(function() {
			//alert(this.value); //선택한 이미지 경로 표시
			readURL(this);
		});
	});
</script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	padding-top: 80px;
	padding-bottom: 30px;
	background-image: url('line_red.jpg');
	background-size: cover;
	background-repeat: no-repeat;
}

.container {
	background-color: #ddd;
	background-image: url('bg_metal.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	border-radius: 5px;
	box-shadow: 10px 10px 5px #222222;
}

form {
	margin-top: 20px;
	margin-bottom: 20px;
}

input {
	text-align: center;
}

.filebox label {
	cursor: pointer;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
	color: #333;
	background-color: #fff;
	border-color: #ccc;
	display: inline-block;
	padding: 6px 12px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
}

.filebox input[type="file"] { /* 파일 필드 숨기기 */
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}
</style>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="./main.jsp">FID</a>
				</div>

				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">시민등록<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="./main/people/PeopleIn.jsp">등록</a></li>
								<li><a href="./peopleList.do">수정</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">멤버등록<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="./main/member/MemberRegister.jsp">등록</a></li>
								<li><a href="./memberList.do">수정/삭제</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">특이사항<span class="caret"></span></a>
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
					<form class="navbar-form navbar-right" role="search" method="post" action="./logout.jsp">
						<button type="submit" class="btn btn-danger">Logout</button>
					</form>
				</div>
			</div>
		</nav>
		<form class="form-horizontal" action="./peopleUp.do" method="post" name="registerForm" enctype="multipart/form-data">
			<div class="form-group">
				<div class="form-inline">
					<div class="form-horiziotal">
						<label for="image" class="control-label col-sm-2">사진 업로드</label>
						<div class="col-sm-2">
							<div id="imagePreview">
								<img class="img-thumbnail" id="blah" src="./Images/<%=num1+num2%>.jpg" style="width: 130px; height: 160px;" />
							</div>
							<div class="filebox col-md-12 col-md-offset-1" style="top: 5px">
								<label for="image">업로드</label> <input type="file" id="image" name="image">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group form-inline">
				<label for="name" class="control-label col-sm-2">이름</label> <label  id="name" name="name"><%=name %></label>
			</div>
			<div class="form-group form-inline"">
				<label for="idNum1" class="control-label col-sm-2">주민등록번호</label><label><%=num1 %></label>-<label><%=num2 %></label> <input type="number" style="text-align: center; visibility:hidden;  width: 100px;" class="form-control" id="idNum1" name="idNum1"
					size="6" maxlength="6"> <input type="number" style="text-align: center;visibility:hidden; width: 100px;" class="form-control" id="idNum2" name="idNum2" size="7" maxlength="7">
			</div>

			<div class="form-group form-inline">
				<label for="zip_code" class="control-label col-sm-2">우편번호</label> <input readonly="readonly" type="text" value="<%=zipcode %>" class="form-control" name="zip_code" id="zip_code" size="6" maxlength="6" placeholder="우편번호"> <input
					type="button" class="btn btn-default" onclick="Postcode()" value="우편번호 찾기">
			</div>
			<div class="form-group form-inline">
				<label for="sido" class="control-label col-sm-2" style="top: 5px; left: 5px; right: 5‒; margin-bottom: 5px; top: 5px; left: 5px; right: 5px;">주소</label> <input type="text" readonly="readonly"value="<%=ad[0] %>"class="form-control"
					name="sido" style="top: 5px; left: 5px; width: 130px; right: 5‒; margin-bottom: 5px; top: 5px; left: 5px; right: 5px;" id="sido" placeholder="도/시"> <input type="text"readonly="readonly" class="form-control"
					name="sigungu" value="<%=ad[1] %>" id="sigungu" style="top: 5px; left: 5px; right: 5‒; margin-bottom: 5px; top: 5px; left: 5px; right: 5px;" placeholder="군/구"> <input type="text" readonly="readonly"class="form-control"
					name="detail_1" id="detail_1" placeholder="건물명/동" value="<%=ad[2] %> <%=ad[3] %>"style="top: 5px; left: 5px; right: 5‒; margin-bottom: 5px; top: 5px; left: 5px; right: 5px;"> <input type="text" class="form-control"
					name="detail_2" id="detail_2" value="<%=lastad %>" placeholder="세부 주소" style="top: 5px; left: 5px; right: 5‒; margin-bottom: 5px; top: 5px; right: 5px;"><br>
			</div>
			<br>
			<div class="form-gruop">
				<button type="submit" class="btn btn-success col-sm-1 col-sm-offset-4">제출</button>
			</div>
			<br>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
		</html>
	</c:when>
	<c:otherwise>
		<c:redirect url="./Login.jsp"></c:redirect>
	</c:otherwise>
</c:choose>