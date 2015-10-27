<%@page import="java.util.HashMap"%>
<%@page import="daoto.MissingDAO"%>
<%@page import="daoto.TraceDAO"%>
<%@page import="daoto.CrimeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	/*
		String result = request.getParameter("Local");
	
		String[] local = result.split(" ");
	
		String sido = null;
		String gugun = null;
	
		// 시 구 구분
		if (local[1].endsWith("시")) {
	
			sido = local[1].substring(0, 2);
			gugun = local[2];
	
		} else {
			sido = local[1].substring(0, 2);
			gugun = local[2] + " " + local[3];
	
		}
	
		CrimeDAO cDAO = new CrimeDAO();
		HashMap<String, Integer> cmap = cDAO.crimeDetailCountLocal(sido, gugun);
		int crime1 = cmap.get("count1");//살인
		int crime2 = cmap.get("count2");//절도
		int crime3 = cmap.get("count3");//폭행
		int crime4 = cmap.get("count4");//성폭행
	
		TraceDAO tDAO = new TraceDAO();
		HashMap<String, Integer> tmap = tDAO.traceDetailCountLocal(sido, gugun);
		int traceA = tmap.get("countA");//지명수배
		int traceB = tmap.get("countB");//벌금수배
		int traceC = tmap.get("countC");//지명통보
	
		MissingDAO mDAO = new MissingDAO();
		HashMap<String, Integer> mmap = mDAO.missingDetailCountLocal(sido, gugun);
		int missing1 = mmap.get("count1");//미아
		int missing2 = mmap.get("count2");//청소년가출
		int missing3 = mmap.get("count3");//실종
		int missing4 = mmap.get("count4");//노인실종
		*/

	int crime1 = (int) request.getAttribute("crime1");
	int crime2 = (int) request.getAttribute("crime2");
	int crime3 = (int) request.getAttribute("crime3");
	int crime4 = (int) request.getAttribute("crime4");

	int traceA = (int) request.getAttribute("traceA");
	int traceB = (int) request.getAttribute("traceB");
	int traceC = (int) request.getAttribute("traceC");

	int missing1 = (int) request.getAttribute("missing1");
	int missing2 = (int) request.getAttribute("missing2");
	int missing3 = (int) request.getAttribute("missing3");
	int missing4 = (int) request.getAttribute("missing4");
%>
<html>
<head>
<link rel='stylesheet' href='chart/Nwagon.css' type='text/css'>
<script src='chart/Nwagon.js'></script>
<meta charset="euc-kr">
</head>
<body>
	<article>
		<h2 style="font-size: 50px; margin: 5px 5px;">현재 지역 통계</h2>
		<div id="chart"></div>
		<script>
               var options = {
                  'dataset': {
                     title: 'region',
                     values:[<%=crime1%>,<%=crime2%>,<%=crime3%>,<%=crime4%>,<%=missing1%>,<%=missing2%>,<%=missing3%>,<%=missing4%>,<%=traceA%>,<%=traceB%>,<%=traceC%>],
                     colorset: ['#56b4e9', '#e69f00', '#cc79a7', '#009e73', '#0072b2','#901639','#0a91e8','#007700','#303030','#303020','#303010'],
                     fields: ['살인','절도','폭행','성폭행','미아','청소년가출','실종','노인실종','지명수배','벌금수배','지명통보'] 
                  },
                  'donut_width' : 100, 
                  'core_circle_radius':0,
                  'chartDiv': 'chart',
                  'chartType': 'pie',
                  'chartSize': {width:800, height:500}
               };
               Nwagon.chart(options);
            </script>
	</article>
</body>
</html>