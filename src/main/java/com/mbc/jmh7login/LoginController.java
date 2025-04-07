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
SqlSession sqlSession;  // spring �ȿ� ���� �ȿ� �ִ� ��ü
	
	@RequestMapping(value = "/login")
				public String log() {
		
				return "loginput"; //JSP file�� �ڷ��Է���
	}
	// login ������ id , pw�� �޾ƿ´�
	@RequestMapping(value = "/loginsave")
			public String log1(LoginDTO dto , HttpServletRequest request , HttpServletResponse response) throws IOException {
				// id , pw ������ �޾ƿ�
				String id = dto.getId();
				String pw = dto.getPw();
			// �α��� ���̵�� DB���� ��ȣȭ �� �н����带 ã�� �´�
				LoginService ls =sqlSession.getMapper(LoginService.class);
				String spw =	ls.pwsearch(id); // ��ȣȭ �� pw (spw)
				// spw = ��ȣȭ �� �н����� pw = ��ȣȭ�� ���� ���� �н����� 
				//spw , pw  �� ���ϱ� ���� ��ȣȭ �� ��ü ����
				PasswordEncoder pe = new BCryptPasswordEncoder(); //spw , pw �� ���ϸ� true false 
					boolean flag		=		pe.matches(pw, spw); // �� �Լ�
	
			if(flag)  // ������ �Ǹ� true
			{
				HttpSession hs = request.getSession(); //hs ��� ��ü�� ������ ��´�
				hs.setAttribute("loginstate", true);
				hs.setAttribute("id",id);
				hs.setMaxInactiveInterval(90);

						return "redirect:/main";	
			}
			else // false
			{
				response.setCharacterEncoding("text/html;charset=utf-8");
				PrintWriter ppw = response.getWriter();
				ppw.write("<script> alert('���̵� �Ǵ� �н����带 �ٽ� Ȯ�� ���ּ���')</script>");
				ppw.write("<script>'locationstate.href ='login'</script>");
				ppw.close();
			}
			
						return "redirect:/loginput"; //JSP file�� �ڷ��Է���
}
	@RequestMapping(value = "/logout")
			public String log1(HttpServletRequest request) {
			HttpSession hs = request.getSession();
			hs.removeAttribute("loginstate");
			hs.removeAttribute("id");
					return "redirect:/main"; //JSP file�� �ڷ��Է���
}

}
