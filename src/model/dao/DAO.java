package model.dao;

import java.util.List;

public interface DAO <T> {

	T findByID (Integer id);
	
	List<T> findAll();
	
	void delete(Integer id);
	
	void update(T t);
	
	int save (T t);
	
	
}
