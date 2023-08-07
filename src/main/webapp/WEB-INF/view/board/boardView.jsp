<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<%@ page import="com.lawAbiding.board.common.ConstVars" %>
<%@ page import="com.lawAbiding.board.common.StringUtil" %>
<%@ include file="../common/header.jsp" %>
<style>
.buttonStyle {
      padding: 3px 8px 3px 8px;
      font-size: 15px;
      font-weight: bold;
      text-align: center;
}
</style>
<script>
function goDelete(seqno, gubun){
	var frm = document.actForm;
	frm.seqno.value = seqno;
	frm.gubun.value = gubun;

	var methodInput = document.createElement('input');
    methodInput.setAttribute('type', 'hidden');
    methodInput.setAttribute('name', '_method');
    methodInput.setAttribute('value', 'DELETE');
    frm.appendChild(methodInput);

	frm.action  = "/api/board/delete/" + seqno;
	frm.submit();
}

function goModify(seqno, gubun){
	var frm = document.actForm;

	frm.seqno.value = seqno;
	frm.action  = "/api/board/modify/" + seqno;
	frm.submit();
}

function downloadFile(filepath, filename) {
	var frm = document.actForm;
	frm.fileName.value = filename;
	frm.nextPath.value = filepath;
	frm.action = "/file/fileDownload";
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
				<jsp:param name="leftNo" value="${result.gubun}"/>
			</jsp:include>
		</div>

		<!--contents start-->
<form name="actForm" method="post">
		<input type="hidden" name="seqno" />
		<input type="hidden" name="board_tp" />
		<input type="hidden" name="gubun"/>
		<input type="hidden" name="gubun_name"/>
		<input type="hidden" name="file_seq" />
		<input type="hidden" name="nextPath" />
		<input type="hidden" name="fileName" />
		<div class="contentsArea">
			<p class="path">${result.gubun_name}</p>
			<table class="tbl-n" >
				<tr height="23">
					<td><span style="font-size:14px; color:#009E82; text-align:left; padding:0px 0px 0px 8px;"><strong>${result.gubun_name}</strong></span></td>
				</tr>
			</table>
			<div class="cont-pd">
				<table class="tbl-bbs-view mt20">
					<colgroup>
						<col width="13%" />
						<col width="15%" />
						<col width="13%" />
						<col width="15%" />
						<col width="13%" />
						<col width="" />
					</colgroup>
					<tbody>
						<tr>
							<th>제목 </th>
							<td colspan="5" class="subject">${result.title}</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${result.writer}</td>
							<th>작성일</th>
                            <td colspan="2">${result.insDateFormat}</td>
                        </tr>
                           <tr>
                            <th>첨부파일</th>
                            <td colspan="5">
                                 <c:forEach items="${fileList}" var="file">
                                    <div style="padding:5px 0px 0px 0px; color:#3838a9"><a href="javascript:downloadFile('${file.getFILEPATH()}','${file.getFILENAME()}')">${file.getFILENAME()}</a><br/></div>
                                 </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6" class="story">
                                 <c:set var="content2" value="${result.contents}" />
                                 ${StringUtil.htmlConvert( StringUtil.null2void(content2))}
                            </td>
                        </tr>
					</tbody>
				</table>
				<div class="ar mt10">
                   <%
                        if(headerSessionAuthType.equals("A")){
                    %>
                            <input type="button" class="buttonStyle" value="삭제" onclick="javascript:goDelete('${result.seqno}', '${result.gubun}');">
                            <input type="button" class="buttonStyle"  value="수정" onclick="javascript:goModify('${result.seqno}', '${result.gubun}');">
                    <%
                        }
                    %>


                </div>
			</div>
		</div>
		</form>
		<!--contents end-->
	</div>
</div><!-- content-wrap end -->