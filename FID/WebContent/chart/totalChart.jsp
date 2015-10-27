<%@page import="java.util.HashMap"%>
<%@page import="daoto.MissingDAO"%>
<%@page import="daoto.TraceDAO"%>
<%@page import="daoto.CrimeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	CrimeDAO cDAO = new CrimeDAO();
	HashMap<String, Integer> cmap = cDAO.crimeDetailCount();
	int crime1 =(int) cmap.get("count1");//살인
	int crime2 =(int) cmap.get("count2");//절도
	int crime3 =(int) cmap.get("count3");//폭행
	int crime4 = (int)cmap.get("count4");//성폭행
	System.out.println(crime4);
	
	TraceDAO tDAO = new TraceDAO();
	HashMap<String, Integer> tmap = tDAO.traceDetailCount();
	int traceA =(int) tmap.get("countA");//지명수배
	int traceB =(int) tmap.get("countB");//벌금수배
	int traceC =(int) tmap.get("countC");//지명통보

	MissingDAO mDAO = new MissingDAO();
	HashMap<String, Integer> mmap = mDAO.missingDetailCount();
	int missing1 =(int) mmap.get("count1");//미아
	int missing2 =(int) mmap.get("count2");//청소년가출
	int missing3 =(int) mmap.get("count3");//실종
	int missing4 =(int)	 mmap.get("count4");//노인실종
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
		<div id="chart8"></div>
		<script>
			var options = {
				'legend' : {
					names : [ '살인', '절도', '폭행', '성폭행', '미아', '청소년 가출', '실종',
							'노인 실종', '지명 수배', '벌금 수배', '지명 통보' ],//요소이름
					hrefs : []
				//링크
				},
				'dataset' : {
					title : 'Total',
					values : [<%=crime1%>,<%=crime2%>,<%=crime3%>,<%=crime4%>,<%=missing1%>,<%=missing2%>,<%=missing3%>,<%=missing4%>,<%=traceA%>,<%=traceB%>,<%=traceC%>],//요소의 값
					colorset : [ '#FF8C00' ],//요소 색상
					fields : [ '' ]
				//어떤그래프인가?
				},
				'chartDiv' : 'chart8', //차트를 그릴 DIV 요소의 ID
				'chartType' : 'column', //차트종류
				'chartSize' : {
					width : 725,
					height : 350
				},//그래프사이즈
				'maxValue' : 10,//요소의 최대값
				'increment' : 1//증가폭
			};
			Nwagon.chart(options);
		</script>
	</article>
</body>
</html>