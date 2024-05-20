package model;

public class Person  {
	private String id;
    private String name;
    private String hostId;
    private String sdt;
    private String status;
    private String address;
    private String note;
    
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Person(String id, String name, String sdt, String address, String note) {
		super();
		this.id = id;
		this.name = name;
		this.sdt = sdt;
		this.address = address;
		this.note = note;
	}
	public Person(String id, String name, String hostId, String sdt, String status, String address, String note) {
		super();
		this.id = id;
		this.name = name;
		this.hostId = hostId;
		this.sdt = sdt;
		this.status = status;
		this.address = address;
		this.note = note;
	}
	public Person(String id, String name, String hostId, String sdt, String status, String address) {
		super();
		this.id = id;
		this.name = name;
		this.hostId = hostId;
		this.sdt = sdt;
		this.status = status;
		this.address = address;
	}
	public Person() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", hostId=" + hostId + ", sdt=" + sdt + ", status=" + status
				+ ", address=" + address + "]";
	}
	public Person(String id, String name, String sdt, String address) {
		super();
		this.id = id;
		this.name = name;
		this.sdt = sdt;
		this.address = address;
	}
    
   
}
