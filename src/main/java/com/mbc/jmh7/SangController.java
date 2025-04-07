package com.mbc.jmh7;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class SangController {

	@Autowired // 쿼리문이 실행시 환경설정 파일 자동 연결실행
	SqlSession sqlSession;
	
	// 이미지 저장 경로
	String path="C:\\MBC12\\spring\\Day4_sang\\src\\main\\webapp\\image";
	
	@RequestMapping(value = "/")
	public String aa() {
		return "main";
	}
	@RequestMapping(value = "/main")
	public String aa0() {
		return "main";
	}
	
	@RequestMapping(value = "/sinput") // menu 에서 입력 (top에서 지정한 이름)
	public String bb() {
		return "input";
	}
	@RequestMapping(value = "/save",method = RequestMethod.POST) // 자료를 입력하여 저장
	public String cc(MultipartHttpServletRequest mul) throws IllegalStateException, IOException { // 데이터를 넘겨줘야 되기 때문에 받는 객체가 필요 > 전송객체
		String spname = mul.getParameter("spname");
		String sname = mul.getParameter("sname");
		String sarea = mul.getParameter("sarea");
		String sdate = mul.getParameter("sdate");
		int sprice = Integer.parseInt(mul.getParameter("sprice"));
		MultipartFile mf = mul.getFile("simg"); // 이미지 모든 정보
			String fname  =	mf.getOriginalFilename();  // 파일명,확장자
			UUID ud = UUID.randomUUID(); // 이미지 고유번호
			fname = ud.toString()+"-"+fname; // 이미지와 파일명 확장자 표시
			
			Service sv = sqlSession.getMapper(Service.class); // idao 에 있는 mapper 를 실행 시켜주는 객체
			sv.insertgo(spname,sname,sarea,sdate,sprice,fname); // service 에 메소드명을 넘기고 idao에 입력 
			
			mf.transferTo(new File(path+"\\"+fname)); //폴더에 이미지 저장
		
		return "input";
	}
	@RequestMapping(value = "/sout") //결과를 가져오는 것
	public String dd(Model mo){
		Service sv = sqlSession.getMapper(Service.class);
	ArrayList<SangDTO>list = sv.outall(); // 저장 객체를 만들어서 모든 데이터를 가져온다
		mo.addAttribute("list",list);
		
		return "outt";
	}	
	@RequestMapping(value = "/detail") // detail > num을 가지고 넘어온다
	public String ee(HttpServletRequest request , Model mo){ // 넘겨주는 값을 받는게
		int num = Integer.parseInt(request.getParameter("num")); 
		Service sv = sqlSession.getMapper(Service.class);
		sv.readcnt(num); // 조회수만
		SangDTO	dto = sv.detail(num); //상품번호 정보를 가져온다
		mo.addAttribute("dto", dto);
		return "outtt";
	}	
		@RequestMapping(value = "/upload")
		public String ff(HttpServletRequest request ,Model md) //dto 를 담아서 보내야 되기 때문에 Model이 필요
		{
			int num = Integer.parseInt(request.getParameter("num"));
			Service sv = sqlSession.getMapper(Service.class);
		SangDTO dto	=	sv.upload(num);
		md.addAttribute("dto", dto);
			return "upload"; // input 에서 수정을 선택하면 상품번호에 대한 내용이 표시
		}
		// uploadsave 수정 화면에서 자료 수정 후 선택하면 여기로
		@RequestMapping(value = "/uploadsave")
		public String gg(MultipartHttpServletRequest mul) throws IllegalStateException, IOException
		{
			int snum = Integer.parseInt(mul.getParameter("snumber")); //상품번호
			String spname = mul.getParameter("spname");
			String sname = mul.getParameter("sname");
			String sarea = mul.getParameter("sarea");
			String sdate = mul.getParameter("sdate");
			int sprice = Integer.parseInt(mul.getParameter("sprice"));
			MultipartFile mf = mul.getFile("simg"); // 이미지 모든 정보
			String dfname = mul.getParameter("himg"); // 기존 이미지에 파일명과 확장자 보유
				String fname  =	mf.getOriginalFilename();  // 파일명,확장자
				UUID ud = UUID.randomUUID(); // 이미지 고유번호
				fname = ud.toString()+"-"+fname; // 이미지와 파일명 확장자 표시
				
				Service sv = sqlSession.getMapper(Service.class); // idao 에 있는 mapper 를 실행 시켜주는 객체
				// 수정 폼에서 입력 받은 자료들을 서비스에 넘겨 수정 > 상품번호
				sv.upload1(spname,sname,sarea,sdate,sprice,fname,snum); // service 에 메소드명을 넘기고 idao에 입력 
				
				mf.transferTo(new File(path+"\\"+fname)); //수정 이미지 저장
			File ff = new  File(path+"\\"+dfname); // 이미지 폴더에 넘겨 받은 파일 삭제
			ff.delete();
			return "redirect:/sout";
		}
		@RequestMapping(value = "/delete")
		public String hh(HttpServletRequest request,Model md)
		{
			int num = Integer.parseInt(request.getParameter("num"));
			Service sv = sqlSession.getMapper(Service.class);
			SangDTO dto = sv.delete1(num);
			md.addAttribute("dto", dto);
			return "deleteview";
		}
		
		@RequestMapping(value = "/delete2")
		public String gg(HttpServletRequest request,Model md)
		{
			int num = Integer.parseInt(request.getParameter("snumber")); // DB에 상품자료 삭제
			String dfname = request.getParameter("himg");  // 이미지 폴더에 저장되어 있는 이미지 삭제
			Service sv = sqlSession.getMapper(Service.class);
			// DB에 저장된 상품번호 삭제
			sv.delete2(num);
			// 이미지 폴더에 자료 삭제
			File ff = new File(path+"\\"+dfname);
			ff.delete();
			
			return "redirect:/";
		}
		@RequestMapping(value = "/search")
		public String hh()
		{
			
			return "search";
		}
		
		@RequestMapping(value = "search2" , method =RequestMethod.POST )
		public String ii(HttpServletRequest request , Model md)
		{
			String key = request.getParameter("key");
			System.out.println(" 검색구분 값 :" +key);
			String svalue = request.getParameter("svalue");
			System.out.println(" 검색 값 :" +svalue);
			Service sv=sqlSession.getMapper(Service.class);
			ArrayList<SangDTO> list = sv.searchgo(key,svalue); // 동적쿼리 사용방법 1
					
			md.addAttribute("list", list);
			
			return "outttt";
		}
	
}
