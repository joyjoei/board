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
function goViewBack(boardSeq){
	var frm = document.actForm;
	frm.boardSeq.value = boardSeq;
	frm.gubun.value = "${result.gubun}";
	frm.gubun_name.value = "${result.gubun_name}";
	frm.action  = "/api/board/view/" + boardSeq;

	frm.submit();
}
function goModify(boardSeq){
	var frm = document.actForm;
	frm.boardSeq.value = boardSeq;
	var titleValue = document.getElementById("title").value;

    var targetSelect = document.getElementById("category");

    var a = targetSelect.options[targetSelect.selectedIndex].text;
    var b = targetSelect.options[targetSelect.selectedIndex].value;

    frm.gubun.value = b;
    frm.gubun_name.value = a;

	if(titleValue == ''){
		alert("제목을 입력해주세요");
		return;
	}else{
	   frm.title.value = titleValue;
	}

	oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
	if(document.getElementById("contents").value==""){
		alert("내용을 입력해주세요");
		return false;
	}else{
	    frm.contents.value = document.getElementById("contents").value;
	}

	frm.action = "/api/board/modify/"+boardSeq+"/edit";
	frm.submit();
}

function downloadFile(filepath, filename) {
	var frm = document.actForm;
	frm.fileName.value = filename;
	frm.nextPath.value = filepath;
	frm.action = "/file/fileDownload";
	frm.submit();
}

function goFileDelete(fileSeq, boardSeq){

    $.ajax({
          url:"/file/fileDelete",
          type:"POST",
          data: {
                    fileSeq : fileSeq
                },
          success: function(result) {
              if (result) {
                  alert("삭제되었습니다.");
                  var remove = document.getElementById("fileId_"+fileSeq);
                  remove.parentNode.removeChild(remove);
              } else {
                  alert("잠시 후에 시도해주세요.");
              }
          },
          error: function() {
              alert("에러 발생");
          }
      })
}

function del(value){
	var addedFormDiv = document.getElementById("addedFormDiv");
	var addedDiv = document.getElementById(value);
	// 마지막으로 생성된 폼의 ID를 통해 Div객체를 가져옴
	addedFormDiv.removeChild(addedDiv); // 폼 삭제
}

function addForm2(){
	var addedFormDiv = document.getElementById("addedFormDiv");

	var date	= new Date();
	var file	= date.getTime();

	var str = "";

	str = "<div style='padding-top:5px;'><input type=file id="+file+" name='uploads' size='40' style='height:20px;' ><span style='padding-left:4px;'><input type='button' value='삭제' onclick='Javascript:del("+file+");'></span></div>";
	// 추가할 폼(에 들어갈 HTML)

	var addedDiv = document.createElement("div"); // 폼 생성
	addedDiv.id = file; // 폼 Div에 ID 부 여 (삭제를 위해)
	addedDiv.innerHTML  = str; // 폼 Div안에 HTML삽입
	addedFormDiv.appendChild(addedDiv); // 삽입할 DIV에 생성한 폼 삽입
}

</script>

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
		<!--session info setting-->
        <c:set var="sessionAuth"  value='<%=headerSessionAuthType%>' />
        <c:set var="sessionSabun"  value='<%=headerSessionSabun%>' />
        <c:set var="sessionUserNm"  value='<%=headerSessionUserNm%>' />
        <c:set var="sessionUserId"  value='<%=headerSessionUserId%>' />

		<form name="actForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="boardSeq" />
		<input type="hidden" name="fileSeq" />
		<input type="hidden" name="modifier" value="${sessionUserNm}"/>
		<input type="hidden" name="mod_user_id" value="${sessionUserId}" />
		<input type="hidden" name="mod_sabun" value="${sessionSabun}" />
		<input type="hidden" name="gubun" />
        <input type="hidden" name="gubun_name"  />

		<div class="contentsArea">
			<p class="path">${result.gubun_name}</p>
			<table class="tbl-n" >
				<tr height="23">
					<td><span style="font-size:14px; color:#009E82; text-align:left; padding:0px 0px 0px 8px;"><strong>${result.gubun_name}</strong></span></td>
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
							<th class="p-gray brnone"><span class="p-blue n">*</span> 게시판</th>
							<td  colspan="3">
                                <select name="category" id="category">
                                    <c:forEach items="${selectBoxBoard}" var="category">
                                        <option value="${category.gubun}" <c:if test ="${result.gubun == category.gubun}">selected="selected"</c:if>>
                                            ${category.gubunName}
                                        </option>
                                    </c:forEach>
                                </select>
							</td>
						</tr>
						<tr>
							<th class="p-gray brnone"><span class="p-blue n">*</span> 제목</th>
							<td colspan="3"><input type="text" name="title" id="title" class="ipt" style="width:80%"/ value='${result.title}'/></td>
						</tr>
						<tr>
							<th class="p-gray brnone at"><span class="p-blue n">*</span> 작성자</th>
							<td colspan="3">
								<font color="gray">${result.writer}</font>
							</td>
						</tr>
						<tr>
							<th class="p-gray brnone"><span class="p-blue n">*</span> 파일첨부</th>
                            <td colspan="3">
                                <input type="button" value="추가" style='width: 40px; height:20px' onclick="addForm2();">
                                <div id="addedFormDiv" ></div>
                                    <c:forEach items="${fileList}" var="file">
                                    <div id="fileId_${file.getFILESEQ()}" style="padding:3px 0px 0px 0px; color:#3838a9"><a href="javascript:downloadFile('${file.getFILEPATH()}','${file.getFILENAME()}')">${file.getFILENAME()}</a>

                                    <input type="button" value="파일삭제" style='width: 80px; height:20px' onclick="goFileDelete('${file.getFILESEQ()}','${file.getBOARDSEQ()}');"></div>
                                </c:forEach>
                             </td>
							</td>
						</tr>
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

                <!--button-->
				<div class="ac mt20">
                    <c:set var="sessionAuth"  value='<%=headerSessionAuthType%>' />
                    <c:set var="sessionSabun"  value='<%=headerSessionSabun%>' />
                    <c:choose>
                        <c:when  test="${sessionAuth == 'A'}" >
                             <input type="button" class="buttonStyle"  value="수정"  onclick="javascript:goModify('${result.seqno}');" />
                        </c:when>
                        <c:when test="${sessionAuth == 'W'}" >
                            <c:if test="${sessionSabun == result.ins_sabun}">
                             <input type="button" class="buttonStyle" value="수정"  onclick="javascript:goModify(${result.seqno});" />
                             </c:if>
                        </c:when>
                    </c:choose>
                    <input type="button" class="buttonStyle" value="취소" onclick="javascript:goViewBack(${result.seqno});" />
                </div>
			</div>
		</div>
		</form>
		<!--contents end-->
	</div>
</div><!-- content-wrap end -->