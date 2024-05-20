package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import DAO.AccountDAO;
import DAO.LocalPersonDAO;
import DAO.ManagerAccountDAO;
import DAO.RequestDAO;
import DAO.WorkDAO;

public class ManagerAccount {
	private String userId;
    private String accountName;
    private String password;
    private String securityQuestion;
    private String answer;
    
	public ManagerAccount() {
		super();
	}

	public ManagerAccount(String accountName, String password, String securityQuestion, String answer) {
		super();
		this.accountName = accountName;
		this.password = password;
		this.securityQuestion = securityQuestion;
		this.answer = answer;
	}
	

	public ManagerAccount(String accountName, String password) {
		super();
		this.accountName = accountName;
		this.password = password;
	}

	public ManagerAccount(String userId, String accountName, String password, String securityQuestion, String answer) {
		super();
		this.userId = userId;
		this.accountName = accountName;
		this.password = password;
		this.securityQuestion = securityQuestion;
		this.answer = answer;
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

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "ManagerAccount [userId=" + userId + ", accountName=" + accountName + ", password=" + password
				+ ", securityQuestion=" + securityQuestion + ", answer=" + answer + "]";
	}
	
	
	
	// check tài khoản, mật khẩu của tài khoản quản lí
	public boolean checkManagerAccount(){
		ArrayList<ManagerAccount> accounts = new ArrayList<ManagerAccount>();
		accounts = ManagerAccountDAO.getInstance().selectAll();
		for (int i = 0; i < accounts.size(); i++) {
			if (this.getAccountName().equals(accounts.get(i).getAccountName()) &&
		            this.getPassword().equals( accounts.get(i).getPassword())) {
				this.setUserId(accounts.get(i).getUserId());
				return true;
			}
		}
		return false;
	}
	public boolean checkManagerAccountName(){
		ArrayList<ManagerAccount> accounts = new ArrayList<ManagerAccount>();
		accounts = ManagerAccountDAO.getInstance().selectAll();
		for (int i = 0; i < accounts.size(); i++) {
			if (this.getAccountName().equals(accounts.get(i).getAccountName())||this.userId.equals(accounts.get(i).getUserId())) {
				return true;
			}
		}
		return false;
	}
	
	
	// đổi mật khẩu cho tài khoản quản lí
	public boolean changePassword(String newpassword){
		if(this.checkManagerAccount()) {
			this.setPassword(newpassword);
			ManagerAccountDAO.getInstance().update(this);
			return true;
		}
		return false;
	}
	
	// thống kê toàn bộ tài khoản quản lí

	public ArrayList<ManagerAccount> selectManagerAccountAll() {
		 ArrayList<ManagerAccount> a= ManagerAccountDAO.getInstance().selectAll();
		return a;
	}
	
	
	// thống kê các tài khoản quản lí bằng tên
	public ManagerAccount searchManagerAccountByName(String accountName) {
		ManagerAccount a = ManagerAccountDAO.getInstance().selectByName(accountName);
		return a;
	}
	
	// tìm các tài khoản thường bằng tên
	public Account searchAccountByName(String s) {
		Account t=AccountDAO.getInstance().selectByName(s);
		return t;
	}
	// tìm tài khoản bằng tên
	public Account searchAccountById(String s) {
		Account t=AccountDAO.getInstance().selectByID(s);
		return t;
	}
	
	
	//thống kê tất cả tài khoản thường
	public ArrayList<Account> selectAccountAll(){
		ArrayList<Account> a=AccountDAO.getInstance().selectAll();
		return a;	
	}
	
	//tạo tài khoản thường mới
	public static int makeNewAccount(Account t) {
		if(!t.checkAccountName()) {
		int tt=AccountDAO.getInstance().insert(t);
		return tt;
		}
		return 0;
	}
	
	
	//tạo tài khoản quản lí mới
	public static int makeNewManagerAccount(ManagerAccount t) {
		if(!t.checkManagerAccountName()) {
		ManagerAccountDAO.getInstance().insert(t);
		return 1;
		}
		return 0;
	}
	
	
	public ManagerAccount(String userId, String accountName, String password) {
		super();
		this.userId = userId;
		this.accountName = accountName;
		this.password = password;
	}

	// thống kê thời gian đăng nhập các tài khoản
	public ArrayList<Work> searchWorkALL() {
		 ArrayList<Work> a = WorkDAO.getInstance().selectAll();
		 return a;
	}
	
	
	
	//thống kê thời gian đăng nhập các tài khoản trong 1 khoảng thời gian
	public ArrayList<Work> searchWorkByTime(LocalDateTime t1,LocalDateTime t2){
		ArrayList<Work> a = WorkDAO.getInstance().selectAll();
		ArrayList<Work> b = new ArrayList<>();
		for (int i=0; i< a.size();i++) {
			if(a.get(i).getTimeloggin().compareTo(t1)>=0 && a.get(i).getTimeloggin().compareTo(t2)<=0) {
				b.add(a.get(i));
			}
		}
		return b;
	}
	
	
	//thống kê các người dân địa phương
	public ArrayList<Person> searchLocalPersonALL() {
		ArrayList<Person> p= LocalPersonDAO.getInstance().selectAll();
		return p;
	}
	
	
	
	// thêm 1 người dân địa phương
	public int addLocalPerson(Person t) {
		int ketqua = LocalPersonDAO.getInstance().insert(t);
		return ketqua;
	}
	
	
	// thay đổi thông tin 1 người dân địa phương
	public int updateLocalPerson(Person t) {
		int ketqua = LocalPersonDAO.getInstance().update(t);
		return ketqua;
	}
	
	// thống kê các người dân địa phương theo id chủ nhà
	public ArrayList<Person> searchHouseholdByHostID(String t){
		ArrayList<Person> p= LocalPersonDAO.getInstance().selectListByHostid(t);
		return p;
	}
	
	
	// thông kê dân theo tên
	public ArrayList<Person> searchPersonsByName(String t){
		ArrayList<Person> p= LocalPersonDAO.getInstance().selectListByName(t);
		return p;
	}
	// thống kê các chủ gia đình
	public ArrayList<Person> searchHostID(){
		ArrayList<Person> p= LocalPersonDAO.getInstance().selectHostId();
		return p;
	}
	
	
	// // thông kê dân theo id
	public Person searchPersonsByID(String t){
		Person p= LocalPersonDAO.getInstance().selectByID(t);
		return p;
	}
	
	// xóa tài khoản thường
	public int deleteAccount(Account t ) {
		int ketqua=AccountDAO.getInstance().delete(t);	
		return ketqua;
				}
	
	// xóa tài khoản quản lí
	public int deleteManagerAccount(ManagerAccount t ) {
		int ketqua=ManagerAccountDAO.getInstance().delete(t);	
		return ketqua;
		}
	// lấy lại mật khẩu tài khoản quản lí
	public int forgetPassword(String accountname,String answer,String newpassword) {
		int ketqua = ManagerAccountDAO.getInstance().forgetPasswork(accountname, answer, newpassword);
		return ketqua;
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
	public int deleteLocalPerson(Person p) {
		int ketqua = LocalPersonDAO.getInstance().delete(p);
		return ketqua;
	}
}