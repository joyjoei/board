<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../common/header.jsp" %>
<%@ page import="com.lawAbiding.board.common.StringUtil" %>
<%
      String leftNo = request.getParameter("leftNo");
%>
<script>
function menuLink(menuNum) {
	if(menuNum=="16"){
		document.location.href = "/api/board/covenantView";
	} else if(menuNum=="17"){
		document.location.href = "/api/board/complianceView";
	}else{
		document.location.href = "/api/board/categoryList?gubun="+menuNum;
	}
}


</script>
 <c:set var="leftNo"  value='<%=leftNo%>' />

<h2>○ 종합법률정보</h2>
<ul>
	<li <c:if test="${leftNo == '01'}"> class='on' </c:if>> <a href="javascript:menuLink('01')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 준법 이슈</a></li>
	<li <c:if test="${leftNo == '02'}"> class='on' </c:if>> <a href="javascript:menuLink('02')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 법령 정보</a></li>
	<li <c:if test="${leftNo == '03'}"> class='on' </c:if>> <a href="javascript:menuLink('03')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 법률 검토</a></li>
</ul>

<h2>○ 법률서식</h2>
<ul>
	<li <c:if test="${leftNo == '04'}"> class='on' </c:if>> <a href="javascript:menuLink('04')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 계약서</a></li>
	<li <c:if test="${leftNo == '05'}"> class='on' </c:if>> <a href="javascript:menuLink('05')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 각종 서식</a></li>
</ul>
<h2>○ 공정거래자율준수</h2>
<ul>
	<li <c:if test="${leftNo == '06'}"> class='on' </c:if>> <a href="javascript:menuLink('06')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 자율준수 편람</a></li>
	<li <c:if test="${leftNo == '07'}"> class='on' </c:if>> <a href="javascript:menuLink('07')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 공정거래 이슈</a></li>
</ul>

<!--  				************		수정 			************					-->

<h2>○ 준법감사-규정</h2>
<ul>
	<li <c:if test="${leftNo == '08'}"> class='on' </c:if>> <a href="javascript:menuLink('08')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 정관</a></li>
	<li <c:if test="${leftNo == '09'}"> class='on' </c:if>> <a href="javascript:menuLink('09')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 윤리경영</a></li>
	<li <c:if test="${leftNo == '10'}"> class='on' </c:if>> <a href="javascript:menuLink('10')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 내부감사</a></li>
	<li <c:if test="${leftNo == '11'}"> class='on' </c:if>> <a href="javascript:menuLink('11')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 내부신고</a></li>
	<!-- 210701 기타업무관련규정 게시판 추가  -->
	<li <c:if test="${leftNo == '15'}"> class='on' </c:if>> <a href="javascript:menuLink('15')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 기타 업무 관련 규정</a></li>
	
</ul>
<h2>○ 내부회계관리제도-규정</h2>
<ul>
	<li <c:if test="${leftNo == '12'}"> class='on' </c:if>> <a href="javascript:menuLink('12')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 이사회-감사위원회</a></li>
	<li <c:if test="${leftNo == '13'}"> class='on' </c:if>> <a href="javascript:menuLink('13')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 내부회계관리</a></li>
	<li <c:if test="${leftNo == '14'}"> class='on' </c:if>> <a href="javascript:menuLink('14')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 기타관련자료</a></li>
</ul>


<%
    if(headerSessionAuthType.equals("A") || headerSessionAuthType.equals("C")){
%>
<h2>○ 준법지원 활동</h2>
<ul>
	<li <c:if test="${leftNo == '16'}"> class='on' </c:if>> <a href="javascript:menuLink('16')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 준법서약서 관리</a></li>
</ul>
<%
	}
	if(headerSessionAuthType.equals("A")){
%>
<h2>○ 시스템관리</h2>
<ul>
	<li <c:if test="${leftNo == '17'}"> class='on' </c:if>> <a href="javascript:menuLink('17')"><img src="/images/left_smenu_ico.gif" alt="" style="vertical-align: middle;" /> 준법통제기준 관리</a></li>
</ul>
<%
	}
%>

