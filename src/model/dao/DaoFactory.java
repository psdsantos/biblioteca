package model.dao;

public class DaoFactory {

	public static UserDAOJDBC createUserDaojdbc() {
		return new UserDAOJDBC();
	}
	
	public static BookDAOJDBC createBookDAOJDBC() {
		return new BookDAOJDBC();
	}
	
}
