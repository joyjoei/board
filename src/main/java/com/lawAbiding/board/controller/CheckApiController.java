package com.lawAbiding.board.controller;
import org.apache.commons.codec.binary.Base64;
import com.lawAbiding.board.domain.check.CheckEntity;

import com.lawAbiding.board.service.CheckService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sso.CoviSSO;
import sutil.Encrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import com.lawAbiding.board.common.ConstVars;
import com.lawAbiding.board.common.StringUtil;

@Controller
@RequiredArgsConstructor
@RequestMapping("/checkAuth")
public class CheckApiController {

    private final CheckService checkService;

    @GetMapping("/ssoChk2")
    public @ResponseBody String loginSSO2(Model model, HttpServletRequest request, HttpServletResponse response) {
        String gwUrl = "gw.kggroup.co.kr";
        String chkReferer = request.getHeader("Referer");
        String sabun = "";
        String dept_id = "";
        String email = "";
        String user_id = "";
        String user_nm = "";
        String authType ;

        System.out.println("================= ref : " + chkReferer);
        /*레퍼럴 체크 */

        /*1. 코비전 sso 정보 가져온다*/
        CoviSSO covisso = new CoviSSO();

        if(covisso.Validation(request)){
            sabun = covisso.getEN(request); //키 값
            user_id = covisso.getID(request);
            dept_id = covisso.getGR(request);

            user_nm = covisso.getUSERNM(request);

            System.out.println("-----------user_nm "+ user_nm);
           // byte[] decodedBytes = Base64.decodeBase64(user_nm);
           // user_nm = new String(decodedBytes);


            email = covisso.getMAIL(request);

            if(sabun == null || sabun.getBytes().length > 8 || sabun.getBytes().length < 6) {
                StringUtil.ErrorAlert(response,"SSO 로그인 실패하였습니다. 그룹웨어를 통해 로그인하시기 바랍니다.");
            }
        /*2. 가져온 사번으로 권한을 조회한다.
            권한테이블에 존재하지 않는다면 읽기권한(R)
            관리자의경우 등록,수정에 관한 제한이 없으며
            쓰기권한('W')일경우에는 본인이 쓴글에만 수정권한이 주어진다, 등록도 가능함
       */
            //CheckEntity result = checkService.getCheckAuth(sabun);
            CheckEntity result = checkService.getCheckAuth(sabun);
            if(result != null){
                authType = result.getAuth_type();
                user_nm = result.getUser_nm();
            }else{
                //권한정보에 존재하지 않으면 읽기권한('R')으로 셋팅한다.
                authType = "R";
            };

            //SSO로 받아온 정보중 필요한정보를 세션에 저장한다.
            HttpSession session = request.getSession();
            session.setAttribute("sabun", sabun);
            session.setAttribute("user_id", user_id);
            session.setAttribute("user_nm", user_nm);
            session.setAttribute("dept_id", dept_id);
            session.setAttribute("user_mail", email);
            session.setAttribute("auth_type", authType);

            System.out.println(session.getAttribute("sabun"));
            System.out.println(session.getAttribute("user_id"));
            System.out.println(session.getAttribute("user_nm"));
            System.out.println(session.getAttribute("auth_type"));
        }else {
            String errMsg = covisso.getErrorMessage(request);
            System.err.println("errMsg   : " + errMsg);
            StringUtil.ErrorAlert(response,"SSO 로그인 실패하였습니다. 그룹웨어를 통해 로그인하시기 바랍니다.");
            //return "<script> alert('SSO 로그인 실패하였습니다. 그룹웨어를 통해 로그인하시기 바랍니다.); </script>";
        }
        return "<script> location.href = '/api/board/mainList'; </script>";
    }

    protected String DecodeBySType(String strData){
        String strRet = null;
        int e=0, d=0, s=0, i=0;
        strRet = Encrypt.com_Decode(strData);
        e = strRet.indexOf(":");
        d = strRet.indexOf(":sisenc");
        if(e > -1 && d > -1)
            strRet = strRet.substring(e+1, d);
        return strRet;
    }
}
