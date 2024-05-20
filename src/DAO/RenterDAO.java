    package DAO;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import database.JDBCUtil;
	import model.Renter;

	public class RenterDAO implements DAOInterface<Renter> {
		public static RenterDAO getInstance() {
					return new RenterDAO();
		}

		@Override
		public int insert(Renter t) {
			Connection connection = JDBCUtil.getConnection();
			
			String sql= "INSERT INTO renter (id,name,sdt,address,note) "
					+ "VALUES(?,?,?,?,?)";
			try {
				PreparedStatement pst = connection.prepareStatement(sql);
				
				pst.setString(1,t.getId());
				pst.setString(2, t.getName());
				pst.setString(3, t.getSdt());
				pst.setString(4, t.getAddress());
				pst.setString(5, t.getNote());
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
		public int update(Renter t) {
			Connection connection = JDBCUtil.getConnection();
			
			String sql= "UPDATE renter"
					+" SET name= ?, sdt=?, address=?, note=?"
					+" WHERE id=?";
				
			try {
				PreparedStatement pst = connection.prepareStatement(sql);
				
				pst.setString(1, t.getName());
				pst.setString(2, t.getSdt());
				pst.setString(3, t.getAddress());
				pst.setString(4, t.getNote());
				pst.setString(5,t.getId());
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
		public int delete(Renter t) {
			Connection connection = JDBCUtil.getConnection();
			
			String sql= "DELETE from renter "
					+" WHERE id= ?";
			System.out.println(sql);
			try {
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setString(1,t.getId());
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
		
		public int deleteByID(String t) {
			Connection connection = JDBCUtil.getConnection();
			
			String sql= "DELETE from renter "
					+" WHERE id= ?";
			System.out.println(sql);
			try {
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setString(1,t);
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
		public ArrayList<Renter> selectAll() {
			ArrayList<Renter> ketQua = new ArrayList<>();
			Connection connection = JDBCUtil.getConnection();
			
			String sql= "SELECT * FROM renter ";
			System.out.println(sql);
			try {
				ResultSet rs = connection.createStatement().executeQuery(sql);
				while (rs.next()) {
					String renterid = rs.getString("id");
					String name = rs.getString("name");
					String sdt = rs.getString("sdt");
					String address = rs.getString("address");
					String note = rs.getString("note");
					Renter a = new Renter(renterid, name, sdt, address, note) ;
					ketQua.add(a);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JDBCUtil.CloseConnection(connection);
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Renter selectByID(String t) {
			Renter ketQua = new Renter();
			
			Connection connection = JDBCUtil.getConnection();
			
			String sql= "SELECT * FROM renter "
						+ "WHERE id = ?";
			System.out.println(sql);
			try {
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setString(1,t);
				ResultSet rs =pst.executeQuery();
				while (rs.next()) {
					String renterid = rs.getString("id");
					String name = rs.getString("name");
					String sdt = rs.getString("sdt");
					String address = rs.getString("address");
					String note = rs.getString("note");
					Renter a = new Renter(renterid, name,  sdt, address,  note) ;
					ketQua = a;
					
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
		public Renter selectByName(String t) {
			Renter ketQua = new Renter();
			Connection connection = JDBCUtil.getConnection();
			
			String sql= "SELECT * FROM renter "
						+ "WHERE name = ?";
			System.out.println(sql);
			try {
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setString(1,t);
				ResultSet rs =pst.executeQuery();
				while (rs.next()) {
					String renterid = rs.getString("id");
					String name = rs.getString("name");
					String sdt = rs.getString("sdt");
					String address = rs.getString("address");
					String note = rs.getString("note");
					Renter a = new Renter(renterid, name, sdt, address,  note) ;
					ketQua = a;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JDBCUtil.CloseConnection(connection);
			// TODO Auto-generated method stub
			return ketQua;
		}

		public boolean checkexistedrenter(Renter t) {
			boolean ketQua = false;
			
			Connection connection = JDBCUtil.getConnection();
			
			String sql= "SELECT * FROM renter "
						+ "WHERE id = ?";
			System.out.println(sql);
			try {
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setString(1,t.getId());
				ResultSet rs =pst.executeQuery();
				while (rs.next()) {
					ketQua = true;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JDBCUtil.CloseConnection(connection);
			// TODO Auto-generated method stub
			return ketQua;
		}
	}


