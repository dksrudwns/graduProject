function ckv(str){	
	var ruler = /^[0-9]*$/;
	
	if(ruler.test(str.value) !=true){
		alert("���ڸ� ������");
		reset(str.id);
	}
}

function ckn(str){
	var ruler = /^[��-�R��-����-��]*$/;
	
	if(ruler.test(str.value) != true){
		alert("�ѱ۸� ������");
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