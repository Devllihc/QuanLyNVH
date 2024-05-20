package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DAO.ActivityDAO;


public class Rent  {
	private int rentid;
    private String rentname;
    private int roomid;
    private Timestamp timestart;
    private Timestamp timefinish;
    private String renterid;
    private String note;
	
    public Rent(int rentid, String rentname, int roomid, Timestamp timestart, Timestamp timefinish, String renterid,
			String note) {
		super();
		this.rentid = rentid;
		this.rentname = rentname;
		this.roomid = roomid;
		this.timestart = timestart;
		this.timefinish = timefinish;
		this.renterid = renterid;
		this.note = note;
	}
	
    public Rent(String rentname, int roomid, Timestamp timestart, Timestamp timefinish, String renterid, String note) {
		super();
		this.rentname = rentname;
		this.roomid = roomid;
		this.timestart = timestart;
		this.timefinish = timefinish;
		this.renterid = renterid;
		this.note = note;
	}

	public Rent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRentid() {
		return rentid;
	}

	public void setRentid(int rentid) {
		this.rentid = rentid;
	}

	public String getRentname() {
		return rentname;
	}

	public void setRentname(String rentname) {
		this.rentname = rentname;
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

	public String getRenterid() {
		return renterid;
	}

	public void setRenterid(String renterid) {
		this.renterid = renterid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Rent [rentid=" + rentid + ", rentname=" + rentname + ", roomid=" + roomid + ", timestart=" + timestart
				+ ", timefinish=" + timefinish + ", renterid=" + renterid + ", note=" + note + "]";
	}
    
    
    
}