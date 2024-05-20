package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Device;

public class DeviceDAO implements DAOInterface<Device> {
	public static DeviceDAO getInstance() {
		return new DeviceDAO();
	}

	@Override
	public int insert(Device t) {
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "INSERT INTO device (devicename,amount,price,status,roomid,note) "
				+ "VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, t.getName());
			pst.setInt(2, t.getAmount());
			pst.setInt(3, t.getPrice());
			pst.setString(4, t.getStatus());
			pst.setInt(5, t.getRoomId());
			pst.setString(6, t.getNote());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Device t) {
		Connection connection = JDBCUtil.getConnection();
		String sql= "UPDATE device"
				+ " SET devicename= ?, amount= ?, price= ?, status= ?, roomid= ?, note= ? "
				+ "WHERE deviceid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, t.getName());
			pst.setInt(2, t.getAmount());
			pst.setInt(3, t.getPrice());
			pst.setString(4, t.getStatus());
			pst.setInt(5, t.getRoomId());
			pst.setString(6, t.getNote());
			pst.setInt(7, t.getDeviceId());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return 0;
	}
	public Device searchByNameStatus(String n,String t) {
		Device aa = new Device() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM device where devicename =? and status =? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,n);
			pst.setString(2,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String devicename = rs.getString("devicename");
				int amount = rs.getInt("amount");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomId");
				int deviceid = rs.getInt("deviceid");
				Device a = new Device(devicename, amount, price, status, roomId, note, deviceid);
				aa= a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return aa;
	}

	@Override
	public int delete(Device t) {
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "DELETE from device "
				+" WHERE devicename= ?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t.getName());
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
	public ArrayList<Device> selectAll() {
		ArrayList<Device> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM device ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				String devicename = rs.getString("devicename");
				int amount = rs.getInt("amount");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomId");
				int deviceid = rs.getInt("deviceid");
				Device a = new Device(devicename, amount, price, status, roomId, note, deviceid);
				ketQua.add(a);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketQua;
	}

	public Device selectByID(int t) {
		Device aa = new Device() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM device where deviceid= ? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String devicename = rs.getString("devicename");
				int amount = rs.getInt("amount");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomId");
				int deviceid = rs.getInt("deviceid");
				Device a = new Device(devicename, amount, price, status, roomId, note, deviceid);
				aa= a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return aa;
	}

	@Override
	public Device selectByName(String t) {
		Device aa = new Device() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM device where devicename = ?  ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String devicename = rs.getString("devicename");
				int amount = rs.getInt("amount");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomId");
				int deviceid = rs.getInt("deviceid");
				Device a = new Device(devicename, amount, price, status, roomId, note, deviceid);
				aa= a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return aa;
		
	}

	@Override
	public Device selectByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Device> selectByStatus(String t) {
		ArrayList<Device> aa = new ArrayList<>() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM device where status = ? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String devicename = rs.getString("devicename");
				int amount = rs.getInt("amount");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomId");
				int deviceid = rs.getInt("deviceid");
				Device a = new Device(devicename, amount, price, status, roomId, note, deviceid);
				aa.add(a);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return aa;
		
	}
	public boolean checkexistByName(String t) {
		boolean aa = false;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM device where devicename = ? and status ='ok' ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			if (rs.next()) {
				aa = true;		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return aa;
		}
	public int totalDeviceInUse(int deviceid, Timestamp timebegin, Timestamp timeend) {
	int total = 0;
	try {
		Connection connection = JDBCUtil.getConnection();
		String sql1 = "SELECT amount FROM deviceactivity,activity WHERE ? between timestart and timefinish and ? between timestart and timefinish and deviceid = ? and activity.activityid=deviceactivity.activityid  ";
		String sql2 = "SELECT amount FROM deviceactivity,activity WHERE (timestart between ? and ? or timefinish between ? and ? ) and deviceid = ? and activity.activityid=deviceactivity.activityid" ;
		String sql3 = "SELECT amount FROM devicerent,rent WHERE ? between timestart and timefinish and ? between timestart and timefinish and deviceid = ? and rent.rentid=devicerent.rentid  ";
		String sql4 = "SELECT amount FROM devicerent,rent WHERE (timestart between ? and ? or timefinish between ? and ? ) and deviceid = ? and rent.rentid=devicerent.rentid" ;
		PreparedStatement pst1 = connection.prepareStatement(sql1);
		pst1.setTimestamp(1,timebegin);
		pst1.setTimestamp(2,timeend);
		pst1.setInt(3,deviceid);
		System.out.println(sql1);
		PreparedStatement pst2 = connection.prepareStatement(sql2);
		pst2.setTimestamp(1,timebegin);
		pst2.setTimestamp(2,timeend);
		pst2.setTimestamp(3,timebegin);
		pst2.setTimestamp(4,timeend);
		pst2.setInt(5,deviceid);
		System.out.println(sql2);
		ResultSet rs1 =pst1.executeQuery();
		while(rs1.next()) {
			int amount = rs1.getInt("amount");
			total += amount;
		}
		ResultSet rs2 =pst2.executeQuery();
		while (rs2.next()) {
			int amount = rs2.getInt("amount");
			total += amount;				
		}
		PreparedStatement pst3 = connection.prepareStatement(sql3);
		pst3.setTimestamp(1,timebegin);
		pst3.setTimestamp(2,timeend);
		pst3.setInt(3,deviceid);
		System.out.println(sql3);
		PreparedStatement pst4 = connection.prepareStatement(sql4);
		pst4.setTimestamp(1,timebegin);
		pst4.setTimestamp(2,timeend);
		pst4.setTimestamp(3,timebegin);
		pst4.setTimestamp(4,timeend);
		pst4.setInt(5,deviceid);
		System.out.println(sql4);
		ResultSet rs3 =pst3.executeQuery();
		while(rs3.next()) {
			int amount = rs3.getInt("amount");
			total += amount;
		}
		ResultSet rs4 =pst4.executeQuery();
		while (rs4.next()) {
			int amount = rs4.getInt("amount");
			total += amount;				
		}
		
		JDBCUtil.CloseConnection(connection);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return total;
	}
	public Device selectByNameUse(String t) {
		Device aa = new Device() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM device where devicename = ? and status='ok' ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String devicename = rs.getString("devicename");
				int amount = rs.getInt("amount");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomId");
				int deviceid = rs.getInt("deviceid");
				Device a = new Device(devicename, amount, price, status, roomId, note, deviceid);
				aa= a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return aa;
		
	}
}
