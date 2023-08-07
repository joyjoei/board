package com.lawAbiding.board.common;

import java.io.UnsupportedEncodingException;
import java.io.PrintWriter;

import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StringUtil {
	//알림창만 띄우기
	@ResponseBody
	public static void ErrorAlert(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter w = response.getWriter();
			w.write("<script>alert('"+msg+"'); history.go(-1)</script>");
			w.flush();
			w.close();
		} catch(Exception e) {
			System.out.println("에러에러");
			e.printStackTrace();
		}
	}
	
	/**
	 * 이미지 파일 확장자 체크 
	 *
	 * @param String fileExt 파일 확장자명
	 * @return String 
	 */
	public static boolean imageFileCheck(String fileExt)
	{
		if(fileExt==null) {
			return false;
		} else {
			if( (fileExt.equals("bmp") )
					|| fileExt.equals("BMP") 
					|| fileExt.equals("jpg")
					|| fileExt.equals("JPG")
					|| fileExt.equals("gif")
					|| fileExt.equals("GIF")
					|| fileExt.equals("png") 
					|| fileExt.equals("PNG")
			) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * 이미지 파일 Mime Type 체크 
	 *
	 * @param String mimeType 파일 확장자명
	 * @return String 
	 */
	public static boolean mimeTypeCheck(String mimeType)
	{
		if(mimeType==null) {
			return false;
		} else {
			if( (mimeType.equals("image/gif") )
					|| mimeType.equals("image/jpeg") 
					|| mimeType.equals("image/x-ms-bmp")
					|| mimeType.equals("image/png")
			) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
     * HTML코드가 웹상에서 그대로 출력될 수 있도록 변경한다. 
     * @param org HTML문자열
     * @return 주요태그가 치환된 문자열
     */
    public static String convertHtml(String org){
        int len = org.length();
        StringBuffer sb = new StringBuffer();
        char c = 0;
 
        for(int i=0; i<len; i++){
            c = org.charAt(i);
            if(c=='<')  sb.append("&lt;");
            else if(c=='>')  sb.append("&gt;");
            else if(c=='\"') sb.append("&quot;");
            else if(c=='\'') sb.append("&#39;");
            else if(c=='\"') sb.append("&#34;");
            else if(c=='\t') sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
            else sb.append(c);
        }
        return sb.toString();
    }
    
    public static String htmlConvert(String org){
        if(org!=null) {
        	org = org.replaceAll("&lt;", "<");
        	org = org.replaceAll("&gt;", ">");
        } else {
        	org = "";
        }
        return org;
    }
    
    
    public static String getExtendName(String fname){
    	  /*
    	   * 파일의 확장자를 구하는 메소드
    	   * 
    	   * */
    	  String[] str = fname.split("\\.");
    	  int size  = str.length;
    	  
    	  String ret  = fname;
    	  if(size > 0){
    	   ret   = str[size-1];
    	  }
    	  return ret;
    	 }
    
    /**
	 * 파일명가져오기
	 *
	 * @param fileName 파일명
	 * @return 확장자
	 */
	public static String getFileName(String fileName)
	{
		if(fileName != null && fileName.length()>0){
			int index = fileName.lastIndexOf(".");
			if(index != -1)
			{
				String extension = fileName.substring(0,index);

				return extension;
			}
		}
		return null;
	}

	 /**
	 * iso-8859-1 -> UTF-8로 인코딩하기
	 *
	 */
	public static String setUTFString(String paramStr)
	{
		String returnStr = "";
		if(paramStr != null && paramStr.length()>0){
			try {
				returnStr = new String(paramStr.getBytes("iso-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnStr;
	}

	/**
	 * String 이 유효한지 체크한다.
	 *
	 * @param str 체크할 문자열
	 * @return <code>true</code> 유효한 문자열; <code>false</code> 유효하지 않은 문자열
	 */
    public static boolean valid(String str){
        if(str==null || str.length()==0) return false;
        return true;
    }
    
	/**
	 * <pre>
	 * 가격을 , 로 포맷화 시킨다     
	 * 10000 -&gt; 10,000
	 * </pre>
	 *
	 * @param price 가격  ( <code>String</code> ) 
	 * @return 3자리단위로 , 로 포맷화된 문자열 
	 */
    public static String priceToComma(String price){        
        boolean containsMinus = false;

        if(!valid(price)) return "";

        if(price.indexOf(".") != -1){
			price = price.substring(0, price.indexOf("."));
		}

        if(!valid(price)) return "";

        if(price.indexOf("-") == 0){
            containsMinus = true;
            price = price.substring(1);
        }
            
        int len = price.length();
        StringBuffer sbuf = new StringBuffer();

        for(int i=0; i<len; i++){
            if(i != 0 && i % 3 == 0) sbuf.append(",");
                sbuf.append(price.charAt((len-1)-i));
        }

        if(containsMinus)
            sbuf.append("-");

        return sbuf.reverse().toString();
    }
    
	/**
     * 엔터를 &lt;BR&gt; 태그로 변환        
	 *
	 * @param inStr 바꿀 문자열
	 * @return 치환된 문자열
	 */
    public static String putsBR(String inStr){        
                
        if (inStr == null) return null;        
        
        char c=(char)0;
        char prevchar=(char)0;
		StringBuffer sb = new StringBuffer();
                                            
        for (int i=0; i < inStr.length() ; i++) {
            c = inStr.charAt(i);
            if((c==13 && prevchar == 10) || (c==10 && prevchar == 13)) sb.append("<br>");
			//if(c==13) sb.append("<br>");
			//if(c==10) sb.append("<br>");
            else sb.append(c);

            prevchar=c;
        }        
                                            
        return sb.toString();        
    } 

    /**
	 * <pre>
	 * 스트링을 일정한 길이로 잘라서 append 로 (...) 축약한다 
	 * 길이를 자를때는 바이트 단위로 자르므로, 한글은 2byte 이다. 
	 * </pre>
	 *
	 * @param str 자를 문자열 
	 * @param append 자르고 나서 append 할 문자열
	 * @param len 잘라낼 길이
	 */
	public static String cutString(String str, String append, int len){
		if(str!=null) {
			int i=0;
			//byte [] b = str.getBytes();
			int b = str.length();
			//if(b.length<=len) return str;
			if(b<=len) {
				return str;
			} else {
				return str.substring(0,15) + append;
			}
			/*for(i=0; i<len; ){
				if(b[i]<0){
					i+=2;
					continue;
				}
				i++;
			}*/
			//return (new String(b, 0, i)+append);
		} else {
			return "";
		}
	}
	
	/**
	 * NULL TO SPACE
	 *
	 * @return String 
	 */
	public static String null2void(String str)
	{
		if(str==null) {
			return "";
		} else {
			return str;
		}
	}
	
	/**
	 * YYYYMMDD 날짜를 YYYY년 MM월 DD일로 치환해서 리턴
	 *
	 * @return String 
	 */
	public static String dateFormatToKor(String dateStr)
	{
		if(dateStr!=null) {
			if(dateStr.length()==8) {
				return dateStr.substring(0,4)+"년 "+dateStr.substring(4,6)+"월 "+dateStr.substring(6,8)+"일";
			} else {
				return dateStr;
			}
		} else {
			return "";
		}
	}
	
	/**
	 * 0000000000 13자리 주민번호를 받아 중간에 - 을 붙혀서 리턴
	 *
	 * @return String 
	 */
	public static String formatJuminNum(String str)
	{
		if(str!=null) {
			if(str.length()==13) {
				return str.substring(0,6)+"-"+str.substring(6,13);
			} else {
				return str;
			}
		} else {
			return "";
		}
	}
	
	/**
	 * 000000 6자리 우편번호를 받아 중간에 - 을 붙혀서 리턴
	 *
	 * @return String 
	 */
	public static String formatZipcode(String str)
	{
		if(str!=null) {
			if(str.length()==6) {
				return str.substring(0,3)+"-"+str.substring(3,6);
			} else {
				return str;
			}
		} else {
			return "";
		}
	}
	
	/**
	 * 0000 4자리 시간형식을 받아 중간에 : 을 붙혀서 리턴
	 *
	 * @return String 
	 */
	public static String formatTime(String str)
	{
		if(str!=null) {
			if(str.length()==4) {
				return str.substring(0,2)+":"+str.substring(2,4);
			} else {
				return str;
			}
		} else {
			return "";
		}
	}
	
	/**
	 * 000000000 9자리의 응시번호를 받아 0000-00000 로 리턴
	 *
	 * @return String 
	 */
	public static String formatExamNumber(String str)
	{
		if(str!=null) {
			if(str.length()==9) {
				return str.substring(0,4)+"-"+str.substring(4,9);
			} else {
				return str;
			}
		} else {
			return "";
		}
	}
	
	public static String getParam(HttpServletRequest request, String paramName) {
		String paramStr = request.getParameter(paramName);
		String returnStr = null;
		if (paramStr == null) {
			returnStr = "";
		} else {
			returnStr = paramStr;
		}
			  
		return returnStr;
	}
	
	public static String getParam(HttpServletRequest request, String paramName, String changeStr) {
		String paramStr = request.getParameter(paramName);
		String returnStr = null;
		if (paramStr == null || "".equals(paramStr)) {
			returnStr = changeStr;
		} else {
			returnStr = paramStr;
		}
			  
		return returnStr;
	}
}
