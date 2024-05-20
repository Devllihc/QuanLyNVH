package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Activity;
import model.DeviceActivity;
import model.DeviceRent;

public class DeviceRentDAO implements DAOInterface<DeviceRent> {
	public static DeviceRentDAO getInstance() {
		return new DeviceRentDAO();
	}

	@Override
	public int insert(DeviceRent t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua = 0;
		String sql= "INSERT INTO devicerent (rentid, deviceid, amount)"
				+ "VALUES(?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setInt(1, t.getRentid());
			pst.setInt(2, t.getDeviceid());
			pst.setInt(3, t.getAmount());
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
	public int update(DeviceRent t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "UPDATE devicerent "
				+" SET amount =?"
				+" WHERE rentid=? and deviceid=?";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(sql);			
			pst.setInt(1, t.getAmount());
			pst.setInt(2, t.getRentid());
			pst.setInt(3, t.getDeviceid());
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
	public int delete(DeviceRent t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "DELETE from devicerent "
				+" WHERE rentid=? and deviceid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t.getRentid());
			pst.setInt(2,t.getDeviceid());
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
	public ArrayList<DeviceRent> selectAll() {
		ArrayList<DeviceRent> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM devicerent ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				int rentid = rs.getInt("rentid");
				int deviceid = rs.getInt("deviceid");
				int amount = rs.getInt("amount");
				DeviceRent a = new DeviceRent(rentid,deviceid,amount);
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
	public DeviceRent selectByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public DeviceRent selectByName(String t) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	public DeviceRent selectByID(int t1, int t2) {
		DeviceRent ketQua = null;
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql= "SELECT * FROM devicerent WHERE rentid =? and deviceid=? ";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t1);
			pst.setInt(1,t2);
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int rentid = rs.getInt("rentid");
				int deviceid = rs.getInt("deviceid");
				int amount = rs.getInt("amount");
				DeviceRent a = new DeviceRent(rentid,deviceid,amount);
				ketQua = a;
			}
			
			
			JDBCUtil.CloseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<DeviceRent> selectByRentId(int t) {
		ArrayList<DeviceRent> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM devicerent, device where device.deviceid=devicerent.deviceid and rentid = ?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t);
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int activityid = rs.getInt("rentid");
				String devicename=rs.getString("devicename");
				int deviceid = rs.getInt("device.deviceid");
				int amount = rs.getInt("amount");
				DeviceRent a = new DeviceRent(activityid,deviceid,amount,devicename);
				ketQua.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}

	public int deleteByRentId(int t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "DELETE from devicerent "
				+" WHERE rentid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t);
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
}
