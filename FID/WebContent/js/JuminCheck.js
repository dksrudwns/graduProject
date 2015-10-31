function isValidJuminNo(jumin1, jumin2) {
  var btn = document.getElementById("submitBtn");
  var txt = document.getElementById("noCheck");
  var yy     = jumin1.substr(0,2);        // �⵵
  var mm     = jumin1.substr(2,2);       // ��
  var dd     = jumin1.substr(4,2);        // ��
  var genda  = jumin2.substr(0,1);        // ����
  var msg, ss, cc;
  // ���ڰ� �ƴ� ���� �Է��� ���
  if (!isNumeric(jumin1)) {
	  txt.innerText = "사용 불가";
	  txt.style.color = "red";
	  btn.disabled = true;
    return false;
  }
  
  // ���̰� 6�� �ƴ� ���
  if (jumin1.length != 6) {
	  txt.innerText = "사용 불가";
	  txt.style.color = "red";
	  btn.disabled = true;
    return false;
  }
  
  // ù��° �ڷῡ�� ������(YYMMDD) ���� �� �⺻ ���� �˻�
  if (yy < "00" 
      || yy > "99" 
      || mm < "01" 
      || mm > "12" 
      || dd < "01" 
      || dd > "31") {
	  txt.innerText = "사용 불가";
	  txt.style.color = "red";
	  btn.disabled = true;
    return false;
  }
  
  // ���ڰ� �ƴ� ���� �Է��� ���
  if (!isNumeric(jumin2)) {
	  txt.innerText = "사용 불가";
	  txt.style.color = "red";
	  btn.disabled = true;
    return false;
  }
  // ���̰� 7�� �ƴ� ���
  if (jumin2.length != 7) {
	  txt.innerText = "사용 불가";
	  txt.style.color = "red";
	  btn.disabled = true;
    return false;
  }
  
  // �����κ��� 1 ~ 4 �� �ƴ� ���
  if (genda < "1" || genda > "4") {
	  txt.innerText = "사용 불가";
	  txt.style.color = "red";
	  btn.disabled = true;
    return false;
  }
 
  // ���� ��� - 1 �Ǵ� 2: 1900���, 3 �Ǵ� 4: 2000���
  cc = (genda == "1" || genda == "2") ? "19" : "20";
  // ù��° �ڷῡ�� ������(YYMMDD) ���� �� ��¥ ���� �˻�
  if (isValidDate(cc+yy+mm+dd) == false) {
	  txt.innerText = "사용 불가";
	  txt.style.color = "red";
	  btn.disabled = true;
    return false;
  }
  
  // Check Digit �˻�
  if (!isSSN(jumin1, jumin2)) {
	  txt.innerText = "사용 불가";
	  txt.style.color = "red";
	  btn.disabled = true;
    return false;
  }
  txt.innerText = "유효";
  txt.style.color = "green";
  btn.disabled = false;
  document.getElementById("idNum1").readOnly = true;
  document.getElementById("idNum2").readOnly = true;
  return true;
}
function isValidDate(iDate) {
  if( iDate.length != 8 ) {
    return false;
  }
   
  oDate = new Date();
  oDate.setFullYear(iDate.substring(0, 4));
  oDate.setMonth(parseInt(iDate.substring(4, 6)) - 1);
  oDate.setDate(iDate.substring(6));
  if( oDate.getFullYear()     != iDate.substring(0, 4) 
      || oDate.getMonth()  != iDate.substring(4, 6) 
      || oDate.getDate()      != iDate.substring(6) ){
     
    return false;
  }
    
  return true;
}
 
function isNumeric(s) { 
  for (i=0; i<s.length; i++) { 
    c = s.substr(i, 1); 
    if (c < "0" || c > "9") return false; 
  } 
  return true; 
}
 
function isSSN(s1, s2) {
  n = 2;
  sum = 0;
  for (i=0; i<s1.length; i++)
    sum += parseInt(s1.substr(i, 1)) * n++;
  for (i=0; i<s2.length-1; i++) {
    sum += parseInt(s2.substr(i, 1)) * n++;
    if (n == 10) n = 2;
  }
  
  c = 11 - sum % 11;
  if (c == 11) c = 1;
  if (c == 10) c = 0;
  if (c != parseInt(s2.substr(6, 1))) return false;
  else return true;
}

