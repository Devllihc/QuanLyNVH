package model;

import java.time.LocalDateTime;

import DAO.WorkDAO;

public class Work {
	String userid;
	String AccountName;
	LocalDateTime timeloggin;
	public Work(String userid, String accountName, LocalDateTime timeloggin) {
		super();
		this.userid = userid;
		AccountName = accountName;
		this.timeloggin = timeloggin;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	public LocalDateTime getTimeloggin() {
		return timeloggin;
	}
	public void setTimeloggin(LocalDateTime timeloggin) {
		this.timeloggin = timeloggin;
	}
	@Override
	public String toString() {
		return "Work [userid=" + userid + ", AccountName=" + AccountName + ", timeloggin=" + timeloggin + "]";
	}
	
	

}
