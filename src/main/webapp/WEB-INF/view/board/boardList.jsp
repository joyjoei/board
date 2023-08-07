<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<%@ page import="com.lawAbiding.board.common.ConstVars" %>
<%@ include file="../common/header.jsp" %>

<style>
.paging_css {
  display: flex;
  justify-content: center;
  padding: 18px 0px;
}

.paging_css a {
  color: black;
  padding: 5px 5px;
  font-size:13px;
}

.paging_css a.active {
  background-color: #4CAF50;
  color: white;
}

.paging_css a:hover:not(.active) {background-color: #ddd;}
</style>
<script>

function goSearch(gubun){
	var frm = document.actForm;
	var searchType  = document.getElementById("searchType");
	var searchValue  = document.getElementById("searchValue").value;
    var type = (searchType.options[searchType.selectedIndex].value);

    if(type == "title"){
        frm.title.value = searchValue;
    }else if(type == "contents"){
        frm.contents.value = searchValue;
    }

	frm.gubun.value = gubun;
	frm.action  ="/api/board/categoryList";
	frm.submit();
}

function goRegister(gubun, gubun_name){

	var frm = document.actForm;
	frm.gubun.value = gubun;
	frm.gubun_name.value = gubun_name;

	frm.action  ="/api/board/boardForm";
	frm.submit();
}

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
		<jsp:param name="leftNo" value="${gubun}" />
	</jsp:include>
</div>
<div id="content-wrap-sub">
	<div border="1"><a href="/api/board/mainList"><img src="/images/top_img3.jpg" width="970" alt="준법지원시스템" /></a></div><!-- visual image : 970 *130 -->
	<div class="lr-wrap clfix">
		<!--lnb start-->
		<div class="lnbArea">
			<jsp:include page="../common/left.jsp">
				<jsp:param name="leftNo" value= "${gubun}"/>
			</jsp:include>
		</div>

<!--contents start-->
<form name="actForm" method="post" action  ="/api/board/boardForm";>
<input type="hidden" name="seqno" />
<input type="hidden" name="gubun"/>
<input type="hidden" name="gubun_name"  />
<input type="hidden" name="title"  />
<input type="hidden" name="contents"  />
<div class="contentsArea">
    <p class="path" >
        HOME > ${gubun_name}
    </p>
            <table class="tbl-n" >
                <tr>
                    <td colspan="2">
                        <div class="TabDesign">

                            <ul>
                                <c:choose>
                                    <c:when test="${gubun == '01' or gubun == '02' or gubun == '03'}">
                                        <li><a href="/api/board/mainList"><span><strong>최신게시</strong></font></span></a></li>
                                        <li <c:if test="${gubun == '01'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=01"><span><strong>준법이슈</strong></font></span></a></li>
                                        <li <c:if test="${gubun == '02'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=02"><span><strong>법령정보</strong></font></span></a></li>
                                        <li <c:if test="${gubun == '03'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=03"><span><strong>법률검토</strong></font></span></a></li>
                                    </c:when>
                                    <c:when test="${gubun == '04' or gubun == '05'}">
                                        <li <c:if test="${gubun == '04'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=04"><span><strong>계약서</strong></font></span></a></li>
                                        <li <c:if test="${gubun == '05'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=05"><span><strong>각종서식</strong></font></span></a></li>
                                    </c:when>
                                    <c:when test="${gubun == '06' or gubun == '07'}">
                                        <li <c:if test="${gubun == '06'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=06"><span><strong>자율준수편람</strong></font></span></a></li>
                                        <li <c:if test="${gubun == '07'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=07"><span><strong>공정거래이슈</strong></font></span></a></li>
                                    </c:when>
                                    <c:when test="${gubun == '08' or gubun == '09' or gubun == '10' or gubun == '11' or gubun == '15'}">
                                        <li <c:if test="${gubun == '08'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=08"><span><strong>정관</strong></font></span></a></li>
                                        <li <c:if test="${gubun == '09'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=09"><span><strong>윤리경영</strong></font></span></a></li>
                                        <li <c:if test="${gubun == '10'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=10"><span><strong>내부감사</strong></font></span></a></li>
                                        <li <c:if test="${gubun == '11'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=11"><span><strong>내부신고</strong></font></span></a></li>
                                        <li <c:if test="${gubun == '15'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=15"><span><strong>기타 업무 관련 규정</strong></font></span></a></li>
                                    </c:when>
                                    <c:when test="${gubun == '12' or gubun == '13' or gubun == '14'}">
                                        <li <c:if test="${gubun == '12'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=12"><span><strong>이사회-감사위원회</strong></font></span></a></li>
                                        <li <c:if test="${gubun == '13'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=13"><span><strong>내부회계관리</strong></font></span></a></li>
                                        <li <c:if test="${gubun == '14'}"> class='selected' </c:if> ><a href="/api/board/categoryList?gubun=14"><span><strong>기타관련자료</strong></font></span></a></li>
                                    </c:when>
                                </c:choose>
							</ul>
						</div>
					</td>
				</tr>
				<tr height="25px;"><td></td></tr>
				<tr>
					<td width="50%"><span style="font-size:14px; color:#009E82; text-align:left; padding:0px 0px 0px 8px;"><strong>${gubun_name}</strong></span></td>
					<td width="50%">
                        <div class="fr al">
                            <select id="searchType">
                                <option name="searchType" value="title">제목</option>
                                <option name="searchType" value="contents">내용</option>
                            </select>
                            <input type="text" id="searchValue" name="searchValue" class="ipt" style="width:110px" />

                            <input type="button" id="button2" value="검색" onclick="javascript:goSearch('${gubun}');">
                            <%
                                if(headerSessionAuthType.equals("A") || headerSessionAuthType.equals("W")){
                            %>
                            <input type="button" id="button2" value="등록" onclick="javascript:goRegister('${gubun}', '${gubun_name}');">
                            <%
                                }
                            %>
                        </div>
                    </td>
				</tr>
			</table>
			<div class="cont-pd">
				<table class="tbl-x2">
					<colgroup>
						<col width="70" />
						<col width="*" />
						<col width="110" />
						<col width="70" />
						<col width="70" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>등록일</th>
							<th>작성자</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${empty boardList.content}">
					    <tr>
					        <td colspan=5>등록된 글이 없습니다.
					        </td>
					    </tr>
					</c:if>
					    <c:forEach items="${boardList.content}" var="post">
                            <tr>
                                <td>${post.getSeqno()}</td>
                                <td class="l-txt">
                                    <a title= ${post.getTitle()} href="javascript:goDetailView('${post.getSeqno()}','${post.getGubun()}','${post.getGubun_name()}')">
                                        ${post.getTitle()}
                                    </a>
                                </td>
                                <td>${post.getIns_date()}</td>
                                <td>${post.getWriter()}</td>
                                <td>${post.getView_cnt()}</td>
                            </tr>
                        </c:forEach>

					</tbody>
				</table>

				<!-- 페이징 영역 시작 -->
                	<div class="paging_css">
                			<!-- 이전 -->
                			<c:choose>
                				<c:when test="${boardList.first}"></c:when>
                				<c:otherwise>
                					<a href="/api/board/categoryList/?gubun=${gubun}&page=0">처음</a>
                					<a href="/api/board/categoryList/?gubun=${gubun}&page=${boardList.number-1}">&laquo;</a>
                				</c:otherwise>
                			</c:choose>

                			<!-- 페이지 그룹 -->
                			<c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                				<c:choose>
                					<c:when test="${boardList.pageable.pageNumber+1 == i}">
                						<a class="active" href="/api/board/categoryList/?gubun=${gubun}&page=${i-1}">${i}</a>
                					</c:when>
                					<c:otherwise>
                						<a href="/api/board/categoryList/?gubun=${gubun}&page=${i-1}">${i}</a>
                					</c:otherwise>
                				</c:choose>
                			</c:forEach>

                			<!-- 다음 -->
                			<c:choose>
                				<c:when test="${boardList.last}"></c:when>
                				<c:otherwise>
                					<a href="/api/board/categoryList/?gubun=${gubun}&page=${boardList.number+1}">&raquo;</a>
                					<a href="/api/board/categoryList/?gubun=${gubun}&page=${boardList.totalPages-1}">마지막</a>
                				</c:otherwise>
                			</c:choose>
                		</ul>
                	</div>
                	<!-- 페이징 영역 끝 -->
			</div>

		</form>
		<!--contents end-->
	</div>
</div><!-- content-wrap end -->