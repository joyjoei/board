package com.lawAbiding.board.controller;

import com.lawAbiding.board.domain.board.*;
import com.lawAbiding.board.domain.category.CategoryEntity;
import com.lawAbiding.board.domain.board.BoardEntity;
import com.lawAbiding.board.domain.compliance.ComplianceEntity;
import com.lawAbiding.board.domain.file.FileEntity;
import com.lawAbiding.board.model.BoardRequest;
import com.lawAbiding.board.model.ComplianceRequest;
import com.lawAbiding.board.service.BoardService;
import com.lawAbiding.board.service.ComplianceService;
import com.lawAbiding.board.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawAbiding.board.common.ConstVars;
import com.lawAbiding.board.common.StringUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;
    private final FileService fileService;
    private final ComplianceService complianceService;


  /*  @GetMapping("/pagingTest")
    public Page<BoardEntity> getAllUsers() {
        Sort sort1 = Sort.by("seqno").descending();
        Pageable pageAble = PageRequest.of(0, 10, sort1);
        return boardService.findAll(pageAble);
    }
*/
    @GetMapping("/mainList")
    public String mainList(Model model){
        //전체 리스트 중 메인 게시판 (최신 15개)

        List<BoardInterface> list = boardService.selectMainList();
        List<BoardEntity> listEntity = new ArrayList<BoardEntity>();

        for(int i=0;i<list.size();i++){
            BoardEntity tempEntity = new BoardEntity();

            BoardInterface temp = list.get(i);

            BeanUtils.copyProperties(temp , tempEntity);
            String tempTitle = StringUtil.cutString(temp.getTitle(), "..", 30);
            tempEntity.setTitle(tempTitle);
            listEntity.add(tempEntity);
        }
        //view에 보낸다.
        model.addAttribute("boardList", listEntity);
        return "view/board/mainList";
    }

    @GetMapping("/boardAllList")
    public String boardAllList(Model model){

        //전체 리스트 조회
        List<BoardEntity> list = boardService.boardAllList();

        //view에 보낸다.
        model.addAttribute("boardList", list);
        model.addAttribute("gubun", "구분");

        return "view/board/boardList";
    }

    //as is 시판별 리스트 조회
    /*@GetMapping("/categoryList")
    public String findListByGubun(@RequestParam(required = false, defaultValue = "") String gubun, Model model,
                            @PageableDefault(size = 10, sort = "seqno", direction = Sort.Direction.DESC) Pageable pageable){
        //gubun_name 찾기
        String gubun_name = boardService.findGubunName(gubun);

        //where gubun = :gubun , Paging 처리
        Page<BoardEntity> ulist = boardService.findAllByGubun(gubun, pageable);


        List<BoardEntity> aa = ulist.toList();


        int pageNumber = ulist.getPageable().getPageNumber(); //현재페이지
        int totalPages = ulist.getTotalPages(); //총 페이지 수. 검색에따라 10개면 10개..
        int pageBlock = 9; //블럭의 수 1, 2, 3, 4, 5
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //현재 페이지가 7이라면 1*5+1=6
        int endBlockPage = startBlockPage+pageBlock-1; //6+5-1=10. 6,7,8,9,10해서 10.
        endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;

        model.addAttribute("boardList", ulist);
        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("gubun", gubun);
        model.addAttribute("gubun_name", gubun_name);

        return "view/board/boardList";
    }
    */

    @RequestMapping("/categoryList")
    public String findListByGubuntest(@RequestParam(required = false, defaultValue = "") String gubun,
                                      @RequestParam(required = false, defaultValue = "") String title,
                                      @RequestParam(required = false, defaultValue = "") String contents,
                                      Model model, @PageableDefault(size = 10, sort = "seqno", direction = Sort.Direction.DESC) Pageable pageable) {
        //gubun_name 찾기
        String gubun_name = boardService.findGubunName(gubun);
        Page<BoardEntity> ulist = boardService.findCategoryList(gubun, title, contents, pageable);


        int pageNumber = ulist.getPageable().getPageNumber(); //현재페이지
        int totalPages = ulist.getTotalPages(); //총 페이지 수. 검색에따라 10개면 10개..
        int pageBlock = 9; //블럭의 수 1, 2, 3, 4, 5
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1; //현재 페이지가 7이라면 1*5+1=6
        int endBlockPage = startBlockPage + pageBlock - 1; //6+5-1=10. 6,7,8,9,10해서 10.
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("boardList", ulist);
        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("gubun", gubun);
        model.addAttribute("gubun_name", gubun_name);
        return "view/board/boardList";
    }

    @PostMapping("/boardForm")
    public String view(@RequestParam String gubun,
                       @RequestParam String gubun_name,
                       Model model){
        model.addAttribute("gubun", gubun);
        model.addAttribute("gubun_name",gubun_name);

        return "view/board/boardForm";

    }

    //게시글 상세조회
    @RequestMapping("/view/{seqno}")
    public String view(@PathVariable int seqno, Model model){

        BoardEntity result = boardService.boardView(seqno);

        //Date 타입 format => 임시 컬럼 insDateFormat에 set
       /* Date tempInsDate = result.getIns_date();
        String parsedLocalDate = tempInsDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String stringInsDate = StringUtil.dateFormatToKor(parsedLocalDate);

        result.setInsDateFormat(stringInsDate);*/
        result.setInsDateFormat(result.getIns_date().toString());

        //FILE
        List<FileEntity> fileList = boardService.getBoardFileList(seqno);

        model.addAttribute("result", result);
        model.addAttribute("fileList", fileList);
        return "view/board/boardView";
    }

    @RequestMapping(value = "/boardSave")
    public String boardSave(HttpServletRequest request) {

        /*System.out.println(request.getParameter("gubun"));
        System.out.println(request.getParameter("gubun_name"));
        System.out.println(request.getParameter("ins_user_id"));
        System.out.println(request.getParameter("ins_sabun"));
        System.out.println(request.getParameter("writer"));
        System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("contents"));*/

        String gubun_ = request.getParameter("gubun");


        /* 게시글 저장 시작 */
        BoardRequest temp = new BoardRequest();
        temp.setGubun(request.getParameter("gubun"));
        temp.setGubun_name(request.getParameter("gubun_name"));
        temp.setWriter(request.getParameter("writer"));
        temp.setIns_sabun(request.getParameter("ins_sabun"));
        temp.setIns_user_id(request.getParameter("ins_user_id"));
        temp.setTitle(request.getParameter("title"));
        temp.setContents(request.getParameter("contents"));

        int seqNo = boardService.boardCreate(temp).getSeqno();

  System.out.println("1 저장된 seqNo : " + seqNo);

        /* 게시글 저장 끝 */
        /* 파일 저장 시작 */

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;

        //파일 업로드
        List<MultipartFile> fileList = mRequest.getFiles("uploads");

        System.out.println("mRequest.getAttribute : " + mRequest.getAttribute("title"));
        System.out.println("mRequest.fileList : " + fileList);

        String path = ConstVars.WEB_ROOT_PATH ;   //    "D:/apache-tomcat-8.5.90/apache-tomcat-8.5.90/webapps/"
        String UPLOAD_ROOT_PATH = ConstVars.UPLOAD_ROOT_PATH ; // "upload/"

        String boardId = boardService.findGubunEnName(gubun_); // 게시판 아이디 ISSUE

        String fullPath = path + UPLOAD_ROOT_PATH + boardId +"/";

        String originFileName = "";
        int cnt = 0;

        if(!fileList.get(0).isEmpty()) {
            for (MultipartFile mf : fileList) {
                originFileName = mf.getOriginalFilename(); // 원본 파일 명
                long fileSize = mf.getSize(); // 파일 사이즈

    /*System.out.println("2 originFileName : " + originFileName);
    System.out.println("3 fileSize : " + fileSize);*/

                String saveFile = fullPath + originFileName; //파일 저장 경로

                try {
                    mf. transferTo(new File(saveFile));
                    cnt++;
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if(cnt>0){
                    /* 파일 DB 저장 시작 */
                    FileEntity fileTemp = new FileEntity();
                    fileTemp.setBOARDID(boardId); // ISSUE
                    fileTemp.setFILENAME(originFileName);

                    fileTemp.setFILEPATH("/" + UPLOAD_ROOT_PATH + boardId +"/");
                    fileTemp.setBOARDSEQ(seqNo);

                    fileService.fileSave(fileTemp);

                }
            }
        }
        /* 파일 저장 끝 */
        return "redirect:/api/board/categoryList?gubun="+ request.getParameter("gubun");
    }

    @DeleteMapping("/delete/{seqno}")
    public String deleteUser(@PathVariable int seqno, String gubun){

        boolean success = boardService.deleteById(seqno);
        if (success) {
            // Success: Redirect to the success page with a success message
           // attributes.addAttribute("message", "삭제되었습니다.");
        } else {
            // Failure: Redirect to the error page with an error message
          //  attributes.addAttribute("message", "삭제 중 오류가 발생하였습니다. \n 담당자에게 문의하세요.");
        }
       // attributes.addAttribute("gubun", gubun);
        return "redirect:/api/board/categoryList?gubun="+gubun;
    }

    @PostMapping("/modify/{seqno}")
    public String goModifyForm(@PathVariable int seqno, Model model){

        BoardEntity result = boardService.boardView(seqno);
        List<CategoryEntity> selectBoxBoard = boardService.selectBoxBoard();

        //Date 타입 format => 임시 컬럼 insDateFormat에 set
        /*Date tempInsDate = result.getIns_date();
        String parsedLocalDate = tempInsDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String stringInsDate = StringUtil.dateFormatToKor(parsedLocalDate);
        result.setInsDateFormat(stringInsDate);*/
        result.setInsDateFormat(result.getIns_date().toString());

        //FILE
        List<FileEntity> fileList = boardService.getBoardFileList(seqno);

        model.addAttribute("result", result);
        model.addAttribute("fileList", fileList);
        model.addAttribute("selectBoxBoard", selectBoxBoard);
        return "view/board/boardModify";
    }

    @PostMapping("/modify/{id}/edit")
    public String editPost(HttpServletRequest request, RedirectAttributes redirectAttributes) {

       /* System.out.println(request.getParameter("gubun"));
        System.out.println(request.getParameter("gubun_name"));

        System.out.println(request.getParameter("boardSeq"));
        System.out.println(request.getParameter("mod_user_id"));
        System.out.println(request.getParameter("mod_sabun"));
        System.out.println(request.getParameter("modifier"));
        System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("contents"));
*/
        /* 게시글 수정 시작 */
        BoardRequest temp = new BoardRequest();
        temp.setSeqNo(Integer.parseInt(request.getParameter("boardSeq")));
        temp.setGubun(request.getParameter("gubun"));
        temp.setGubun_name(request.getParameter("gubun_name"));
        temp.setModifier(request.getParameter("modifier"));
        temp.setMod_sabun(request.getParameter("mod_sabun"));
        temp.setMod_user_id(request.getParameter("mod_user_id"));
        temp.setTitle(request.getParameter("title"));
        temp.setContents(request.getParameter("contents"));


        int seqNo = boardService.boardUpdate(temp).getSeqno();

        /* 파일 저장 시작 */
        String gubun_ = request.getParameter("gubun");
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;

        //파일 업로드
        List<MultipartFile> fileList = mRequest.getFiles("uploads");

        System.out.println("mRequest.getAttribute : " + mRequest.getAttribute("title"));
        System.out.println("mRequest.fileList : " + fileList);

        String path = ConstVars.WEB_ROOT_PATH ;   //    "D:/upload/"
        String UPLOAD_ROOT_PATH = ConstVars.UPLOAD_ROOT_PATH ; // "/upload/"
        String boardId = boardService.findGubunEnName(gubun_); // 게시판 아이디 ISSUE

        String fullPath = path + UPLOAD_ROOT_PATH + boardId +"/";
        System.out.println("0 fullPath : " + fullPath);
        String originFileName = "";
        int cnt = 0;
        if(fileList != null){
            if(fileList.size() > 0) {
                if (!fileList.get(0).isEmpty()) {
                    for (MultipartFile mf : fileList) {
                        originFileName = mf.getOriginalFilename(); // 원본 파일 명
                        long fileSize = mf.getSize(); // 파일 사이즈

                        System.out.println("2 originFileName : " + originFileName);
                        System.out.println("3 fileSize : " + fileSize);

                        String saveFile = fullPath + originFileName; //파일 저장 경로
                        System.out.println("3.5 safeFile : " + saveFile);
                        try {
                            mf.transferTo(new File(saveFile));
                            cnt++;
                        } catch (IllegalStateException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        if (cnt > 0) {
                            /* 파일 DB 저장 시작 */
                            FileEntity fileTemp = new FileEntity();

                            System.out.println("4 boardID : " + boardId); //String
                            System.out.println("5 file_path : " + UPLOAD_ROOT_PATH + boardId + "/");

                            fileTemp.setBOARDID(boardId); // ISSUE
                            fileTemp.setFILENAME(originFileName);

                            fileTemp.setFILEPATH("/" + UPLOAD_ROOT_PATH + boardId + "/");
                            fileTemp.setBOARDSEQ(seqNo);

                            fileService.fileSave(fileTemp);

                        }
                    }
                }
            }
        }
        /* 파일 저장 끝 */
        redirectAttributes.addFlashAttribute("seqNo", seqNo);
        return "redirect:/api/board/view/" + seqNo;
    }

    @GetMapping("/complianceView")
    public String compliance(Model model){
        int seqno = 1;
        ComplianceEntity result = complianceService.selectAll(seqno);
        model.addAttribute("result", result);

        return "view/board/compliance";
    }

    @PostMapping("/complianceModify")
    public String complianceModifyForm(@RequestParam int seqno, Model model){
        ComplianceEntity result = complianceService.selectAll(seqno);
        model.addAttribute("seqno", seqno);
        model.addAttribute("result", result);

        return "view/board/complianceModify";
    }

    @PostMapping("/complianceModify/{id}/edit")
    public String complianceModify(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        System.out.println(request.getParameter("seqno"));

        /* 게시글 수정 시작 */
        ComplianceRequest temp = new ComplianceRequest();
        temp.setSeqNo(Integer.parseInt(request.getParameter("seqno")));
        temp.setModifier(request.getParameter("modifier"));
        temp.setMod_sabun(request.getParameter("mod_sabun"));
        temp.setMod_user_id(request.getParameter("mod_user_id"));
        temp.setContents(request.getParameter("contents"));

        int seqNo = complianceService.complianceUpdate(temp).getSeqno();
        return "redirect:/api/board/complianceView";
    }

    @GetMapping("/covenantView")
    public String covenantView(Model model){
        return "view/board/covenant";
    }


    @GetMapping("/main")
    public String main(Model model){
        return "view/board/main";
    }


    @GetMapping("/main2")
    public String main2(Model model){
        return "view/board/main2";
    }

}
