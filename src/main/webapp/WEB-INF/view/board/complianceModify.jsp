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
	
	oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
	
	if(document.getElementById("contents").value==""){
		alert("내용을 입력해주세요");
		return false;
	}else{
		frm.contents.value = document.getElementById("contents").value;
	}
	
	frm.action = "/api/board/complianceModify/"+seqno+"/edit";
	frm.submit();
}
	

function goViewBack(seqno){
	var frm = document.actForm;
	frm.seqno.value = seqno;
	document.location.href = "/api/board/complianceView";
}
</script>

<!-- head-wrapper start -->

<jsp:useBean id="StringUtil" class="com.lawAbiding.board.common.StringUtil"/>
<div id="head-wrapper">
	<jsp:include page="../common/top.jsp">
    		<jsp:param name="leftNo" value="17" />
    	</jsp:include>
</div>

<!-- content-wrap start -->
<div id="content-wrap-sub">
	<div border="1"><a href="/api/board/mainList"><img src="/images/top_img3.jpg" width="970" alt="준법지원시스템" /></a></div><!-- visual image : 970 *130 -->
    <div class="lr-wrap clfix">
        <!--lnb start-->
        <div class="lnbArea">
            <jsp:include page="../common/left.jsp">
				<jsp:param name="subNo" value="17" />
			</jsp:include>	
		</div>
		<!--lnb area end--> 
		
		<!--contents start-->
		<c:set var="sessionAuth"  value='<%=headerSessionAuthType%>' />
        <c:set var="sessionSabun"  value='<%=headerSessionSabun%>' />
        <c:set var="sessionUserNm"  value='<%=headerSessionUserNm%>' />
        <c:set var="sessionUserId"  value='<%=headerSessionUserId%>' />

		<form name="actForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="seqno" />
		<input type="hidden" name="modifier" value="${sessionUserNm}"/>
		<input type="hidden" name="mod_user_id" value="${sessionUserId}" />
		<input type="hidden" name="mod_sabun" value="${sessionSabun}" />
		<div class="contentsArea">
			<p class="path">HOME > 시스템관리 > 준법통제기준 관리</p>
			<table class="tbl-n" >
				<tr height="23">
					<td><span style="font-size:14px; color:#009E82; text-align:left; padding:0px 0px 0px 8px;"><strong>준법통제기준 관리</strong></span></td>
				</tr>
			</table>
			<div class="cont-pd">
				<table class="tbl-x02 mt20">
					<colgroup>
						<col width="15%" />
						<col width="*" />
						<col width="15%" />
						<col width="45%" />
					</colgroup>
					<tbody>
						<tr>
							<th class="p-gray brnone at"><span class="p-blue n">*</span> 내용</th>
							<td colspan="3">
								<textarea name="contents" id="contents" style="height:350px; width:500px;">${result.contents} </textarea>
                                <script type="text/javascript">
                                    var oEditors = [];
                                    nhn.husky.EZCreator.createInIFrame({
                                        oAppRef: oEditors,
                                        elPlaceHolder: "contents",
                                        sSkinURI: "/editor/SmartEditor2Skin.html",
                                        fCreator: "createSEditor2"
                                    });
                                </script>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="ac mt20">
					<c:set var="sessionAuth"  value='<%=headerSessionAuthType%>' />
                    <c:set var="sessionSabun"  value='<%=headerSessionSabun%>' />
                    <c:choose>
                        <c:when  test="${sessionAuth == 'A'}" >
                             <input type="button" value="수정" id="button1" onclick="javascript:goModify('${result.seqno}');" />
                        </c:when>
                    </c:choose>
                    <input type="button" value="취소" id="button1" onclick="javascript:goViewBack(${result.seqno});" />
				</div>
			</div>
		</div>
		</form>
		<!--contents end-->
	</div>
</div><!-- content-wrap end -->
