package model.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javafx.fxml.FXMLLoader;

public class DB {
	
	private static Connection connection = null;
	
	public Connection getConnection() throws DbException {
		
		if(connection==null) {
			try {
			Properties properties = this.loadProperties();
			
			String url = properties.getProperty("dburl");
			
			connection = DriverManager.getConnection(url, properties);
			
			}
			catch(SQLException sqle) {
				
			sqle.printStackTrace();	
			throw new 	DbException("Error in init the connection");
			
			}
		
		}
		return connection;
		
	}
	
	public void closeConnection() throws DbException {
		if(connection!= null) {
		try {
			connection.close();
		}catch(SQLException sqle) {
			
			throw new DbException("Error in closing connection with DB");
		
		}
		}
		
		
	}
	
	
	private Properties loadProperties() throws DbException  {

		Properties dbProperties;

		
		try {
			dbProperties = new Properties();
			dbProperties.load(this.getClass().getResourceAsStream("db2.properties"));

			return dbProperties;
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw new DbException("Error in opening db.properties");
	
		}

	}
}
