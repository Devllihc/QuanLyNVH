package model;

import java.util.ArrayList;


import DAO.RoomDAO;

public class Room implements InterfaceSearch<Room> {
	private int roomId;
    private String name;
    private int capacity;
    public Room(int roomId, String name, int capacity, int price, String status, String note) {
		super();
		this.roomId = roomId;
		this.name = name;
		this.capacity = capacity;
		this.price = price;
		this.status = status;
		this.note = note;
	}
	private int price;
    private String status;
    private String note;
    //constructors
    
    public Room(String name, int capacity, int price, String status, String note) {
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.status = status;
        this.note = note;
    }
   
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	//getter and setter
	public int getRoomId() {
		return roomId;
	}
	
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
    @Override
	public String toString() {
		return "Room [roomId=" + roomId + ", name=" + name + ", capacity=" + capacity + ", price=" + price + ", status="
				+ status + ", note=" + note + "]";
	}
	public int insert() {
		int ketqua= RoomDAO.getInstance().insert(this);
		return ketqua;
		}
	public int update() {
		int ketqua = RoomDAO.getInstance().update(this);
		return ketqua;
	}
	public int delete() {
		int ketqua= RoomDAO.getInstance().delete(this);
		return ketqua;
	}
	public Room[] searchALL() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Room[] searchByName() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Room> searchByStatus(String t) {
		ArrayList<Room> a = RoomDAO.getInstance().selectByStatus(t);
		return a;
	}
	public ArrayList<Room> searchAllRoom() {
		ArrayList<Room> a = RoomDAO.getInstance().selectAll();
		return a;
	}
	public Room SearchByName(String t) {
		Room a = RoomDAO.getInstance().selectByName(t);
		return a;
	}
	public Room SearchByID(int t) {
		Room a = RoomDAO.getInstance().selectByID(t);
		return a;
	}
}
