package com.mbc.jmh7;

import java.util.ArrayList;

public interface Service {

	void insertgo(String spname, String sname, String sarea, String sdate, int sprice, String fname);

	ArrayList<SangDTO> outall();

	void readcnt(int num);

	SangDTO detail(int num);

	SangDTO upload(int num);

	void upload1(String spname, String sname, String sarea, String sdate, int sprice, String fname, int snum);

	SangDTO delete1(int num);

	void delete2(int num);

	ArrayList<SangDTO> searchgo(String key, String svalue); 



	
	

}
