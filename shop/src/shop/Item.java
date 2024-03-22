package shop;

public class Item {
	private String name;
	private int price, piece;
	
	public Item() {
		
	}
	
	public Item(String name, int price, int piece) {
		this.name = name;
		this.price = price;
		this.piece = piece;
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
		return new Item(this.name, this.price, this.piece);
	}
	
	@Override
	public String toString() {
		return String.format("%s(%d원) 남은 수량 : %d개", this.name, this.price, this.piece);
	}
}
