package com.mbc.jmh7.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

	@Autowired
	SqlSession sqlSession;  // spring 안에 서블릿 안에 있는 객체
	
	@RequestMapping(value = "/memberinput")
			public String mm() {
		
		return "mminput"; //JSP file명 자료입력폼
	}
	// 폼에서 넘겨 받은 id가 중복인지 DB로 넘겨 검사
	@ResponseBody // 비동기식 전송 방식에는 반드시 선언 
	@RequestMapping(value = "/idgo")
			public String mm1(String id) {
		MemberService ms = sqlSession.getMapper(MemberService.class);
			int count = ms.idcount(id);
		
			if(count==0) return "ok";
			else               return "No";
	}
	
	// 폼에서 입력한 자료들을 DB에 저장
	@RequestMapping(value = "/membersave")
			public String mm2(MemberDTO dto) { 
				String id = dto.getId();
				String pw = dto.getPw();
				String name = dto.getName();
				int age = dto.getAge();
				
				//pw 암호화 
				PasswordEncoder pe = new BCryptPasswordEncoder();
				pw =	 pe.encode(pw); //pw암호화 과정 종료
				
				MemberService ms = sqlSession.getMapper(MemberService.class);
				ms.insertup(id,pw,name,age);
		
		return "redirect:/"; //JSP file명 자료입력폼
	}
	
	@RequestMapping(value = "/memberinput")
		public String mm3(Model md , HttpServletRequest request ,HttpServletResponse response) throws IOException {
		
		HttpSession hs =request.getSession();
		boolean flag	=	(boolean)		hs.getAttribute("loginstate");
		
		if(flag) // 로그인이 인증 되었을 때
		{
		
		MemberService ms = sqlSession.getMapper(MemberService.class);
		ArrayList<MemberDTO>list = ms.allout();
		md.addAttribute("list", list);
					return "memberoutt"; //JSP file명 자료입력폼
		}
		else // 로그인을 하지 않고 출력을 했을 경우 loginstate가 false 
		{
			response.setCharacterEncoding("text/html;charset=utf-8");
			PrintWriter ppw = response.getWriter();
			ppw.write("<script> alert('아이디 또는 패스워드를 다시 확인 해주세요')</script>");
			ppw.write("<script>'locationstate.href ='login'</script>");
			ppw.close();
		}
		
		return "redirect:/loginput";
		}
		
}
	

	


