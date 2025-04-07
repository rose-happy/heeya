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
	SqlSession sqlSession;  // spring �ȿ� ���� �ȿ� �ִ� ��ü
	
	@RequestMapping(value = "/memberinput")
			public String mm() {
		
		return "mminput"; //JSP file�� �ڷ��Է���
	}
	// ������ �Ѱ� ���� id�� �ߺ����� DB�� �Ѱ� �˻�
	@ResponseBody // �񵿱�� ���� ��Ŀ��� �ݵ�� ���� 
	@RequestMapping(value = "/idgo")
			public String mm1(String id) {
		MemberService ms = sqlSession.getMapper(MemberService.class);
			int count = ms.idcount(id);
		
			if(count==0) return "ok";
			else               return "No";
	}
	
	// ������ �Է��� �ڷ���� DB�� ����
	@RequestMapping(value = "/membersave")
			public String mm2(MemberDTO dto) { 
				String id = dto.getId();
				String pw = dto.getPw();
				String name = dto.getName();
				int age = dto.getAge();
				
				//pw ��ȣȭ 
				PasswordEncoder pe = new BCryptPasswordEncoder();
				pw =	 pe.encode(pw); //pw��ȣȭ ���� ����
				
				MemberService ms = sqlSession.getMapper(MemberService.class);
				ms.insertup(id,pw,name,age);
		
		return "redirect:/"; //JSP file�� �ڷ��Է���
	}
	
	@RequestMapping(value = "/memberinput")
		public String mm3(Model md , HttpServletRequest request ,HttpServletResponse response) throws IOException {
		
		HttpSession hs =request.getSession();
		boolean flag	=	(boolean)		hs.getAttribute("loginstate");
		
		if(flag) // �α����� ���� �Ǿ��� ��
		{
		
		MemberService ms = sqlSession.getMapper(MemberService.class);
		ArrayList<MemberDTO>list = ms.allout();
		md.addAttribute("list", list);
					return "memberoutt"; //JSP file�� �ڷ��Է���
		}
		else // �α����� ���� �ʰ� ����� ���� ��� loginstate�� false 
		{
			response.setCharacterEncoding("text/html;charset=utf-8");
			PrintWriter ppw = response.getWriter();
			ppw.write("<script> alert('���̵� �Ǵ� �н����带 �ٽ� Ȯ�� ���ּ���')</script>");
			ppw.write("<script>'locationstate.href ='login'</script>");
			ppw.close();
		}
		
		return "redirect:/loginput";
		}
		
}
	

	


