package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DAO.ActivityDAO;


public class Activity  {
	private int activityid;
    private String activityname;
    private int roomid;
    private Timestamp timestart;
    private Timestamp timefinish;
    private String note;
	
    public Activity(int activityid, String activityname, int roomid, Timestamp timestart, Timestamp timefinish,
			String note) {
		super();
		this.activityid = activityid;
		this.activityname = activityname;
		this.roomid = roomid;
		this.timestart = timestart;
		this.timefinish = timefinish;
		this.note = note;
	}

	public Activity(String activityname, int roomid, Timestamp timestart, Timestamp timefinish, String note) {
		super();
		this.activityname = activityname;
		this.roomid = roomid;
		this.timestart = timestart;
		this.timefinish = timefinish;
		this.note = note;
	}

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getActivityid() {
		return activityid;
	}

	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public Timestamp getTimestart() {
		return timestart;
	}

	public void setTimestart(Timestamp timestart) {
		this.timestart = timestart;
	}

	public Timestamp getTimefinish() {
		return timefinish;
	}

	public void setTimefinish(Timestamp timefinish) {
		this.timefinish = timefinish;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Activity [activityid=" + activityid + ", activityname=" + activityname + ", roomid=" + roomid
				+ ", timestart=" + timestart + ", timefinish=" + timefinish + ", note=" + note + "]";
	}
	
  
	
}