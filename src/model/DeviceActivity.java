package model;

public class DeviceActivity {
	private int activityid;
	private int deviceid;
	private int amount;
	private String name;
	public DeviceActivity(int activityid, int deviceid, int amount, String name) {
		super();
		this.activityid = activityid;
		this.deviceid = deviceid;
		this.amount = amount;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public DeviceActivity(int deviceid, int amount, String name) {
		super();
		this.deviceid = deviceid;
		this.amount = amount;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DeviceActivity(int activityid, int deviceid, int amount) {
		super();
		this.activityid = activityid;
		this.deviceid = deviceid;
		this.amount = amount;
	}
	
	public DeviceActivity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getActivityid() {
		return activityid;
	}
	public void setActivityid(int activityid) {
		this.activityid = activityid;
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
		return "DeviceActivity [activityid=" + activityid + ", deviceid=" + deviceid + ", amount=" + amount + ", name="
				+ name + "]";
	}
		
}
