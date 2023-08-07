function bluring() {
	if(event.srcElement.tagName=="A" || event.srcElement.tagName=="IMG") {
		document.body.focus();
	}
}
document.onfocusin = bluring;

/*
 * null 체크
 */
function isEmpty(input) {
    if (input.value == null || input.value.replace(/ /gi,"") == "") {
        return true;
    }
    return false;
}

/* 주민등록번호 확인 */
function AppJuminChk(str) {

	var fCheck = document.getElementById("foreignerFlag");
	
	buf = new Array(13);
  for (i = 0; i < 13; i++)
  {
		buf[i] = parseInt(str.charAt(i));
	}
	multipliers = [2,3,4,5,6,7,8,9,2,3,4,5];
	if( buf[6] > 0 && buf[6] < 5 ) //내국인	
	{		
		for (i = 0, sum = 0; i < 12; i++) sum += (buf[i] *= multipliers[i]);
		var r1 = sum%11;		
		var temp = 11* ((sum-r1)/11) + 11 - sum;		
		var r2 = temp%10;		
		var temp1 = temp- 10*((temp-r2)/10);				
		if( temp1 != buf[12] )		
		{			
			if(fCheck.checked) {
				window.alert("정상적인 외국인등록번호가 아닙니다.");
			} else {
				window.alert("정상적인 주민등록번호가 아닙니다.");
			}
			return false;		
		}else	return true;		
	}	
	else //외국인	
	{	    
		odd = buf[7]*10 + buf[8]; 
    
		if (odd%2 != 0) {
			if(fCheck.checked) {
				window.alert("정상적인 외국인등록번호가 아닙니다.");
			} else {
				window.alert("정상적인 주민등록번호가 아닙니다.");
			}

			return false;
		}

		if ((buf[11] != 6)&&(buf[11] != 7)&&(buf[11] != 8)&&(buf[11] != 9)) 
		{
			if(fCheck.checked) {
				window.alert("정상적인 외국인등록번호가 아닙니다.");
			} else {
				window.alert("정상적인 주민등록번호가 아닙니다.");
			}
			return false;
		}
			
		for (i = 0, sum = 0; i < 12; i++) sum += (buf[i] *= multipliers[i]);

		sum=11-(sum%11);
    
		if (sum>=10) sum-=10;	sum += 2;

		if (sum>=10) sum-=10;

		if ( sum != buf[12]) {
			if(fCheck.checked) {
				window.alert("정상적인 외국인등록번호가 아닙니다.");
			} else {
				window.alert("정상적인 주민등록번호가 아닙니다.");
			}
		    return false;
		}
		else 
		{
		    return true;
		}
	}	 

	return true;
	
}

// 이메일 주소값을 체크합니다.
function emailCheck (emailStr) 
{
	var emailPat=/^(.+)@(.+)$/
	var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]"
	var validChars="\[^\\s" + specialChars + "\]"
	var quotedUser="(\"[^\"]*\")"
	var ipDomainPat=/^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/
	var atom=validChars + '+'
	var word="(" + atom + "|" + quotedUser + ")"
	var userPat=new RegExp("^" + word + "(\\." + word + ")*$")
	var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$")

	var matchArray=emailStr.match(emailPat)

	if (matchArray==null) {
	  /* Too many/few @'s or something; basically, this address doesn't
	     even fit the general mould of a valid e-mail address. */
	        alert("유효한 이메일 주소가 아닙니다")
	        return false
	}

	var user=matchArray[1]
	var domain=matchArray[2]

	/* if the e-mail address is at an IP address (as opposed to a symbolic
	   host name) make sure the IP address is valid. */
	var IPArray=domain.match(ipDomainPat)
	if (IPArray!=null) {
	    // this is an IP address
	          for (var i=1;i<=4;i++) {
	            if (IPArray[i]>255) {
	                alert("IP 주소가 틀렸습니다")
	                return false
	            }
	    }
	    return true
	}

	// Domain is symbolic name
	var domainArray=domain.match(domainPat)
	if (domainArray==null) {
	        alert("도메인명이 맞지 않습니다.")
	    return false
	}

	var atomPat=new RegExp(atom,"g")
	var domArr=domain.match(atomPat)
	var len=domArr.length
	if (domArr[domArr.length-1].length<2 || 
	    domArr[domArr.length-1].length>3) {
	   // the address must end in a two letter or three letter word.
	   alert("도메인명의 마지막 글자는 3글자거나 2자리의 국가코드 입니다.")
	   return false
	}

	// Make sure there's a host name preceding the domain.
	if (len<2) {
	   var errStr="이주소의 호스트명이 없습니다."
	   alert(errStr)
	   return false
	}

	return true;
}

