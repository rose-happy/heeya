package com.mbc.jmh7;

public class SangDTO {
	
	int snumber;
	String spname,sname,sarea,sdate;
	int sprice;
	String simg;
	int sreadcnt;
	public SangDTO() {}
	public SangDTO(int snumber, String spname, String sname, String sarea, String sdate, int sprice, String simg) {
		super();
		this.snumber = snumber;
		this.spname = spname;
		this.sname = sname;
		this.sarea = sarea;
		this.sdate = sdate;
		this.sprice = sprice;
		this.simg = simg;
	}
	public int getSnumber() {
		return snumber;
	}
	public void setSnumber(int snumber) {
		this.snumber = snumber;
	}
	public String getSpname() {
		return spname;
	}
	public void setSpname(String spname) {
		this.spname = spname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSarea() {
		return sarea;
	}
	public void setSarea(String sarea) {
		this.sarea = sarea;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public int getSprice() {
		return sprice;
	}
	public void setSprice(int sprice) {
		this.sprice = sprice;
	}
	public String getSimg() {
		return simg;
	}
	public void setSima(String simg) {
		this.simg = simg;
	}
	public int getSreadcnt() {
		return sreadcnt;
	}
	public void setSreadcnt(int sreadcnt) {
		this.sreadcnt = sreadcnt;
	}
	
	
}
