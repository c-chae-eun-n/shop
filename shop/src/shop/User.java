package shop;

public class User {
	public static final String ADMIN = "admin";
	
	private String id, password;
	private Cart cart;
	
	public User() {
		
	}
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
		cart = new Cart();
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public User clone() {
		return new User(this.id, this.password);
	}
	
	@Override
	public String toString() {
		return String.format("%s/%s", this.id, this.password);
	}
}
