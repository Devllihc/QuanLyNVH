package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Person;

public class LocalPersonDAO implements DAOInterface<Person> {
	public static LocalPersonDAO getInstance() {
		return new LocalPersonDAO();
	}

	@Override
	public int insert(Person t) {
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "INSERT INTO person (id,name,hostid,sdt,address,status,note)"
				+ "VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1,t.getId());
			pst.setString(2, t.getName());
			pst.setString(3, t.getHostId());
			pst.setString(4, t.getSdt());
			pst.setString(5, t.getAddress());
			pst.setString(6, t.getStatus());
			pst.setString(7, t.getNote());
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
	public int update(Person t) {
		int ketqua=0;
		Connection connection = JDBCUtil.getConnection();
		String sql= "UPDATE person Set name=?, hostid=?, sdt=?, address=? ,status=?, note=? WHERE id=?"
				;
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(7,t.getId());
			pst.setString(1, t.getName());
			pst.setString(2, t.getHostId());
			pst.setString(3, t.getSdt());
			pst.setString(4, t.getAddress());
			pst.setString(5, t.getStatus());
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

	@Override
	public int delete(Person t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "DELETE from person "
				+" WHERE id=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t.getId());
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
	public ArrayList<Person> selectAll() {
		ArrayList<Person> ketqua= new ArrayList<Person>() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM person";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs =pst.executeQuery();
			while(rs.next()) {
			String id= rs.getString("id");
			String name= rs.getString("name");
			String hostid= rs.getString("hostid");
			String sdt= rs.getString("sdt");
			String address= rs.getString("address");
			String status= rs.getString("status");
			String note= rs.getString("note");
			Person a=new Person(id, name, hostid, sdt, status, address, note);
			ketqua.add(a);
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketqua;
	}
		public ArrayList<Person> selectHostId() {
			ArrayList<Person> ketqua= new ArrayList<Person>() ;
			Connection connection = JDBCUtil.getConnection();
			String sql= "SELECT * FROM person where userid=hostid";
			System.out.println(sql);
			try {
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rs =pst.executeQuery();
				while(rs.next()) {
				String id= rs.getString("id");
				String name= rs.getString("name");
				String hostid= rs.getString("hostid");
				String sdt= rs.getString("sdt");
				String address= rs.getString("address");
				String status= rs.getString("status");
				String note= rs.getString("note");
				Person a=new Person(id, name, hostid, sdt, status, address, note);
				ketqua.add(a);
				}
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketqua;
	}

	@Override
	public Person selectByID(String t) {
		Person tt = new Person();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM person WHERE name =? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			
			String id= rs.getString("id");
			String name= rs.getString("name");
			String hostid= rs.getString("hostid");
			String sdt= rs.getString("sdt");
			String address= rs.getString("address");
			String status= rs.getString("status");
			String note= rs.getString("note");
			Person a=new Person(id, name, hostid, sdt, status, address, note);
			tt=a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCUtil.CloseConnection(connection);
		return tt;
	}
	public ArrayList<Person> selectListByName(String t) {
		ArrayList<Person> ketqua= new ArrayList<Person>() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM person WHERE id =?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while(rs.next()) {
			String id= rs.getString("id");
			String name= rs.getString("name");
			String hostid= rs.getString("hostid");
			String sdt= rs.getString("sdt");
			String address= rs.getString("address");
			String status= rs.getString("status");
			String note= rs.getString("note");
			Person a=new Person(id, name, hostid, sdt, status, address, note);
			ketqua.add(a);
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketqua;
	}
	public ArrayList<Person> selectListByHostid(String t) {
		ArrayList<Person> ketqua= new ArrayList<Person>() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM person WHERE hostid =?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while(rs.next()) {
			String id= rs.getString("id");
			String name= rs.getString("name");
			String hostid= rs.getString("hostid");
			String sdt= rs.getString("sdt");
			String address= rs.getString("address");
			String status= rs.getString("status");
			String note= rs.getString("note");
			Person a=new Person(id, name, hostid, sdt, status, address, note);
			ketqua.add(a);
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketqua;
	}

	@Override
	public Person selectByName(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
