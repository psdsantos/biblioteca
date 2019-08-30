package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import model.db.DB;
import model.db.DbException;
import model.entities.Book;
import model.entities.User;
import model.util.Alerts;

public class BookDAOJDBC implements DAO<Book>{

	@Override
	public List<Book> findByID_Name(Book book) {

		List<Book> list = null;
		list = findAll();
		
		list.removeIf(n -> !n.getName().equalsIgnoreCase(book.getName()));
		return list;
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<>();

		try {
			if(conn==null) {
				conn = DB.getConnection();
			}
			st = conn.prepareStatement(
				"SELECT * FROM Book ORDER BY ID");
			rs = st.executeQuery();

			
			while (rs.next()) {
				Book obj = new Book();
				obj.setId(rs.getInt("ID"));;
				obj.setName(rs.getString("Name"));
				obj.setStatus(rs.getBoolean("bookStatus"));
				User user = new User();
				user.setID(rs.getInt("ClientID"));
				obj.setUser(user);
				
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
	public void delete(Book book) {
		// TODO Auto-generated method stub
		try {
			Connection conn;
			PreparedStatement preparedStatement;
			
			conn = DB.getConnection();
			
			preparedStatement = conn.prepareStatement("DELETE FROM Book WHERE ID = ?"
					) ;
			
			preparedStatement.setInt(1, book.getId());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			Alerts.showAlert("Deleting book", "Numero de linhas afetadas: "+rowsAffected, AlertType.INFORMATION);
		
		} catch(Exception e) {
			Alerts.showAlert("Error in delete data", e.getMessage(), AlertType.ERROR);
		}
		
	}

	@Override
	public int update(Book book) {
		// TODO Auto-generated method stub
		try {
			Connection conn;
			PreparedStatement preparedStatement;
			
			conn = DB.getConnection();
			if(book.getUser().getID()!= null) {
			preparedStatement = conn.prepareStatement("UPDATE Book"
					+ "SET Name = ?, "
					+ "ClientID = ?,"
					+ "bookStatus = ?,"
					+ "WHERE ID = ?;"
					) ;
			
			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getUser().getCpf());
			preparedStatement.setBoolean(3, book.isStatus());
			preparedStatement.setInt(4, book.getId());
			
			}else {
				preparedStatement = conn.prepareStatement("UPDATE Book"
						+ "SET Name = ?, "
						+ "bookStatus = ?,"
						+ "WHERE ID = ?;"
						) ;
				
				preparedStatement.setString(1, book.getName());
				preparedStatement.setBoolean(2, book.isStatus());
				preparedStatement.setInt(3, book.getId());
					
			}
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
	public int save(Book book) {
		// TODO Auto-generated method sbookub
		
		try {
			Connection conn;
			PreparedStatement preparedStatement;
			
			conn = DB.getConnection();
			if(book.getUser().getID() != null) {
			preparedStatement = conn.prepareStatement("INSERT INTO Book"
					+ "(Name, clientID, bookStatus)"
					+ "VALUES"
					+ "(?,?,?)", Statement.RETURN_GENERATED_KEYS
					) ;
			
			preparedStatement.setString(1, book.getName());
			preparedStatement.setInt(2, book.getUser().getID());
			preparedStatement.setBoolean(3, book.isStatus());
			}else {
				preparedStatement = conn.prepareStatement("INSERT INTO Book"
						+ "(Name, bookStatus)"
						+ "VALUES"
						+ "(?,?)", Statement.RETURN_GENERATED_KEYS
						) ;
				
				preparedStatement.setString(1, book.getName());
				preparedStatement.setBoolean(2, book.isStatus());
					
			}
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

