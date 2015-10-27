<%@page import="daoto.searchLocalMDTO"%>
<%@page import="daoto.searchLocalCDTO"%>
<%@page import="daoto.searchLocalTDTO"%>
<%@page import="java.util.Vector"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<title>실종</title>
<style type="text/css">
h1 {
	text-align: center;
}

fieldset {
	padding: 10px;
}

.pic {
	width: 150px;
	height: 230px;
	position: relative;
	margin: auto;
	border: 1px solid transparent;
	text-align: center;
}

hr {
	border: none;
	border: 1px dashed red;
}

#missingT {
	/* visibility:hidden 안보이게하기(영역은 그대로 차지)*/
	
}

#crimeT {
	/*display: none;
	/* visibility:hidden 안보이게하기(영역은 그대로 차지)*/
	
}

#wantedT {
	/*display: none;
	/* visibility:hidden 안보이게하기(영역은 그대로 차지)*/
	
}
</style>
</head>
<body>
	<%
		Vector<searchLocalMDTO> missigRs = (Vector<searchLocalMDTO>) request.getAttribute("missigRs");
		Vector<searchLocalCDTO> CrimeRs = (Vector<searchLocalCDTO>) request.getAttribute("CrimeRs");
		Vector<searchLocalTDTO> TraceRs = (Vector<searchLocalTDTO>) request.getAttribute("TraceRs");
	%>
	<div id="missingT">
		<h1>실 종</h1>
		<fieldset>

			<%
				for (int i = 0; i < missigRs.size(); i++) {
					searchLocalMDTO mDTO = missigRs.get(i);
					String URL = "Images/" + mDTO.getPeopleNum() + ".jpg";
			%>
			<form method="post" action="/FID/mDetailInforServlet.do">
				<div class="pic">
					<button>
						<span style="display: inline-block;"><img src=<%=URL%> width="150px" height="200px" /></span>
					</button>
					<input type="hidden" name="num" value=<%=mDTO.getPeopleNum()%>>
					<%=mDTO.getName()%>
				</div>
			</form>
			<%
				}
			%>

		</fieldset>
		<br />
		<hr />
	</div>
	<div id="crimeT">
		<h1>범 죄</h1>
		<fieldset>

			<%
				for (int i = 0; i < CrimeRs.size(); i++) {
					searchLocalCDTO cDTO = CrimeRs.get(i);
					String URL = "Images/" + cDTO.getPeopleNum() + ".jpg";
			%>
			<form method="post" action="/FID/mDetailInforServlet.do">
				<div class="pic">
					<button>
						<span style="display: inline-block;"><img src=<%=URL%> width="150px" height="200px" /></span>
					</button>
					<input type="hidden" name="num" value=<%=cDTO.getPeopleNum()%>>
					<%=cDTO.getName()%>
				</div>
			</form>
			<%
				}
			%>

		</fieldset>
		<br />
		<hr />
	</div>

	<div id="wantedT">
		<h1>수 배</h1>
		<fieldset>

			<%
				for (int i = 0; i < TraceRs.size(); i++) {
					searchLocalTDTO TDTO = TraceRs.get(i);
					String URL = "Images/" + TDTO.getPeopleNum() + ".jpg";
			%>
			<form method="post" action="/FID/mDetailInforServlet.do">
				<div class="pic">
					<button>
						<span style="display: inline-block;"><img src=<%=URL%> width="150px" height="200px" /></span>
					</button>
					<input type="hidden" name="num" value=<%=TDTO.getPeopleNum()%>>
					<%=TDTO.getName()%>
				</div>
			</form>
			<%
				}
			%>

		</fieldset>
		<br />
		<hr />
	</div>

</body>
</html>