/* 필드값이 숫자인지 확인 */
function IsNumber(inputname) {
	for(var i = 0; i < inputname.value.length; i++) {
		var chr = inputname.value.charAt(i,1);
		if(chr < '0' || chr > '9') {
			alert("숫자만 입력하실수 있습니다.");
			inputname.value = inputname.value.substring(0,i);
		}
	}
}

// 필드값이 숫자가 아닌지 확인
function NotIsNumber()
{
    str = event.keyCode;
    if (str >= 48 && str <= 57)
    {
        alert("숫자는 입력하실 수 없습니다.");
        event.returnValue = false;
    }
}

/* 필드값이 특수문자인지 확인 */
function IsSpecialKey()
{
	str = event.keyCode;
	
	if (SpecialKeyCode(str))
	{
		alert("특수키는 사용하실 수 없습니다!");
		event.returnValue = false;
	}
}

function EmptyKeyCode(str)
{
	switch (str)
	{
		case 32:
			return true;
			break;
	}
}

/* 주민번호 입력시 6자리가 입력되는 시점에 주민번호2번째 필드로 자동 focus */
function chgFocus(len, inObj, nextObj) {
	if( inObj.value.length == len) {
		nextObj.focus();
	}
}

//라디오 박스가 체크 되어 있는지 안되어 있는지
function checkRadio(objId) 
{
	var radioboxs = document.getElementsByName(objId);
	var returnVal = false;
	if(radioboxs!=null && radioboxs!="undefinded") {
		for(var i=0; i<radioboxs.length; i++){
			if(radioboxs[i].checked) {
				returnVal = true;
			}
		}
	}
	return returnVal;
}

//금액에 콤마 찍기
function commify(n) {
  var reg = /(^[+-]?\d+)(\d{3})/;   // 정규식
  n += '';                          // 숫자를 문자열로 변환

  while (reg.test(n))
    n = n.replace(reg, '$1' + ',' + '$2');

  return n;
}

//스크롤바 없는 일반팝업
function popwin(url,w,h) 
{
    window.open(url,'','width='+w+',height='+h+',scrollbars=no,resizable=no,toolbar=no,top=100,left=100');
}
//스크롤바 있는 일반팝업
function popwin_roll(url,w,h) 
{
    window.open(url,'','width='+w+',height='+h+',scrollbars=yes,resizable=no,toolbar=no,top=10,left=10');
}

//byte단위 글자수제한
function ChkByte(objname,maxlength) { 
	var objstr = objname.value; // 입력된 문자열을 담을 변수 
	var objstrlen = objstr.length; // 전체길이 
	
	// 변수초기화 
	var maxlen = maxlength; // 제한할 글자수 최대크기 
	var i = 0; // for문에 사용 
	var bytesize = 0; // 바이트크기 
	var strlen = 0; // 입력된 문자열의 크기
	var onechar = ""; // char단위로 추출시 필요한 변수 
	var objstr2 = ""; // 허용된 글자수까지만 포함한 최종문자열
	
	// 입력된 문자열의 총바이트수 구하기
	for(i=0; i< objstrlen; i++) { 
	// 한글자추출 
		onechar = objstr.charAt(i); 
	
		if (escape(onechar).length > 4) { 
			bytesize += 2;     // 한글이면 2를 더한다. 
		} else {  
			bytesize++;      // 그밗의 경우는 1을 더한다.
		} 
	
		if(bytesize <= maxlen)  {   // 전체 크기가 maxlen를 넘지않으면 
			strlen = i + 1;     // 1씩 증가
		}
	}
	
	// 총바이트수가 허용된 문자열의 최대값을 초과하면 
	if(bytesize > maxlen) { 
		alert( "제목에서 허용된 문자열의 최대값을 초과했습니다. \n초과된 내용은 자동으로 삭제 됩니다."); 
		objstr2 = objstr.substr(0, strlen); 
		objname.value = objstr2; 
	} 
	objname.focus(); 
} 


