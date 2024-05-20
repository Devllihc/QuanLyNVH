package model;

import java.time.LocalDateTime;

public class Request {
	String hostid;
	String request;
	LocalDateTime time;
	String note;
	int requestid;
	
	public Request() {
		super();
	}

	public int getRequestid() {
		return requestid;
	}

	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}
	

	public Request(String hostid, String request, LocalDateTime time, String note, int requestid) {
		super();
		this.hostid = hostid;
		this.request = request;
		this.time = time;
		this.note = note;
		this.requestid = requestid;
	}

	public Request(String hostid, String request, LocalDateTime time, String note) {
		super();
		this.hostid = hostid;
		this.request = request;
		this.time = time;
		this.note = note;
	}
	
	public String getHostid() {
		return hostid;
	}

	public void setHostid(String hostid) {
		this.hostid = hostid;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Request [hostid=" + hostid + ", request=" + request + ", time=" + time + ", note=" + note + "]";
	}

}
