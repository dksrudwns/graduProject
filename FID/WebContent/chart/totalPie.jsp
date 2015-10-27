<%@page import="daoto.MissingDAO"%>
<%@page import="daoto.TraceDAO"%>
<%@page import="daoto.CrimeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	CrimeDAO cDAO = new CrimeDAO();
	int crimeCount = cDAO.crimeCount();
	
	TraceDAO tDAO = new TraceDAO();
	int traceCount = tDAO.traceCount();
	
	MissingDAO mDAO =new MissingDAO();
	int missingCount = mDAO.MissingCount();
%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='../chart/Nwagon.css' type='text/css'>
<script src='../chart/Nwagon.js'></script>
<meta charset="euc-kr">
</head>
<body>
	<article>
		<h2 style="font-size: 50px; margin: 5px 5px;">종합통계</h2>
		<div id="chart"></div>
		<script>
			var options = {
				'dataset' : {
					title : 'Web accessibility status',
					values : [ <%=crimeCount%>, <%=missingCount%>, <%=traceCount%> ],
					colorset : [ '#56b4e9', '#e69f00', '#cc79a7' ],
					fields : [ '범죄', '실종', '수배' ]
				},
				'donut_width' : 100,
				'core_circle_radius' : 0,
				'chartDiv' : 'chart',
				'chartType' : 'pie',
				'chartSize' : {
					width : 700,
					height : 450
				}
			};

			Nwagon.chart(options);
		</script>
	</article>
</body>
</html>