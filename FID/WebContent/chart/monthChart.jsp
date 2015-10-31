<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='Nwagon.css' type='text/css'>
<script src='Nwagon.js'></script>
<meta charset="euc-kr">
<script src="../js/jquery-2.1.4.min.js"></script>
<script>
	var c;

	var options = {
		'legend' : {
			names : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월',
					'10월', '11월', '12월' ]
		},
		'dataset' : {
			title : 'Crime Amount per month',
			values : [],
			colorset : [ '#DC143C', '#FF8C00', '#2EB400', '#4EB3C0' ],
			fields : []
		},
		'chartDiv' : 'chart7',
		'chartType' : 'line',
		'leftOffsetValue' : 50,
		'bottomOffsetValue' : 60,
		'chartSize' : {
			width : 700,
			height : 300
		},
		'minValue' : 0,
		'maxValue' : 30,
		'increment' : 5
	};

	function getdata() {
		var type = $('#type').val();
		var year = $('#year').val();

		if (type === 'crime') {
			var fields = [ '살인', '절도', '폭행', '성폭행' ];
			var url = "../yearCrime.do";
			sendToServer(url,year,fields);
		}
		else if(type === 'missing'){
			var fields = [ '미아', '청소년 가출', '실종', '노인 실종' ];
			var url = "../yearMissing.do";
			sendToServer(url,year,fields);
		}
		else{
			var fields = [ '지명수배', '벌금수배', '지명통보'];
			var url = "../yearTrace.do";
			sendToServer(url,year,fields);
		}

	}
	function sendToServer(url,year,fields) {
		$.get(url, {
			"year" : year
		}).done(function(data) {
			$('#chart7').empty();
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
</head>
<body>
	<div class="regionSelect">
		<h3 style="display: lock">세부 선택</h3>

		<label for="type">종류</label> <select id="type" name="type">
			<option value="-" selected="selected">- 선택 -</option>
			<option value="crime">범죄</option>
			<option value="missing">실종</option>
			<option value="trace">수배</option>
		</select> 
		<label for="year">년도</label> <select id="year" name="year">
			<option value="-" selected="selected">- 선택 -</option>
			<option value="2015">2015</option>
			<option value="2014">2014</option>
			<option value="2013">2013</option>
			<option value="2012">2012</option>
			<option value="2011">2011</option>
		</select> <input type="submit" value="조회" onclick="getdata();">
	</div>
	<article>
		<h2 style="font-size: 50px; margin: 5px 5px;">월별 통계</h2>
		<div id="chart7"></div>
		<script>
			Nwagon.chart(options);
		</script>
	</article>
</body>
</html>