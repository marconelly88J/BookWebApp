package model;

public class User {

	private int id;
	private String email;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String email) {
		super();
		this.id = id;
		this.email = email;
	}

	public User(String email) {
		super();
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + "]";
	}
	
	
		
}
