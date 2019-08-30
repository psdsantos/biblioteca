package model.entities;

public class Book {
	
	private Integer id;
	private String name;
	/**
	 * True = isAvailabe
	 * False = ins't Available
	 */
	private boolean status;
	private User user;
	
	public Book() {
	}
	
	public Book(String name, boolean status) {
		this.name = name;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isStatus() {
		return this.status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
