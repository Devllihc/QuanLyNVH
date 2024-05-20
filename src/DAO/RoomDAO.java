package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.JDBCUtil;

import model.Room;

public class RoomDAO implements DAOInterface<Room> {
	public static RoomDAO getInstance() {
		return new RoomDAO();
	}
	

	@Override
	public int insert(Room t) {
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "INSERT INTO room (name,capacity,price,status,note) "
				+ "VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, t.getName());
			pst.setInt(2, t.getCapacity());
			pst.setInt(3, t.getPrice());
			pst.setString(4, t.getStatus());
			pst.setString(5, t.getNote());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return 0;
	}

	@Override
	public int update(Room t) {
		Connection connection = JDBCUtil.getConnection();
		String sql= "UPDATE room"
				+ " SET name= ?, capacity= ?, price= ?, status= ?, note= ? "
				+ "WHERE roomid=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, t.getName());
			pst.setInt(2, t.getCapacity());
			pst.setInt(3, t.getPrice());
			pst.setString(4, t.getStatus());
			pst.setString(5, t.getNote());
			pst.setInt(6, t.getRoomId());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return 0;
	}

	@Override
	public int delete(Room t) {
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "DELETE from room "
				+" WHERE roomid= ?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t.getRoomId());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return 0;
	}

	@Override
	public ArrayList<Room> selectAll() {
		ArrayList<Room> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM room ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				int capacity = rs.getInt("capacity");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomid");
				Room a = new Room(roomId,name, capacity, price, status, note);
				ketQua.add(a);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}


	
	public Room selectByID(int t) {
		Room ketQua = new Room();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM room where roomid =  ?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int capacity = rs.getInt("capacity");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomid= rs.getInt("roomid");
		
				Room a = new Room(roomid, name, capacity, price, status, note);
				ketQua =a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}

	@Override
	public Room selectByName(String t) {
		Room ketQua = new Room();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM room where name = ? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int capacity = rs.getInt("capacity");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomid= rs.getInt("roomid");
		
				Room a = new Room(roomid, name, capacity, price, status, note);
				ketQua =a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}

	public ArrayList<Room> selectByStatus(String t) {
		ArrayList<Room> ketQua = new ArrayList<>() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM room where status = ?  ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int capacity = rs.getInt("capacity");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomid= rs.getInt("roomId");
				Room a = new Room(roomid, name,capacity, price, status, note);
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

		
	@Override
	public Room selectByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean findRoomByName(String t) {
		boolean ketQua = false;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM room where name = ? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			if (rs.next()) {
				ketQua = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}
	public boolean checkRoomFree(int roomID, Timestamp timebegin,Timestamp timeend) {
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql1= "SELECT * FROM activity WHERE ? between timestart and timefinish and ? between timestart and timefinish and roomid = ? ";
			String sql2 = "SELECT * FROM activity WHERE (timestart between ? and ? or timefinish between ? and ? ) and roomid = ?";
			String sql3= "SELECT * FROM rent WHERE ? between timestart and timefinish and ? between timestart and timefinish and roomid = ? ";
			String sql4 = "SELECT * FROM activity WHERE (timestart between ? and ? or timefinish between ? and ? ) and roomid = ?";
			PreparedStatement pst1 = connection.prepareStatement(sql1);
			pst1.setTimestamp(1,timebegin);
			pst1.setTimestamp(2,timeend);
			pst1.setInt(3,roomID);
			System.out.println(sql1);
			PreparedStatement pst2 = connection.prepareStatement(sql2);
			pst2.setTimestamp(1,timebegin);
			pst2.setTimestamp(2,timeend);
			pst2.setTimestamp(3,timebegin);
			pst2.setTimestamp(4,timeend);
			pst2.setInt(5,roomID);
			System.out.println(sql1);
			ResultSet rs1 =pst1.executeQuery();
			if (rs1.next()) {
				return false;				
			}
			ResultSet rs2 =pst2.executeQuery();
			if (rs2.next()) {
				return false;				
			}
			PreparedStatement pst3 = connection.prepareStatement(sql3);
			pst3.setTimestamp(1,timebegin);
			pst3.setTimestamp(2,timeend);
			pst3.setInt(3,roomID);
			System.out.println(sql3);
			PreparedStatement pst4 = connection.prepareStatement(sql4);
			pst4.setTimestamp(1,timebegin);
			pst4.setTimestamp(2,timeend);
			pst4.setTimestamp(3,timebegin);
			pst4.setTimestamp(4,timeend);
			pst4.setInt(5,roomID);
			System.out.println(sql1);
			ResultSet rs3 =pst3.executeQuery();
			if (rs3.next()) {
				return false;				
			}
			ResultSet rs4 =pst4.executeQuery();
			if (rs4.next()) {
				return false;				
			}
			JDBCUtil.CloseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
