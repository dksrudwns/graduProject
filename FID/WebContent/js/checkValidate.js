function ckv(str){	
	var ruler = /^[0-9]*$/;
	
	if(ruler.test(str.value) !=true){
		alert("숫자만 쓰세요");
		reset(str.id);
	}
}

function ckn(str){
	var ruler = /^[가-힣ㄱ-ㅎㅏ-ㅣ]*$/;
	
	if(ruler.test(str.value) != true){
		alert("한글만 쓰세요");
		reset(str.id);
	}
}

function reset(id){
	if(id=="idNum1"){
		document.getElementById("idNum1").value="";	
	}
	if(id=="idNum2"){
		document.getElementById("idNum2").value="";
	}
	if(id=="name"){
		document.getElementById("name").value="";
	}
}