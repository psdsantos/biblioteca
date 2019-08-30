package model.dao;

import java.util.List;

public interface DAO <T> {

	List<T> findByID_Name (T t);
	
	List<T> findAll();
	
	void delete(T t);
	
	int update(T t);
	
	int save (T t);
	
	
}
