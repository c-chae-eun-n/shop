package shop;

public class Item {
	private String name;
	private int price;
	private int piece;
	private int total;
	
	public Item() {
		
	}
	
	// ItemManager
	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	// Cart
	public Item(String name, int piece, int total) {
		this.name = name;
		this.piece = piece;
		this.total = total;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPiece() {
		return this.piece;
	}
	
	public void setPiece(int piece) {
		this.piece = piece;
	}
	
	public Item clone() {
		return new Item(this.name, this.price);
	}
	
	@Override
	public String toString() {
		return String.format("%s (%d원)", this.name, this.price);
	}
}
