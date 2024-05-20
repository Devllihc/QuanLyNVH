package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Request;

public class RequestDAO implements DAOInterface<Request> {
	public static RequestDAO getInstance() {
		return new RequestDAO();
	}
	






	@Override
	public int insert(Request t) {
		Connection connection = JDBCUtil.getConnection();
		String sql = "INSERT INTO request (hostid,request,note,time)"
				+ "VALUES(?,?,?,?)";
		PreparedStatement pst;
		int ketqua=0;
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, t.getHostid());
			pst.setString(2, t.getRequest());
			pst.setString(3, t.getNote());
			pst.setObject(4, t.getTime());
			ketqua =pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JDBCUtil.CloseConnection(connection);
		return ketqua;
	}






	@Override
	public int update(Request t) {
		Connection connection = JDBCUtil.getConnection();
		String sql= "UPDATE request "
				+" SET hostid= ?, request=?, time =?, note=?"
				+" WHERE requestid=?";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1,t.getHostid());
			pst.setString(2, t.getRequest());
			pst.setObject(3, t.getTime());
			pst.setString(4,t.getNote());
			pst.setInt(5,t.getRequestid());
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
		// TODO Auto-generated method stub
	}






	@Override
	public int delete(Request t) {
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "DELETE from request "
				+" WHERE requestid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t.getRequestid());
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
	public ArrayList<Request> selectAll() {
		ArrayList<Request> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM request ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				String hostid = rs.getString("hostid");
				String request = rs.getString("request");
				LocalDateTime time = (LocalDateTime) rs.getObject("time");
				String note = rs.getString("note");
				int requestid = rs.getInt("requestid");
				Request a = new Request(hostid, request, time, note, requestid);
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
	public Request selectByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	public Request selectByID(int t){
		Request a = new Request();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM request WHERE t=requestid";
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			String hostid = rs.getString("hostid");
			String request = rs.getString("request");
			LocalDateTime time = (LocalDateTime) rs.getObject("time");
			String note = rs.getString("note");
			int requestid = rs.getInt("requestid");
			Request tt = new Request(hostid, request, time, note, requestid);
			a=tt;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return a;
	}






	@Override
	public Request selectByName(String t) {
		// TODO Auto-generated method stub
		return null;
	}
}
