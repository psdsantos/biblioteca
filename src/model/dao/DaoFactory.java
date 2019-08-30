package model.dao;

public class DaoFactory {

	public UserDAOJDBC createUserDaojdbc() {
		return new UserDAOJDBC();
	}
	
	public BookDAOJDBC createBookDAOJDBC() {
		return new BookDAOJDBC();
	}
	
}
