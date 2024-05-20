package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Work;

public class WorkDAO {
	public static WorkDAO getInstance() {
			return new WorkDAO();
	}
	public ArrayList<Work> selectAll() {
		ArrayList<Work> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		String sql1= "SELECT account.userid,accountname,timeloggin FROM account, work where account.userid=work.userid ";
		String sql2= "SELECT manageraccount.userid,accountname,timeloggin FROM manageraccount, work where manageraccount.userid=work.userid ";
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql1);
			while (rs.next()) {
				String userid = rs.getString("account.userid");
				String accountname = rs.getString("accountname");
				LocalDateTime timeloggin = (LocalDateTime) rs.getObject("timeloggin");
				Work a= new Work(userid,accountname,timeloggin);
				ketQua.add(a);
			}
			ResultSet rs2 = connection.createStatement().executeQuery(sql2);
			while (rs2.next()) {
				String userid = rs2.getString("manageraccount.userid");
				String accountname = rs2.getString("accountname");
				LocalDateTime timeloggin = (LocalDateTime) rs2.getObject("timeloggin");
				Work a= new Work(userid,accountname,timeloggin);
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
	public ArrayList<Work> selectByTime(LocalDateTime t1, LocalDateTime t2){
		 ArrayList<Work> a=this.selectAll();
		 ArrayList<Work> b=new ArrayList<>();
		 for(int i=0;i<a.size();i++) {
			 if(a.get(i).getTimeloggin().compareTo(t1)>=0&&a.get(i).getTimeloggin().compareTo(t2)<=0) {
				 b.add(a.get(i));
			 }
		 }
		return b;
	}
	public int deleteByTime(LocalDateTime t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "DELETE from work "
				+" WHERE timeloggin<?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setObject(1, t);;
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
	public int insert(Work t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "INSERT INTO work(userid,timeloggin) "
				+" VALUES(?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, t.getUserid());
			pst.setObject(2, t.getTimeloggin());
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
