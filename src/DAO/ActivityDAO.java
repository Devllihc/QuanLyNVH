package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Activity;


public class ActivityDAO  {
	public static ActivityDAO getInstance() {
		return new ActivityDAO();
	}


	public int insert(Activity t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua = 0;
		String sql= "INSERT INTO activity (activityid, activityname,roomid, timestart, timefinish, note)"
				+ "VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setInt(1, t.getActivityid());
			pst.setString(2, t.getActivityname());
			pst.setInt(3, t.getRoomid());
			pst.setTimestamp(4, t.getTimestart());
			pst.setTimestamp(5, t.getTimefinish());
			pst.setString(6, t.getNote());
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


	public int update(Activity t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "UPDATE activity "
				+" SET activityname=?,roomid=?, timestart=?, timefinish=?, note = ?"
				+" WHERE activityid=?";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(sql);			
			pst.setString(1, t.getActivityname());
			pst.setInt(2, t.getRoomid());
			pst.setTimestamp(3, t.getTimestart());
			pst.setTimestamp(4, t.getTimefinish());
			pst.setString(5, t.getNote());
			pst.setInt(6, t.getActivityid());
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


	public int delete(Activity t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "DELETE from activity "
				+" WHERE activityid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t.getActivityid());
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


	public ArrayList<Activity> selectAll() {
		ArrayList<Activity> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM activity ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				String activityname = rs.getString("activityname");
				int roomid = rs.getInt("roomid");
				Timestamp timestart = rs.getTimestamp("timestart");
				Timestamp timefinish = rs.getTimestamp("timefinish");
				String note = rs.getString("note");
				Activity a = new Activity(activityid,activityname,roomid,timestart,timefinish,note);
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


	public Activity selectByID(int t) {
		Activity ketQua = null;
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql= "SELECT * FROM activity WHERE activityid =? ";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t);
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				String activityname = rs.getString("activityname");
				int roomid = rs.getInt("roomid");
				Timestamp timestart = rs.getTimestamp("timestart");
				Timestamp timefinish = rs.getTimestamp("timefinish");
				String note = rs.getString("note");
				Activity a = new Activity(activityid,activityname,roomid,timestart,timefinish,note);
				ketQua = a;
			}
			
			
			JDBCUtil.CloseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
		
	}


	public Activity selectByName(Activity t) {
		Activity ketQua = null;
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql= "SELECT * FROM activity WHERE activityname =? ";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t.getActivityname());
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				String activityname = rs.getString("activityname");
				int roomid = rs.getInt("roomid");
				Timestamp timestart = rs.getTimestamp("timestart");
				Timestamp timefinish = rs.getTimestamp("timefinish");
				String note = rs.getString("note");
				ketQua = new Activity(activityid,activityname,roomid,timestart,timefinish,note);
			}
			
			JDBCUtil.CloseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
		
	}
	public ArrayList<Timestamp> CheckFreeActivity(int t, Timestamp fromtime, Timestamp totime){
		ArrayList<Timestamp> from = new ArrayList<>();
		ArrayList<Timestamp> to = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		Timestamp timebegin = fromtime;
		Timestamp timeend = totime;
		String sql1= "SELECT timestart FROM activity WHERE timestart between ? and ? and timefinish between ? and ? and roomid = ? ORDER BY timestart asc ";
		
		String sql2= "SELECT timefinish FROM activity WHERE timestart between ? and ? and timefinish between ? and ? and roomid = ? ORDER BY timefinish asc ";

		String sql3= "SELECT timefinish FROM activity WHERE ? between timestart and timefinish and roomid = ?";
	
		String sql4= "SELECT timestart FROM activity WHERE ? between timestart and timefinish and roomid = ?";
		
		try {
			PreparedStatement pst3 = connection.prepareStatement(sql3);
			pst3.setTimestamp(1, fromtime);
			pst3.setInt(2, t);
			System.out.println(sql3);
			ResultSet rs3 =pst3.executeQuery();
			if (rs3.next()) {
			    timebegin = rs3.getTimestamp("timefinish");
			}
			PreparedStatement pst4 = connection.prepareStatement(sql4);
			pst4.setTimestamp(1,totime);
			pst4.setInt(2, t);
			System.out.println(sql4);
			ResultSet rs4 =pst4.executeQuery();
			if( rs4.next()) { 
				timeend = rs4.getTimestamp("timestart");
			}
			from.add(timebegin);
			
			PreparedStatement pst1 = connection.prepareStatement(sql1);
			pst1.setTimestamp(1,fromtime);
			pst1.setTimestamp(2, totime);
			pst1.setTimestamp(3,fromtime);
			pst1.setTimestamp(4, totime);
			pst1.setInt(5, t);
			System.out.println(sql1);
			ResultSet rs1 =pst1.executeQuery();
			while (rs1.next()) {
				to.add(rs1.getTimestamp("timestart"));
			}
			PreparedStatement pst2 = connection.prepareStatement(sql2);
			pst2.setTimestamp(1, fromtime);
			pst2.setTimestamp(2, totime);
			pst2.setTimestamp(3, fromtime);
			pst2.setTimestamp(4, totime);
			pst2.setInt(5, t);
			System.out.println(sql2);
			ResultSet rs2 =pst2.executeQuery();
			while (rs2.next()) {
				from.add(rs2.getTimestamp("timefinish"));
			}
			to.add(timeend);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Timestamp> ketQua = new ArrayList<>();
		for(int i=0 ; i< to.size(); i++ ) {
			ketQua.add(from.get(i));
			ketQua.add(to.get(i));
			System.out.println("from "+from.get(i)+ "to "+ to.get(i));
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}
	public ArrayList<Activity> searchOffDate() {
		ArrayList<Activity> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM activity WHERE timefinish < NOW() ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				String activityname = rs.getString("activityname");
				int roomid = rs.getInt("roomid");
				Timestamp timestart = rs.getTimestamp("timestart");
				Timestamp timefinish = rs.getTimestamp("timefinish");
				String note = rs.getString("note");
				Activity a = new Activity(activityid,activityname,roomid,timestart,timefinish,note);
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
	public Activity SearchNearOffDate() {
		Activity ketQua = new Activity();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM activity WHERE timefinish > NOW() ORDER BY timefinish DESC ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				String activityname = rs.getString("activityname");
				int roomid = rs.getInt("roomid");
				Timestamp timestart = rs.getTimestamp("timestart");
				Timestamp timefinish = rs.getTimestamp("timefinish");
				String note = rs.getString("note");
				Activity a = new Activity(activityid,activityname,roomid,timestart,timefinish,note);
				ketQua = a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}
	public int SearchID(Activity t ) {
		int ketQua = 0;
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql= "SELECT activityid FROM activity WHERE activityname =? and timestart=? ";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t.getActivityname());
			pst.setString(2,t.getTimestart().toString());
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				ketQua=activityid;
			}
			JDBCUtil.CloseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	
}
