package model.dao;

public interface DAO <T> {

	void findByID (T t);
	
	void findAll();
	
	void delete(T t);
	
	void update(T t);
	
	void save (T t);
	
	
}
