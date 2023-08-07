<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<%@ page import="com.lawAbiding.board.common.ConstVars" %>
<%@ page import="com.lawAbiding.board.common.StringUtil" %>
<%@ include file="../common/header.jsp" %>

<script>
var inputFileList = new Array();     //  파일을 담아놓을 배열 (업로드 버튼 누를 때 서버에 전송할 데이터)


function goSave(){
	var frm = document.actForm;
	if(isEmpty(frm.title)){
		alert("제목을 입력해주세요");
		frm.title.focus();
		return;
	}
	oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);

	if(document.getElementById("contents").value==""){
		alert("내용을 입력해주세요");
		return false;
	}else{
		frm.contents.value = document.getElementById("contents").value;
	}


	frm.action = "/api/board/boardSave";
	frm.submit();
}


function Cancel(){
	history.back();
}
</script>


<!-- head-wrapper start -->
<div id="head-wrapper">
	<jsp:include page="../common/top.jsp">
		<jsp:param name="leftNo" value="06" />
	</jsp:include>	
</div><!-- head-wrapper end --> 

<!-- content-wrap start -->


<div id="content-wrap-sub">	
	<div border="1"><a href="/api/board/mainList"><img src="/images/top_img3.jpg" width="970" alt="준법지원시스템" /></a></div><!-- visual image : 970 *130 -->
	<div class="lr-wrap clfix">
	
		<!--lnb start-->
		<div class="lnbArea">
			<jsp:include page="../common/left.jsp">
				<jsp:param name="leftNo" value="${gubun}" />
			</jsp:include>	
		</div>
		<!--lnb area end--> 
 
		
		<!--contents start-->
		<form name="actForm" method="post" action = "/api/board/boardSave" enctype="multipart/form-data">

            <input type="hidden" name="ins_user_id" value= <%=headerSessionUserId%>>
            <input type="hidden" name="ins_sabun" value= <%=headerSessionSabun%>>
            <input type="hidden" name="writer" value= <%=headerSessionUserNm%>>
            <input type="hidden" name="gubun" value="${gubun}" />
            <input type="hidden" name="gubun_name" value="${gubun_name}" />

		
		<div class="contentsArea">
			<p class="path">${gubun_name}</p>
			<table class="tbl-n" >
				<tr height="23">
					<td><span style="font-size:17px; color:#009E82; text-align:left; padding:0px 0px 0px 8px;"><strong> ${gubun_name} 글쓰기</strong></span></td>
				</tr>
			</table>
			<div class="cont-pd">
				<table class="tbl-x02 mt20">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th class="p-gray brnone"><span class="p-blue n">*</span> 게시판</th>
							<td>
								<font color="gray" id=board>${gubun_name}</font>
							</td>
								
						</tr>
						<tr>
							<th class="p-gray brnone"><span class="p-blue n">*</span> 제목</th>
							<td><input type="text" name="title" class="ipt" style="width:80%" onkeyup="ChkByte(this,100);"/></td>
						</tr>
						<tr>
							<th class="p-gray brnone"><span class="p-blue n">*</span> 작성자</th>
							<td>
								<font color="gray"><%=headerSessionUserNm %></font>
							</td>
						</tr>
						<tr>
							<th class="p-gray brnone"><span class="p-blue n">*</span> 파일첨부</th>
							<td>

                                　　<input type="file" name="uploads" multiple="multiple" >

							</td>
						</tr>
						<tr>
							<th class="p-gray brnone at"><span class="p-blue n">*</span> 내용</th>
							<td>
								<textarea name="contents" id="contents" style="height:350px; width:500px;"></textarea>
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

					<%
			 			if(headerSessionAuthType.equals("A") || headerSessionAuthType.equals("W")){
			 		%>
					<input type="button" value="저장" id="button1" onclick="javascript:goSave();" />
					<%
			 			}
			 		%>
					<input type="button" value="취소" id="button1" onclick="javascript:Cancel();" />
				</div>
			</div>
		</div>
		</form>
		<!--contents end-->
	</div>
</div><!-- content-wrap end -->