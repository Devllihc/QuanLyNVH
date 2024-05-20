package model;



public class Renter implements InterfaceSearch<Renter> {
		private String renterid;
	    private String name;
	    private String sdt;
	    private String address;
	    private String note;
	    
	    
		public Renter(String renterid, String name, String sdt, String address, String note) {
			super();
			this.renterid = renterid;
			this.name = name;
			this.sdt = sdt;
			this.address = address;
			this.note = note;
		}
		

		public Renter() {
			super();
			// TODO Auto-generated constructor stub
		}


		public String getId() {
			return renterid;
		}
		public void setId(String renterid) {
			this.renterid = renterid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSdt() {
			return sdt;
		}
		public void setSdt(String sdt) {
			this.sdt = sdt;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = note;
		}
		@Override
		public Renter[] searchALL() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Renter[] searchByName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String toString() {
			return "Renter [id=" + renterid + ", name=" + name + ", sdt=" + sdt + ", address=" + address
					+ ", note=" + note + "]";
		}
	   
}
