package model;

import java.util.ArrayList;

import DAO.DeviceDAO;


public class Device implements InterfaceSearch<Device> {
	private int deviceId;
    private String devicename;
    private int amount;
    private int roomId;
    private int price;
    private String note;
    private String status;
	
    public Device() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Device(String devicename, int amount, int price, String status, int roomId, String note, int deviceId) {
		this.devicename = devicename;
        this.amount = amount;
        this.roomId = roomId;
        this.price = price;
        this.note = note;
        this.status= status; ;
        this.deviceId=deviceId;
	}
	
	
	

	public Device(String devicename, int amount, int price,  String status,int roomId,String note) {
		super();
		this.devicename = devicename;
		this.amount = amount;
		this.roomId = roomId;
		this.price = price;
		this.note = note;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getName() {
		return devicename;
	}

	public void setName(String devicename) {
		this.devicename = devicename;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
    
	@Override
    public String toString() {
        return "Device{" +
                "deviceId=" + deviceId +
                ", name='" + devicename + '\'' +
                ", amount=" + amount +    
                ", price=" + price +
                ", status=" + status +
                ", roomId=" + roomId +
                ", note='" + note + '\'' +
                '}';
    }

	@Override
	public Device[] searchALL() {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Device[] searchByName() {
		// TODO Auto-generated method stub
		return null;
	}
	public int insert() {
		int a = DeviceDAO.getInstance().insert(this);
		return a;
		}
	public int update() {
		int a = DeviceDAO.getInstance().update(this);
		return a;
	}
	public int delete() {
		int a = DeviceDAO.getInstance().delete(this);
		return a;
	}
	public ArrayList<Device> searchByStatus(String t) {
		ArrayList<Device> a = DeviceDAO.getInstance().selectByStatus(t);
		return a;
	}
	public ArrayList<Device> searchAllDevice() {
		ArrayList<Device> a = DeviceDAO.getInstance().selectAll();
		return a;
	}
	public Device searchDeviceByName(String t) {
		Device a = DeviceDAO.getInstance().selectByName(t);
		return a;
	}
	public Device searchDeviceByID(int t) {
		Device a = DeviceDAO.getInstance().selectByID(t);
		return a ;
	}
	
}
