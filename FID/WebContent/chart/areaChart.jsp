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
		$.get(url, {
			"sido" : sido
		}).done(function(data) {
			$('#chart7').empty();
			delete options.dataset.values;
			delete options.dataset.values;
			options.dataset.values = [];
			options.dataset.fields = fields;
			for (key in data) {
				options.dataset.values.push(data[key]);
			}
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
		<div id="chart8"><label style="font-size: 45px;margin-left: 50px;">범죄</label></div>
		<script>
			Nwagon.chart(options);
		</script>
		<div id="chart9"><label style="font-size: 45px;margin-left: 50px;">수배</label></div>
		<div id="chart10"><label style="font-size: 45px;margin-left: 50px;">실종</label></div>
	</article>
</body>
</html>