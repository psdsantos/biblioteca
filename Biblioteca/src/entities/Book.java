package entities;

public class Book {
	String name;
	int code;
	public boolean status;
	
	public Book() {
	}
	public Book(String name, int code, boolean status) {
		super();
		this.name = name;
		this.code = code;
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
