package model.entities;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import model.exceptions.BookException;
import model.util.Alerts;

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
	
	
	public void addBooks(Book book) {
		System.out.println(getBorrowedBooksCount());
		if((this.getBorrowedBooksCount()+1)>=3) {
			this.setStatus(false);
			Alerts.showAlert("Maximum", "You have three books, it is the limit", AlertType.WARNING);
			}else {

			this.setBorrowedBooksCount(this.getBorrowedBooksCount() + 1);
		}
	}

	public void rmBooks(Book book)  {
		if((this.getBorrowedBooksCount()-1)<3) {
			this.setStatus(true);
		}
		if((this.getBorrowedBooksCount()-1)<=0) {
			this.setStatus(true);
			Alerts.showAlert("Minimum", "You have zero books, it is the minimum limit", AlertType.WARNING);
		}else {

			this.setBorrowedBooksCount(this.getBorrowedBooksCount() - 1);
		}
	}

	
	public int getBorrowedBooksCount() {
		return borrowedBooksCount;
	}

	public void setBorrowedBooksCount(int borrowedBooksCount) {
		this.borrowedBooksCount = borrowedBooksCount;
	}
	
	
	@Override
	public String toString() {
		return "User [superUser=" + superUser + ", ID=" + ID + ", name=" + name + ", cpf=" + cpf
				+ ", borrowedBooksCount=" + borrowedBooksCount + ", password=" + password + ", status=" + status
				+  "]";
	}
	
	
}
