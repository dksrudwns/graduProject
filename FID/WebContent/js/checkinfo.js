function checkNum(str){   
   var ruler = /^[0-9]*$/;
   
   if(ruler.test(str.value) !=true){
      alert("Numbers Only");
      reset(str.id);
   }
}

function checkName(str){
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
   if(id=="pro_idNum1"){
      document.getElementById("pro_idNum1").value="";   
   }
   if(id=="pro_idNum2"){
      document.getElementById("pro_idNum2").value="";
   }
   if(id=="name"){
      document.getElementById("name").value="";
   }
}

function onlyNumber(txt){
   var key = event.keyCode;
   if(!(key==8||key==9||key==13||key==46||key==144||
      (key>=48&&key<=57)||key==110||key==190)){
      event.returnValue = false;
   }      
}