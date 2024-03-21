package shop;

public class User {
	private String id, password;
	
	public final String ADMIN = "admin";
	public final String MENUS = "24567";
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return String.format("%s/%s", this.id, this.password);
	}
}
