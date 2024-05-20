package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.JDBCUtil;
import model.ManagerAccount;
import model.Request;

public class ManagerAccountDAO implements DAOInterface<ManagerAccount> {
	public static ManagerAccountDAO getInstance() {
		return new ManagerAccountDAO();
	}
	
	
	

	@Override
	public int insert(ManagerAccount t) {
		Connection connection = JDBCUtil.getConnection();
		String sql= "INSERT INTO manageraccount (userid,accountname,password,secques,answer)"
				+ "VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1,t.getUserId());
			pst.setString(2, t.getAccountName());
			pst.setString(3, t.getPassword());
			pst.setString(4, t.getSecurityQuestion());
			pst.setString(5,t.getAnswer());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ManagerAccount t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "UPDATE manageraccount SET accountname=?, password=?, secques=?, answer=?"
				+ " WHERE userid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(5,t.getUserId());
			pst.setString(1, t.getAccountName());
			pst.setString(2, t.getPassword());
			pst.setString(3, t.getSecurityQuestion());
			pst.setString(4,t.getAnswer());
			ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketqua;
	}

	@Override
	public int delete(ManagerAccount t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "DELETE from manageraccount "
				+" WHERE userid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t.getUserId());
			ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketqua;
	}

	@Override
	public ArrayList<ManagerAccount> selectAll() {
		ArrayList<ManagerAccount> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM manageraccount ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				String userid = rs.getString("userid");
				String accountname = rs.getString("accountname");
				String password = rs.getString("password");
				String securityQuestion = rs.getString("secques");
				String answer = rs.getString("answer");
				ManagerAccount a = new ManagerAccount(userid,accountname,password,securityQuestion,answer);
				ketQua.add(a);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}

	@Override
	public ManagerAccount selectByID(String t) {
		ManagerAccount tt = new ManagerAccount();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM manageraccount WHERE userid =? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs = connection.createStatement().executeQuery(sql);
			String userid = rs.getString("userid");
			String accountname = rs.getString("accountname");
			String password = rs.getString("password");
			String securityQuestion = rs.getString("secques");
			String answer = rs.getString("answer");
			ManagerAccount a = new ManagerAccount(userid,accountname,password,securityQuestion,answer);
			tt=a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCUtil.CloseConnection(connection);
		return tt;
	}

	@Override
	public ManagerAccount selectByName(String t) {
		ManagerAccount tt = new ManagerAccount();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM manageraccount WHERE accountname = ? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
			String userid = rs.getString("userid");
			String accountname = rs.getString("accountname");
			String password = rs.getString("password");
			String securityQuestion = rs.getString("secques");
			String answer = rs.getString("answer");
			ManagerAccount a = new ManagerAccount(userid,accountname,password,securityQuestion,answer);
			tt=a;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCUtil.CloseConnection(connection);
		return tt;
	}
	public int forgetPasswork(String accountname,String answer,String newpassword) {
		ArrayList<ManagerAccount> a = this.selectAll();
		for (int i=0; i< a.size();i++) {
			if(a.get(i).getAnswer().equals(answer)) {
				ManagerAccount tt =this.selectByName(accountname);
				tt.setPassword(newpassword);
				ManagerAccountDAO.getInstance().update(tt);
				return 1;
			}
		}
		return 0;}
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
	public ArrayList<Request> searchRequest(){
		ArrayList<Request> a=RequestDAO.getInstance().selectAll();
		return a;
	}
}
