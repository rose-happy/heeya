package com.mbc.jmh7login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Autowired
SqlSession sqlSession;  // spring 안에 서블릿 안에 있는 객체
	
	@RequestMapping(value = "/login")
				public String log() {
		
				return "loginput"; //JSP file명 자료입력폼
	}
	// login 폼에서 id , pw를 받아온다
	@RequestMapping(value = "/loginsave")
			public String log1(LoginDTO dto , HttpServletRequest request , HttpServletResponse response) throws IOException {
				// id , pw 폼에서 받아옴
				String id = dto.getId();
				String pw = dto.getPw();
			// 로그인 아이디로 DB에서 암호화 된 패스워드를 찾아 온다
				LoginService ls =sqlSession.getMapper(LoginService.class);
				String spw =	ls.pwsearch(id); // 암호화 된 pw (spw)
				// spw = 암호화 된 패스워드 pw = 암호화가 되지 않은 패스워드 
				//spw , pw  를 비교하기 위해 암호화 된 객체 생성
				PasswordEncoder pe = new BCryptPasswordEncoder(); //spw , pw 를 비교하면 true false 
					boolean flag		=		pe.matches(pw, spw); // 비교 함수
	
			if(flag)  // 인증이 되면 true
			{
				HttpSession hs = request.getSession(); //hs 라는 객체에 정보를 담는다
				hs.setAttribute("loginstate", true);
				hs.setAttribute("id",id);
				hs.setMaxInactiveInterval(90);

						return "redirect:/main";	
			}
			else // false
			{
				response.setCharacterEncoding("text/html;charset=utf-8");
				PrintWriter ppw = response.getWriter();
				ppw.write("<script> alert('아이디 또는 패스워드를 다시 확인 해주세요')</script>");
				ppw.write("<script>'locationstate.href ='login'</script>");
				ppw.close();
			}
			
						return "redirect:/loginput"; //JSP file명 자료입력폼
}
	@RequestMapping(value = "/logout")
			public String log1(HttpServletRequest request) {
			HttpSession hs = request.getSession();
			hs.removeAttribute("loginstate");
			hs.removeAttribute("id");
					return "redirect:/main"; //JSP file명 자료입력폼
}

}
