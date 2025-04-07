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

	@Autowired // �������� ����� ȯ�漳�� ���� �ڵ� �������
	SqlSession sqlSession;
	
	// �̹��� ���� ���
	String path="C:\\MBC12\\spring\\Day4_sang\\src\\main\\webapp\\image";
	
	@RequestMapping(value = "/")
	public String aa() {
		return "main";
	}
	@RequestMapping(value = "/main")
	public String aa0() {
		return "main";
	}
	
	@RequestMapping(value = "/sinput") // menu ���� �Է� (top���� ������ �̸�)
	public String bb() {
		return "input";
	}
	@RequestMapping(value = "/save",method = RequestMethod.POST) // �ڷḦ �Է��Ͽ� ����
	public String cc(MultipartHttpServletRequest mul) throws IllegalStateException, IOException { // �����͸� �Ѱ���� �Ǳ� ������ �޴� ��ü�� �ʿ� > ���۰�ü
		String spname = mul.getParameter("spname");
		String sname = mul.getParameter("sname");
		String sarea = mul.getParameter("sarea");
		String sdate = mul.getParameter("sdate");
		int sprice = Integer.parseInt(mul.getParameter("sprice"));
		MultipartFile mf = mul.getFile("simg"); // �̹��� ��� ����
			String fname  =	mf.getOriginalFilename();  // ���ϸ�,Ȯ����
			UUID ud = UUID.randomUUID(); // �̹��� ������ȣ
			fname = ud.toString()+"-"+fname; // �̹����� ���ϸ� Ȯ���� ǥ��
			
			Service sv = sqlSession.getMapper(Service.class); // idao �� �ִ� mapper �� ���� �����ִ� ��ü
			sv.insertgo(spname,sname,sarea,sdate,sprice,fname); // service �� �޼ҵ���� �ѱ�� idao�� �Է� 
			
			mf.transferTo(new File(path+"\\"+fname)); //������ �̹��� ����
		
		return "input";
	}
	@RequestMapping(value = "/sout") //����� �������� ��
	public String dd(Model mo){
		Service sv = sqlSession.getMapper(Service.class);
	ArrayList<SangDTO>list = sv.outall(); // ���� ��ü�� ���� ��� �����͸� �����´�
		mo.addAttribute("list",list);
		
		return "outt";
	}	
	@RequestMapping(value = "/detail") // detail > num�� ������ �Ѿ�´�
	public String ee(HttpServletRequest request , Model mo){ // �Ѱ��ִ� ���� �޴°�
		int num = Integer.parseInt(request.getParameter("num")); 
		Service sv = sqlSession.getMapper(Service.class);
		sv.readcnt(num); // ��ȸ����
		SangDTO	dto = sv.detail(num); //��ǰ��ȣ ������ �����´�
		mo.addAttribute("dto", dto);
		return "outtt";
	}	
		@RequestMapping(value = "/upload")
		public String ff(HttpServletRequest request ,Model md) //dto �� ��Ƽ� ������ �Ǳ� ������ Model�� �ʿ�
		{
			int num = Integer.parseInt(request.getParameter("num"));
			Service sv = sqlSession.getMapper(Service.class);
		SangDTO dto	=	sv.upload(num);
		md.addAttribute("dto", dto);
			return "upload"; // input ���� ������ �����ϸ� ��ǰ��ȣ�� ���� ������ ǥ��
		}
		// uploadsave ���� ȭ�鿡�� �ڷ� ���� �� �����ϸ� �����
		@RequestMapping(value = "/uploadsave")
		public String gg(MultipartHttpServletRequest mul) throws IllegalStateException, IOException
		{
			int snum = Integer.parseInt(mul.getParameter("snumber")); //��ǰ��ȣ
			String spname = mul.getParameter("spname");
			String sname = mul.getParameter("sname");
			String sarea = mul.getParameter("sarea");
			String sdate = mul.getParameter("sdate");
			int sprice = Integer.parseInt(mul.getParameter("sprice"));
			MultipartFile mf = mul.getFile("simg"); // �̹��� ��� ����
			String dfname = mul.getParameter("himg"); // ���� �̹����� ���ϸ�� Ȯ���� ����
				String fname  =	mf.getOriginalFilename();  // ���ϸ�,Ȯ����
				UUID ud = UUID.randomUUID(); // �̹��� ������ȣ
				fname = ud.toString()+"-"+fname; // �̹����� ���ϸ� Ȯ���� ǥ��
				
				Service sv = sqlSession.getMapper(Service.class); // idao �� �ִ� mapper �� ���� �����ִ� ��ü
				// ���� ������ �Է� ���� �ڷ���� ���񽺿� �Ѱ� ���� > ��ǰ��ȣ
				sv.upload1(spname,sname,sarea,sdate,sprice,fname,snum); // service �� �޼ҵ���� �ѱ�� idao�� �Է� 
				
				mf.transferTo(new File(path+"\\"+fname)); //���� �̹��� ����
			File ff = new  File(path+"\\"+dfname); // �̹��� ������ �Ѱ� ���� ���� ����
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
			int num = Integer.parseInt(request.getParameter("snumber")); // DB�� ��ǰ�ڷ� ����
			String dfname = request.getParameter("himg");  // �̹��� ������ ����Ǿ� �ִ� �̹��� ����
			Service sv = sqlSession.getMapper(Service.class);
			// DB�� ����� ��ǰ��ȣ ����
			sv.delete2(num);
			// �̹��� ������ �ڷ� ����
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
			System.out.println(" �˻����� �� :" +key);
			String svalue = request.getParameter("svalue");
			System.out.println(" �˻� �� :" +svalue);
			Service sv=sqlSession.getMapper(Service.class);
			ArrayList<SangDTO> list = sv.searchgo(key,svalue); // �������� ����� 1
					
			md.addAttribute("list", list);
			
			return "outttt";
		}
	
}
