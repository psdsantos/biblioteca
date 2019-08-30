package model.dao;

import java.util.List;

public interface DAO <T> {

	T findByID (Integer id);
	
	List<T> findAll();
	
	void delete(Integer id);
	
	int update(T t);
	
	int save (T t);
	
	
}
