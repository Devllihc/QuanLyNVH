package model;


import java.util.ArrayList;

import DAO.AccountDAO;
import DAO.RequestDAO;


public class Account {
	private String userId;
    private String accountName;
    private String password;
    private String note;
    
	public Account(String userId, String accountName, String password, String note) {
		super();
		this.userId = userId;
		this.accountName = accountName;
		this.password = password;
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Account(String userId, String accountName, String password) {
		super();
		this.userId = userId;
		this.accountName = accountName;
		this.password = password;
	}
	
	public Account(String accountName, String password) {
		super();
		this.accountName = accountName;
		this.password = password;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Account [userId=" + userId + ", accountName=" + accountName + ", password=" + password + ", note="
				+ note + "]";
	}
	
	
	// kiểm tra tài khoản mật khẩu để đăng nhập
	public boolean checkAccount() {
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = AccountDAO.getInstance().selectAll();
		for (int i = 0; i < accounts.size(); i++) {
			if (this.accountName.equals(accounts.get(i).getAccountName()) &&
		            this.password.equals( accounts.get(i).getPassword())) {
				this.setUserId(accounts.get(i).getUserId());
				return true;
			}
		}
		return false;
	}
	public boolean checkAccountName() {
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = AccountDAO.getInstance().selectAll();
		for (int i = 0; i < accounts.size(); i++) {
			if (this.accountName.equals(accounts.get(i).getAccountName())||this.userId.equals(accounts.get(i).getUserId())) {
				return true;
			}
		}
		return false;
	}
	// đổi mật khẩu
	public boolean changePassword(String newpassword){
		if(this.checkAccount()) {
			this.setPassword(newpassword);
			AccountDAO.getInstance().update(this);
			return true;
		}
		return false;
	}
	// tạo yêu cầu
	public int setRequest(Request t) {
		ArrayList<Request> a= RequestDAO.getInstance().selectAll();
		for (int i=0;i<a.size();i++) {
			if(t.getHostid()==a.get(i).getHostid()&& t.getRequest()==a.get(i).getRequest()) {
				return 0;
			}
		}
		int kq=RequestDAO.getInstance().insert(t);
		return kq;
	}
	
	// thống kê các yêu cầu
	public ArrayList<Request> searchRequest(){
		ArrayList<Request> a=RequestDAO.getInstance().selectAll();
		return a;
	}
	
	// xóa yêu cầu
	public int deleteRequest ( Request t) {
		int kq=RequestDAO.getInstance().delete(t);
		return kq;
	}    
}
