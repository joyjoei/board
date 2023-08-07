<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<%@ page import="com.lawAbiding.board.common.ConstVars" %>
<%@ include file="../common/header.jsp" %>

<script>
function goDetailView(seqno, gubun, gubun_name){
	var frm = document.actForm;
	frm.seqno.value = seqno;
	frm.gubun.value = gubun;
	frm.gubun_name.value = gubun_name;
	frm.action  = "/api/board/view/" + seqno;
	frm.submit();
}
</script>
<jsp:useBean id="StringUtil" class="com.lawAbiding.board.common.StringUtil"/>
<!-- head-wrapper start -->
<div id="head-wrapper">
	<jsp:include page="../common/top.jsp">
		<jsp:param name="leftNo" value="02" />
	</jsp:include>
</div>
<div id="content-wrap-sub">
	<div border="1"><a href="/api/board/mainList"><img src="/images/top_img3.jpg" width="970" alt="준법지원시스템" /></a></div><!-- visual image : 970 *130 -->
	<div class="lr-wrap clfix">
		<!--lnb start-->
		<div class="lnbArea">
			<jsp:include page="../common/left.jsp">
				<jsp:param name="leftNo" value="99"/>
			</jsp:include>
		</div>

<!--contents start-->
<form name="actForm" method="post">
<input type="hidden" name="seqno" />
<input type="hidden" name="gubun" />
<input type="hidden" name="gubun_name" />
<div class="contentsArea">
    <p class="path" >
        HOME > 통합게시판
    </p>
            <table class="tbl-n" >
				<tr>
					<td colspan="2">
						<div class="TabDesign">
							<ul>
								<li class="selected"><a href="/api/board/mainList"><span><strong>최신게시</strong></font></span></a></li>
		                        <li><a href="/api/board/categoryList?gubun=01"><span><strong>준법이슈</strong></font></span></a></li>
								<li><a href="/api/board/categoryList?gubun=02"><span><strong>법령정보</strong></font></span></a></li>
								<li><a href="/api/board/categoryList?gubun=03"><span><strong>법률검토</strong></font></span></a></li>
								<li><a href="/api/board/main"><span><strong>준법지원제도</strong></font></span></a></li>
								<li><a href="/api/board/main2"><span><strong>준법경영 선언문</strong></font></span></a></li>
								<li><a href="/api/board/complianceView"><span><strong>준법통제기준</strong></font></span></a></li>
							</ul>
						</div>
					</td>
				</tr>
				<tr height="25px;"><td></td></tr>
				<tr>
					<td width="50%"><span style="font-size:14px; color:#009E82; text-align:left; padding:0px 0px 0px 8px;"><strong>통합게시판</strong></span></td>
					<td width="50%"></td>
				</tr>
			</table>
			<div class="cont-pd">
				<table class="tbl-x2">
					<colgroup>
						<col width="120" />
                        <col width="*" />
                        <col width="110" />
                        <col width="70" />
                        <col width="70" />
					</colgroup>
					<thead>
						<tr>
                            <th>게시판</th>
                            <th>제목</th>
                            <th>등록일</th>
                            <th>작성자</th>
                            <th>조회수</th>
                        </tr>
					</thead>
					<tbody>
					    <c:forEach items="${boardList}" var="post">
                            <tr>
                             <td>${post.getGubun_name()}</td>
                              <td class="l-txt">
                                <a title= ${post.getTitle()} href="javascript:goDetailView('${post.getSeqno()}','${post.getGubun()}','${post.getGubun_name()}')">
                                        ${post.getTitle()}
                                </a>
                              </td>
                                <td>${post.getInsDateFormat()}</th>
                                <td>${post.getWriter()}</th>
                                <td>${post.getView_cnt()}</th>
                            </tr>
                        </c:forEach>
					</tbody>
				</table>
			</div>
		</form>
		<!--contents end-->
	</div>
</div><!-- content-wrap end -->