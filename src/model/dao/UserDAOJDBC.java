package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.db.DB;
import model.entities.User;
import model.util.Alerts;

public class UserDAOJDBC implements DAO<User> {

	@Override
	public User findByID(Integer id) {
		Connection conn=null;
		PreparedStatement st = null;
		ResultSet rs = null;
		User obj=null;

		try {
			if(conn==null) {
				conn = DB.getConnection();
			}
			st = conn.prepareStatement(
				"SELECT * FROM Client WHERE ClientID = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			rs.next();
			//ID = ?, clientCPF =?, clientStatus =?, Name = ?, SuperUser = ?, BorrowedBookCount =?

				obj = new User();
				obj.setID(rs.getInt("ClientID"));
				obj.setName(rs.getString("Name"));
				obj.setStatus(rs.getBoolean("clientStatus"));
				obj.setCpf(rs.getString("clientCPF"));
				obj.setBorrowedBooksCount(rs.getInt("BorrowedBookCount"));
				obj.setSuperUser(rs.getBoolean("SuperUser"));
				obj.setPassword(rs.getString("clientPassword"));
				
				
				return obj;

		}catch(Exception e) {
			Alerts.showAlert("To quase caindo", e.getMessage(), AlertType.ERROR);
		}

		return obj;
		
	}

	public User findByCPF(String CPF) {
		Connection conn=null;
		PreparedStatement st = null;
		ResultSet rs = null;
		User obj=null;

		try {
			if(conn==null) {
				conn = DB.getConnection();
			}
			st = conn.prepareStatement(
				"SELECT * FROM Client WHERE clientCPF = ?");
			st.setString(1, CPF);
			rs = st.executeQuery();
			rs.next();
			//ID = ?, clientCPF =?, clientStatus =?, Name = ?, SuperUser = ?, BorrowedBookCount =?

				obj = new User();
				obj.setID(rs.getInt("ClientID"));
				obj.setName(rs.getString("Name"));
				obj.setStatus(rs.getBoolean("clientStatus"));
				obj.setCpf(rs.getString("clientCPF"));
				obj.setBorrowedBooksCount(rs.getInt("BorrowedBookCount"));
				obj.setSuperUser(rs.getBoolean("SuperUser"));
				obj.setPassword(rs.getString("clientPassword"));
			
				return obj;

		}catch(Exception e) {
			Alerts.showAlert("To quase caindo", e.getMessage(), AlertType.ERROR);
		}

		return obj;
		
	}	
	
	public void findByCPF(User user) {
		
	}
	
	@Override
	public List findAll() {
		Connection conn=null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();

		try {
			if(conn==null) {
				conn = DB.getConnection();
			}
			st = conn.prepareStatement(
				"SELECT * FROM Client ORDER BY ClientID");
			rs = st.executeQuery();

			//ID = ?, clientCPF =?, clientStatus =?, Name = ?, SuperUser = ?, BorrowedBookCount =?
			while (rs.next()) {
				User obj = new User();
				obj.setID(rs.getInt("ClientID"));
				obj.setName(rs.getString("Name"));
				obj.setStatus(rs.getBoolean("clientStatus"));
				obj.setCpf(rs.getString("clientCPF"));
				obj.setBorrowedBooksCount(rs.getInt("BorrowedBookCount"));
				obj.setSuperUser(rs.getBoolean("SuperUser"));
				obj.setPassword(rs.getString("clientPassword"));
				
				list.add(obj);
			}
			return list;
		}
		catch (Exception e) {
			Alerts.showAlert("Error in recovery data", e.getMessage(), AlertType.ERROR);
		}
		return list;
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		try {
			Connection conn;
			PreparedStatement preparedStatement;
			
			conn = DB.getConnection();
			
			preparedStatement = conn.prepareStatement("DELETE FROM Client WHERE ClientID = ?;");
		
			preparedStatement.setInt(1, id);
			
			int rowsAffected = preparedStatement.executeUpdate();
			
	
		}catch(Exception e) {
			Alerts.showAlert("ja to loko", e.getMessage(), AlertType.ERROR);
		}
		
		}

	@Override
	public int update(User user) {
		try {
			Connection conn;
			PreparedStatement preparedStatement;
			
			conn = DB.getConnection();
			
			preparedStatement = conn.prepareStatement("UPDATE Client"
					+ "SET ClientID = ?, clientCPF =?, clientStatus =?, Name = ?, SuperUser = ?, BorrowedBookCount =?, clientPassword = ?"
					+ "WHERE ClientID = ?;"
					) ;
			
			
			preparedStatement.setInt(1, user.getID());
			preparedStatement.setString(2, user.getCpf());
			preparedStatement.setBoolean(3, user.isStatus());
			preparedStatement.setString(4, user.getName());
			preparedStatement.setBoolean(5, user.isSuperUser());
			preparedStatement.setInt(6, user.getBorrowedBooksCount());
			preparedStatement.setString(7, user.getPassword());
			preparedStatement.setInt(8, user.getID());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
		
			//conn.close();
			//preparedStatement.close();
			resultSet.next();
			
		
			return resultSet.getInt(1);
		} catch(Exception e) {
			Alerts.showAlert("Error in UPDATE data", e.getMessage(), AlertType.ERROR);
		}
		return -1111;
	}

	@Override
	public int save(User user) {
		
		try {
			Connection conn;
			PreparedStatement preparedStatement;
			
			conn = DB.getConnection();
			preparedStatement = conn.prepareStatement("INSERT INTO Client"
					+ "(clientCPF, clientStatus, Name, SuperUser, BorrowedBookCount, clientPassword)"
					+ "VALUES"
					+ "(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
					) ;
			
			preparedStatement.setString(1, user.getCpf());
			preparedStatement.setBoolean(2, user.isStatus());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setBoolean(4, user.isSuperUser());
			preparedStatement.setInt(5, user.getBorrowedBooksCount());
			preparedStatement.setString(6, user.getPassword());
			
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
		
			//conn.close();
			//preparedStatement.close();
			resultSet.next();
			
		
			return resultSet.getInt(1);
		} catch(Exception e) {
			Alerts.showAlert("Error in write data", e.getMessage(), AlertType.ERROR);
		} 
		return -1111;
		}
	}

	

