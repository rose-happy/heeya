package com.mbc.jmh7.member;

import java.util.ArrayList;

public interface MemberService {

	int idcount(String id);

	void insertup(String id, String pw, String name, int age);

	ArrayList<MemberDTO> allout();

}
