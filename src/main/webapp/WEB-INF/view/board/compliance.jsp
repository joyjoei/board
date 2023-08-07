<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<%@ page import="com.lawAbiding.board.common.ConstVars" %>
<%@ include file="../common/header.jsp" %>
<script>
function goModify(seqno){
	var frm = document.actForm;
	frm.seqno.value = seqno;
	frm.action  = "/api/board/complianceModify";
	frm.submit();
}
</script>

<!-- head-wrapper start -->

<jsp:useBean id="StringUtil" class="com.lawAbiding.board.common.StringUtil"/>
<div id="head-wrapper">
	<jsp:include page="../common/top.jsp">
    		<jsp:param name="leftNo" value="17" />
    	</jsp:include>
</div>
<!-- head-wrapper end -->
<!-- content-wrap start -->
<div id="content-wrap-sub">	
	<div border="1"><a href="/api/board/mainList"><img src="/images/top_img3.jpg" width="970" alt="준법지원시스템" /></a></div><!-- visual image : 970 *130 -->
    <div class="lr-wrap clfix">
        <!--lnb start-->
        <div class="lnbArea">
            <jsp:include page="../common/left.jsp">
                <jsp:param name="leftNo" value="17"/>
            </jsp:include>
        </div>
		<!--lnb area end--> 
		
		<!--contents start-->
		<form name="actForm" method="post">
		<input type="hidden" name="seqno" />
		<div class="contentsArea">
			<p class="path">HOME > 시스템관리 > 준법통제기준</p>
			<table class="tbl-n" >
			    <tr>
                    <td colspan="2">
                        <div class="TabDesign">
                            <ul>
                                <li><a href="/api/board/mainList"><span><strong>최신게시</strong></font></span></a></li>
                                <li><a href="/api/board/categoryList?gubun=01"><span><strong>준법이슈</strong></font></span></a></li>
                                <li><a href="/api/board/categoryList?gubun=02"><span><strong>법령정보</strong></font></span></a></li>
                                <li><a href="/api/board/categoryList?gubun=03"><span><strong>법률검토</strong></font></span></a></li>
                                <li><a href="/api/board/main"><span><strong>준법지원제도</strong></font></span></a></li>
                                <li><a href="/api/board/main2"><span><strong>준법경영 선언문</strong></font></span></a></li>
                                <li class="selected"><a href="/api/board/complianceView"><span><strong>준법통제기준</strong></font></span></a></li>
                            </ul>
                        </div>
                    </td>
                </tr>

			</table>
			<div class="cont-pd">
				<div class="ar mt10">

	 				<c:set var="sessionAuth"  value='<%=headerSessionAuthType%>' />
                    <c:choose>
                        <c:when  test="${sessionAuth == 'A'}" >
                             <input type="button" value="수정" id="button1" onclick="javascript:goModify('${result.seqno}');" />
                        </c:when>
                    </c:choose>

				</div> 
				<table class="tbl-bbs-view mt20">

					<tbody>
						<tr>
							<td colspan="6" class="story">
							    <c:set var="content2" value="${result.contents}" />
                                ${StringUtil.htmlConvert( StringUtil.null2void(content2))}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		</form>
		<!--contents end-->
	</div>
</div><!-- content-wrap end -->