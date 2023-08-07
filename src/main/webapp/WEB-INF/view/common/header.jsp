<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<%@ page import="com.lawAbiding.board.common.StringUtil" %>
<head>
	<title>준법지원시스템</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet"  type="text/css" href="/css/style.css" />
	<script src="/js/jquery-1.7.1.min.js"></script>
	<script src="/js/common.js" ></script>
	<script src="/js/commUtil.js" ></script>
	<script src="/js/cookie.js" ></script>
	<script src="/js/linkManager.js" ></script>
	<script src="/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
</head>
<body>
<%
         String headerSessionSabun =  StringUtil.null2void((String)session.getAttribute("sabun"));
         String headerSessionUserId =  StringUtil.null2void((String)session.getAttribute("user_id"));
         String headerSessionUserNm =  StringUtil.null2void((String)session.getAttribute("user_nm"));
         String headerSessionAuthType =  StringUtil.null2void((String)session.getAttribute("auth_type"));

System.out.println("***************************"+ headerSessionSabun);
System.out.println("***************************"+ headerSessionUserNm);
System.out.println("***************************"+ headerSessionAuthType);
         if(headerSessionSabun == null || headerSessionSabun.equals("")){
             StringUtil.ErrorAlert(response,"SSO 로그인 실패하였습니다. 그룹웨어를 통해 로그인하시기 바랍니다.");
             }
%>

</body>