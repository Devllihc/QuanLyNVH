package model;

public class DeviceRent {
	private int rentid;
	private int deviceid;
	private int amount;
	private String name;
	public DeviceRent(int deviceid, int amount, String name) {
		super();
		this.deviceid = deviceid;
		this.amount = amount;
		this.name = name;
	}

	public DeviceRent(int rentid, int deviceid, int amount, String name) {
		super();
		this.rentid = rentid;
		this.deviceid = deviceid;
		this.amount = amount;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DeviceRent(int rentid, int deviceid, int amount) {
		super();
		this.rentid = rentid;
		this.deviceid = deviceid;
		this.amount = amount;
	}
	
	public DeviceRent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getRentid() {
		return rentid;
	}

	public void setRentid(int rentid) {
		this.rentid = rentid;
	}


	public int getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "DeviceActivityDAO [rentid=" + rentid + ", deviceid=" + deviceid + ", amount=" + amount + "]";
	}
		
}
