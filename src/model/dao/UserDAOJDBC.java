package model.dao;

import java.util.List;

import model.entities.User;

public class UserDAOJDBC implements DAO<User> {

	@Override
	public List<User> findByID_Name(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void findByCPF(User user) {
		
	}
	
	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return -1111;
	}

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		return -1111;
	}

	
}
