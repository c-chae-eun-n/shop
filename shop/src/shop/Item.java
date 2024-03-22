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
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public int getTotal() {
		return this.total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public Item clone() {
		return new Item(this.name, this.price);
	}
	
	@Override
	public String toString() {
		return String.format("%s (%d원)", this.name, this.price);
	}
}
