package entities;

public class Book {
	String name;
	public boolean status;
	
	public Book() {
	}
	public Book(String name, boolean status) {
		this.name = name;
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
