package com.saeyan.dto;

import java.sql.Timestamp;

public class BoardCommentVO {
	private int num;
	private String id;
	private String cmemo;
	private int cnum;
	private Timestamp cdate;
	
	public int getNum() {
		return num;
	}
	public void setNum(int i) {
		this.num = i;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCmemo() {
		return cmemo;
	}
	public void setCmemo(String cmemo) {
		this.cmemo = cmemo;
	}
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public Timestamp getCdate() {
		return cdate;
	}
	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}
}
