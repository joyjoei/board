package com.lawAbiding.board.controller;

import com.lawAbiding.board.common.ConstVars;
import com.lawAbiding.board.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileApiController {
    private final FileService fileService;
    private String fileRootPath = ConstVars.WEB_ROOT_PATH;


    // 파일 다운로드
    @RequestMapping("/fileDownload")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getParameter("fileName");
        String nextPath = request.getParameter("nextPath");
        String fullPath = fileRootPath + nextPath + fileName;

        File f = new File(fullPath);

        // file 다운로드 설정
        String attach_nm = new String(fileName.getBytes(),"ISO-8859-1");

        byte b[] = new byte[4062];
        response.reset();
        String strClient=request.getHeader("User-Agent");
        if(strClient.indexOf("MSIE 5.5")>-1)
        {
            response.setContentType("Content-type: application/x-msdownload; charset=utf8");
            response.setHeader("Content-Disposition", "attachment;filename="+attach_nm);
        } else {
            response.setContentType("Content-type: application/x-msdownload; charset=utf8");
            response.setHeader("Content-Disposition", "attachment;filename="+attach_nm);
        }
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        response.setContentType("application/download");
        response.setContentLength((int)f.length());
       // response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");
        // response 객체를 통해서 서버로부터 파일 다운로드
        OutputStream os = response.getOutputStream();
        // 파일 입력 객체 생성
        FileInputStream fis = new FileInputStream(f);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
    }

    @RequestMapping("/fileDelete")
    @ResponseBody
    public boolean fileDelete(@RequestParam("fileSeq") String fileSeq) throws IOException {

        System.out.println("fileSeq : " );
        System.out.println("dioijjj "+fileSeq);

        int fileSeq_ = Integer.parseInt(fileSeq);

        boolean result = fileService.deleteFileById(fileSeq_);
        System.out.println("controller : "+ result );
        return result;
    }
}
