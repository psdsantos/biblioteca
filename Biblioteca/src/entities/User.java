package entities;

import java.util.ArrayList;

public class User {
	String name;
	String cpf;
	boolean status;
	public ArrayList<Book> borrowed = new ArrayList<Book>();
	
	public User() {
	}
	public User(String name, String cpf, boolean status) {
		super();
		this.name = name;
		this.cpf = cpf;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
