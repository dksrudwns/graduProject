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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>대상 세부 정보</title>
</head>
<body>
	<%
		String URL = "Images/" + request.getAttribute("peopleNum") + ".jpg";
		String peopleNum = (String) request.getAttribute("peopleNum");
		String name = (String) request.getAttribute("name");
		String zipcode = (String) request.getAttribute("zipcode");
		String address = (String) request.getAttribute("address");
		Vector<MissingDTO> vMDTO = (Vector<MissingDTO>) request.getAttribute("MissingArea");
		Vector<CrimeDTO> vCDTO = (Vector<CrimeDTO>) request.getAttribute("CrimeArea");
		Vector<TraceDTO> vTDTO = (Vector<TraceDTO>) request.getAttribute("TraceArea");
		AddressCodeDAO aDAO = new AddressCodeDAO();
		AddressCodeDTO aDTO;
		String Crimeadd = null;
		String MissingAdd = null;
		String FindAdd = null;
		String pro_name = (String) request.getAttribute("pro_name");
		String pro_phon = (String) request.getAttribute("pro_phon");
	%>
	<fieldset>
		<legend>대상자 인적 정보</legend>
		<table>
			<tr>
				<td>
					<div>
						<img src=<%=URL%> width="120" height="160" />
					</div>
				</td>
				<td>
					<div>
						<table>
							<tr>
								<td>이 름 : <%=name%></td>

							</tr>
							<tr>
								<td>주민번호 : <%=peopleNum%></td>

							</tr>
							<tr>
								<td>주 소 : <%=zipcode + " " + address%></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</fieldset>

	<fieldset id="crime">
		<legend>범죄</legend>
		<table border="1">
			<tr style="background-color: cyan">
				<th>종류</th>
				<th>범죄 지역</th>
				<th>범죄 발생일</th>
			</tr>
			<%
				if (vCDTO.size() == 0) {
			%>
			<tr>
			</tr>
			<%
				} else {
					for (int i = 0; i < vCDTO.size(); i++) {
						CrimeDTO cDTO = vCDTO.get(i);
						aDTO = aDAO.selectAddressCodeDAO(cDTO.getCrimeArea());
						Crimeadd = aDTO.getSido() + " " + aDTO.getGugun() + " " + aDTO.getDetail();
			%>
			<tr>
				<td><%=cDTO.getCrimeCode()%></td>
				<td><%=Crimeadd%></td>
				<td><%=cDTO.getCrimeDate()%></td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</fieldset>

	<fieldset id="trace">
		<legend>수배</legend>
		<table border="1">
			<tr style="background-color: cyan">
				<th>종류</th>
				<th>수배금</th>
				<th>수배상태</th>
				<th>수배등록일</th>
			</tr>
			<%
				if (vTDTO.size() == 0) {
			%>
			<tr>
			</tr>
			<%
				} else {
					for (int i = 0; i < vTDTO.size(); i++) {
						TraceDTO tDTO = vTDTO.get(i);
			%>
			<tr>
				<td><%=tDTO.getTraceCode()%></td>
				<td><%=tDTO.getT_Money()%></td>
				<td><%=tDTO.getTraceCondition()%></td>
				<td><%=tDTO.getTraceDate()%></td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</fieldset>

	<fieldset id="missing">
		<legend>실종</legend>
		<table border="1">
			<tr style="background-color: cyan">
				<th>종류</th>
				<th>실종 지역</th>
				<th>실종 등록일</th>
				<th>발견 지역</th>
				<th>발견 등록일</th>
			</tr>
			<%
				if (vMDTO.size() == 0) {
			%>
			<tr>
			</tr>
			<%
				} else {
					MissingpeDAO mpDAO = new MissingpeDAO();
					MissingpeDTO mpDTO = mpDAO.selectMissingpeInfo(peopleNum);
					PeopleInDAO peDAO = new PeopleInDAO();
					PeopleInDTO peDTO = peDAO.selectPeopelIn(mpDTO.getProtecterCode());

					for (int i = 0; i < vMDTO.size(); i++) {
						MissingDTO mDTO = vMDTO.get(i);
						aDTO = aDAO.selectAddressCodeDAO(mDTO.getMissingAddress());
						MissingAdd = aDTO.getSido() + " " + aDTO.getGugun() + " " + aDTO.getDetail();
						aDTO = aDAO.selectAddressCodeDAO(mDTO.getFindAddress());
						if (aDTO == null) {
							FindAdd = "null";
						} else {
							FindAdd = aDTO.getSido() + " " + aDTO.getGugun() + " " + aDTO.getDetail();
						}
			%>
			<tr>
				<td><%=mDTO.getMissingCode()%><%=i%></td>
				<td><%=MissingAdd%></td>
				<td><%=mDTO.getMissingDate()%></td>
				<td><%=FindAdd%></td>
				<td><%=mDTO.getFindDate()%></td>
			</tr>
			<%
				}
			%>
			보호자 이름 :<%=peDTO.getName() %><br>
			보호자 전화번호 : <a data-rel="external" href="tel:<%=mpDTO.getProtecterPh() %>" target="_blank"><%=mpDTO.getProtecterPh() %></a>
			<%
				}
			%>
		</table>
	</fieldset>
</body>
</html>
