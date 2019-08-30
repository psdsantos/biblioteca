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
				"SELECT * FROM Client WHERE ID = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			rs.next();
			//ID = ?, clientCPF =?, clientStatus =?, Name = ?, SuperUser = ?, BorrowedBookCount =?

				obj = new User();
				obj.setID(rs.getInt("ID"));
				obj.setName(rs.getString("Name"));
				obj.setStatus(rs.getBoolean("clientStatus"));
				obj.setCpf(rs.getString("ClientCPF"));
				obj.setBorrowedBooksCount(rs.getInt("borrowedBooksCount"));
				obj.setSuperUser(rs.getBoolean("isSuperUser"));
				
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
				"SELECT * FROM Client ORDER BY ID");
			rs = st.executeQuery();

			//ID = ?, clientCPF =?, clientStatus =?, Name = ?, SuperUser = ?, BorrowedBookCount =?
			while (rs.next()) {
				User obj = new User();
				obj.setID(rs.getInt("ID"));
				obj.setName(rs.getString("Name"));
				obj.setStatus(rs.getBoolean("clientStatus"));
				obj.setCpf(rs.getString("ClientCPF"));
				obj.setBorrowedBooksCount(rs.getInt("borrowedBooksCount"));
				obj.setSuperUser(rs.getBoolean("isSuperUser"));
				
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
			
			preparedStatement = conn.prepareStatement("DELETE FROM Client WHERE ID = ?;");
		
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
			
			preparedStatement = conn.prepareStatement("UPDATE Book"
					+ "SET ID = ?, clientCPF =?, clientStatus =?, Name = ?, SuperUser = ?, BorrowedBookCount =?"
					+ "WHERE ID = ?;"
					) ;
			
			
			preparedStatement.setInt(1, user.getID());
			preparedStatement.setString(2, user.getCpf());
			preparedStatement.setBoolean(3, user.isStatus());
			preparedStatement.setString(4, user.getName());
			preparedStatement.setBoolean(5, user.isSuperUser());
			preparedStatement.setInt(6, user.getBorrowedBooksCount());
			preparedStatement.setInt(6, user.getID());
			
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
			preparedStatement = conn.prepareStatement("INSERT INTO Book"
					+ "(ID, clientCPF, clientStatus, Name, SuperUser, BorrowedBookCount)"
					+ "VALUES"
					+ "(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
					) ;
			
			preparedStatement.setInt(1, user.getID());
			preparedStatement.setString(2, user.getCpf());
			preparedStatement.setBoolean(3, user.isStatus());
			preparedStatement.setString(4, user.getName());
			preparedStatement.setBoolean(5, user.isSuperUser());
			preparedStatement.setInt(6, user.getBorrowedBooksCount());
			
			
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

	

