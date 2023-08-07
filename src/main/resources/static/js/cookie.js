function setCookie(name, value)    {
	 var argv = setCookie.arguments;
	 var argc = setCookie.arguments.length;
	 var expires = (2 < argc) ? argv[2] : null;
	 var path = (3 < argc) ? argv[3] : null;
	 var domain = (4 < argc) ? argv[4] : null;
	 var secure = (5 < argc) ? argv[5] : false;
	 
	 document.cookie = name + "=" + escape (value) +
	    ((expires == null) ? "" : ("; expires=" + expires)) + 
	    ((path == null) ? "" : ("; path=" + path)) +
	    ((domain == null) ? "" : ("; domain=" + domain)) +
	    ((secure == true) ? "; secure" : "");
}

 
function getCookie(name) {
 var arg = name + "=";
 var alen = arg.length;
 var clen = document.cookie.length; 
 var i = 0;
    while (i < clen)  {
              var j = i + alen;
              if (document.cookie.substring(i, j) == arg) return getCookieVal (j);
              i = document.cookie.indexOf(" ", i) + 1;
              if (i == 0) break; 
    }
    return null;
}


function deleteCookie(name) {  
 var exp = new Date();  
 var argv = deleteCookie.arguments;
 var argc = deleteCookie.arguments.length;
 
 var path = (1 < argc) ? argv[1] : null;
 var domain = (2 < argc) ? argv[2] : null;
 var secure = (3 < argc) ? argv[3] : false;
 

 exp.setTime (exp.getTime() - 1);  
 var cval = getCookie (name);  
       
 document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString()+
     ((path == null) ? "" : ("; path=" + path)) +
     ((domain == null) ? "" : ("; domain=" + domain)) +
     ((secure == true) ? "; secure" : "");
}


function getCookieVal(offset)    {
	 var endstr = document.cookie.indexOf (";", offset);
	    if (endstr == -1)
	        endstr = document.cookie.length;
	    return unescape(document.cookie.substring(offset, endstr));
}



