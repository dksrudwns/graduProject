<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
     <meta charset="utf-8">
     <link rel="stylesheet" type="text/css" href="style.css">
        
  	<link rel='stylesheet' href='Nwagon.css' type='text/css'>
	<script src='Nwagon.js'></script>
</head>
<body>
    <header>
        <h1><a href="statistic.jsp">통계</a></h1>
    </header>
    <nav>
        <ol>
        <li><a href="national.jsp">통계</a></li>
        <li><a href="local.jsp">통계</a></li>
        <li><a href="data.jsp">통계</a></li>              
        </ol>
    </nav>
    
    <h2>통계</h2>
	<div id="Nwagon"></div>
	<script>
	    var options = {
	        'legend':{
	            names: [['A-a'], ['B-a', 'B-b', 'B-c'], ['C-a',  'C-b', 'C-c', 'C-d']]
	        },
	        'dataset': {
	            title: 'Web accessibility status',
	            values: [[60], [50, 100, 67], [90, 70, 44, 88]],
	            colorset: ['#2EB400', '#2BC8C9', "#666666"],
	            fields: ['A', 'B', 'C'],
	            opacity:[0.3, 0.5, 0.7, 0.9]
	        },
	        'core_circle_value' : ['72.7%'],
	        'core_circle_radius':30,
	        'maxValue' : 100,
	        'increment' : 20,
	        'chartDiv': 'Nwagon',
	        'chartType': 'polar',
	        'chartSize': {width:700, height:400}
	    };
	    Nwagon.chart(options);
	</script>
</body>
</html>