package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Account;


public class AccountDAO implements DAOInterface<Account> {
	public static AccountDAO getInstance() {
		return new AccountDAO();
	}

	@Override
	public int insert(Account t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "INSERT INTO account (userid,accountname,password,note)"
				+ "VALUES(?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1,t.getUserId());
			pst.setString(2, t.getAccountName());
			pst.setString(3, t.getPassword());
			pst.setString(4, t.getNote());
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
	public int update(Account t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "UPDATE account "
				+" SET accountname= ?, password=?, note=?"
				+" WHERE userid=?";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(4,t.getUserId());
			pst.setString(1, t.getAccountName());
			pst.setString(2, t.getPassword());
			pst.setString(3,t.getNote());
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
	public int delete(Account t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "DELETE from account "
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
	public ArrayList<Account> selectAll() {
		ArrayList<Account> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM account ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				String userid = rs.getString("userid");
				String accountname = rs.getString("accountname");
				String password = rs.getString("password");
				String note = rs.getString("note");
				Account a = new Account(userid,accountname,password,note);
				ketQua.add(a);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
		// TODO Auto-generated method stub
	}

	@Override
	public Account selectByID(String t) {
		Account tt = new Account();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM account WHERE userid =? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			String userid = rs.getString("userid");
			String accountname = rs.getString("accountname");
			String password = rs.getString("password");
			String note = rs.getString("note");
			Account a = new Account(userid,accountname,password,note);
			tt=a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCUtil.CloseConnection(connection);
		return tt;
	}

	@Override
	public Account selectByName(String t) {
		Account tt = new Account();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM account WHERE accountname =? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while(rs.next()) {
			String userid = rs.getString("userid");
			String accountname = rs.getString("accountname");
			String password = rs.getString("password");
			String note = rs.getString("note");
			Account a = new Account(userid,accountname,password,note);
			tt=a;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCUtil.CloseConnection(connection);
		return tt;
	}
}
