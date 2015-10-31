<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='Nwagon.css' type='text/css'>
<script src='Nwagon.js'></script>
<script src="../js/jquery-2.1.4.min.js"></script>
<script>
	var options = {
		'dataset' : {
			title : 'Web accessibility status',
			values : [ 25, 3, 10, 7 ],
			colorset : [ '#2EB400', '#2BC8C9', "#666666", '#f09a93' ],
			fields : [ '살인', '절도', '폭행', '성폭행' ],
		},
		'donut_width' : 35,
		'core_circle_radius' : 50,
		'chartDiv' : 'chart8',
		'chartType' : 'donut',
		'chartSize' : {
			width : 600,
			height : 230
		}
	};

	$(function() {
		
		$.ajax({
			url : "../getSido.do",
			dataType : "json",
			success : function(data) {
				$(data).each(function(index, item) {
					$("#sido:last").append(
						"<option value="+item+">" + item + "</option>");
					});
				}
			});
		options.chartDiv = null;
		options.chartDiv = 'chart9'
			Nwagon.chart(options);
		options.chartDiv = null;
		options.chartDiv = 'chart10'
			Nwagon.chart(options);
		
		
		
		});
	
	function setChart() {
		var sido = $('#sido').val();
		$.get("../getAreaData.do", {
			"sido" : sido
		}).done(function(data) {
			$('#chart8').empty();
			delete options.dataset.values;
			delete options.dataset.fields;
			options.chartDiv = null;
			options.chartDiv = 'chart8'
			options.dataset.values = data.Crime;
			options.dataset.fields = [ '살인', '절도', '폭행', '성폭행' ];
			console.log(options.dataset.values);
			Nwagon.chart(options);
			
			$('#chart9').empty();
			delete options.dataset.values;
			delete options.dataset.fields;
			options.chartDiv = null;
			options.chartDiv = 'chart9'
			options.dataset.values = data.Trace;
			options.dataset.fields = [ '지명수배', '벌금수배', '지명통보'];
			console.log(options.dataset.values);
			Nwagon.chart(options);
			
			$('#chart10').empty();
			delete options.dataset.values;
			delete options.dataset.fields;
			options.chartDiv = null;
			options.chartDiv = 'chart10'
			options.dataset.values = data.Missing;
			options.dataset.fields = [ '미아', '청소년 가출', '실종', '노인 실종' ];
			console.log(options.dataset.values);
			Nwagon.chart(options);
	
		});
	}
</script>
<meta charset="utf-8">
</head>
<body >
	<div class="regionSelect">
		<h3 style="display: inline-block">시/도 선택</h3>
		<select id="sido" name="sido" onchange="setChart()">
			<option value="-" selected="selected">- 시/도 -</option>
		</select>
	</div>
	<article>
		<h2 style="font-size: 50px; margin: 5px 5px;">지역 통계</h2>
		<br>
		<label style="font-size: 45px;margin-left: 50px;">범죄</label>
		<div id="chart8"></div>
		<script>
			Nwagon.chart(options);
		</script>
		<label style="font-size: 45px;margin-left: 50px;">수배</label>
		<div id="chart9"></div>
		<label style="font-size: 45px;margin-left: 50px;">실종</label>
		<div id="chart10"></div>
	</article>
</body>
</html>