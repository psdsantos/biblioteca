package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.exceptions.BookException;

public class User {
	
	private boolean superUser; 
	private Integer ID;
	private String name;
	private String cpf;
	private int borrowedBooksCount;
	private String password;
	/**
	 * True = Can take books
	 * False = Can't take books
	 */
	private boolean status;
	
	


	private  List<Book> borrowed = new ArrayList<Book>();
	
	public User() {
		
	}
	
	public User(String name, String cpf, int borrowedBooksCount, boolean status) {
		this.setName(name);
		this.setCpf(cpf);
		this.setBorrowedBooksCount(borrowedBooksCount);
		this.setStatus(status);
	}
	
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSuperUser() {
		return superUser;
	}

	public void setSuperUser(boolean superUser) {
		this.superUser = superUser;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public List getBorrowed() {
		return this.borrowed;
	}
	
	public void addBooks(Book book) throws BookException {
		if(borrowed.size()>=3) {
			this.setStatus(false);
			throw new BookException("You have three books, it is the limit");
		}

		try {
			this.borrowed.add(book);
			this.setBorrowedBooksCount(this.getBorrowedBooksCount() + 1);
		}catch(Exception e) {
			throw new BookException(e.getMessage());
		}
		
	}

	public int getBorrowedBooksCount() {
		return borrowedBooksCount;
	}

	public void setBorrowedBooksCount(int borrowedBooksCount) {
		this.borrowedBooksCount = borrowedBooksCount;
	}
}
