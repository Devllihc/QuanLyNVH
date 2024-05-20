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

public class DeviceActivityDAO implements DAOInterface<DeviceActivity> {
	public static DeviceActivityDAO getInstance() {
		return new DeviceActivityDAO();
	}

	@Override
	public int insert(DeviceActivity t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua = 0;
		String sql= "INSERT INTO deviceactivity (activityid, deviceid, amount)"
				+ "VALUES(?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setInt(1, t.getActivityid());
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
	public int update(DeviceActivity t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "UPDATE deviceactivity "
				+" SET amount =?"
				+" WHERE activityid=? and deviceid=?";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(sql);			
			pst.setInt(1, t.getAmount());
			pst.setInt(2, t.getActivityid());
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
	public int delete(DeviceActivity t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "DELETE from deviceactivity "
				+" WHERE activityid=? and deviceid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t.getActivityid());
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
	
	public int deleteByActivityId(int t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "DELETE from deviceactivity "
				+" WHERE activityid=?";
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

	@Override
	public ArrayList<DeviceActivity> selectAll() {
		ArrayList<DeviceActivity> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM deviceactivity, device where device.deviceid=deviceactivity.deviceid ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				String devicename=rs.getString("devicename");

				int deviceid = rs.getInt("deviceid");
				int amount = rs.getInt("amount");
				DeviceActivity a = new DeviceActivity(activityid,deviceid,amount,devicename);
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
	public DeviceActivity selectByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public DeviceActivity selectByName(String t) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	public DeviceActivity selectByID(int t1, int t2) {
		DeviceActivity ketQua = null;
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql= "SELECT * FROM deviceactivity WHERE activityid =? and deviceid=? ";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t1);
			pst.setInt(1,t2);
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int activityid = rs.getInt("activityid");

				int deviceid = rs.getInt("deviceid");
				int amount = rs.getInt("amount");
				DeviceActivity a = new DeviceActivity(activityid,deviceid,amount);
				ketQua = a;
			}
			
			
			JDBCUtil.CloseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<DeviceActivity> selectByActivityId(int t) {
		ArrayList<DeviceActivity> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM deviceactivity, device where device.deviceid=deviceactivity.deviceid and activityid = ?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t);
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				String devicename=rs.getString("devicename");
				int deviceid = rs.getInt("device.deviceid");
				int amount = rs.getInt("amount");
				DeviceActivity a = new DeviceActivity(activityid,deviceid,amount,devicename);
				ketQua.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}
}
