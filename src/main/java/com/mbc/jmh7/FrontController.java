package com.mbc.jmh7;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {

	@RequestMapping(value = "/")
	public String aa(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		hs.setAttribute("loginstate", false);
		return "main";
	}
	
	@RequestMapping(value = "/main")
	public String aa1() {
		return "main";
	}
}
