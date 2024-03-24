package shop;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> cartList;
	private ItemManager itemManager = ItemManager.getInstance();
	
	public Cart() {
		cartList = new ArrayList<>();
	}
	
	// Create
	public void createCart(String name, int piece) {
		int index = findItemIndexByName(name);

		if(index == -1) {
			Item item = new Item();
			item.setName(name);
			item.setPiece(piece);
			
			cartList.add(item);
			return;
		}
		int curPiece = cartList.get(index).getPiece();
		cartList.get(index).setPiece(curPiece+piece);
		return;
	}
	
	private int findItemIndexByName(String name) {
		for(int i=0; i<cartList.size(); i++) {
			Item item = cartList.get(i);
			if(item.getName().equals(name))
				return i;
		}
		return -1;
	} 
	
	// Delete
	public void removeItem(String name) {
		int index = findItemIndexByName(name);
		
		if(index < 0 || index >= cartList.size()) {
			System.err.println("존재하지 않는 상품입니다.");
			return;
		}
		
		cartList.remove(index);
	}
	
	public void removeCartAll() {
		cartList.clear();
	}
	
	public Item getItem(int index) {
		Item item = cartList.get(index);
		return item.clone();
	}
	
	// Update
	public void setCart(String name, int piece) {
		int index = findItemIndexByName(name);
		
		if(index < 0 || index >= cartList.size()) {
			System.err.println("존재하지 않는 상품입니다.");
			return;
		}
		
		cartList.get(index).setPiece(piece);
	}
	
	public void printMyCartAll() {
		if(cartList.size() == 0) {
			System.out.println("텅-");
			return;
		}
		
		for(int i=0; i<cartList.size(); i++) {
			Item item = cartList.get(i);
			System.out.printf("%d) %s (%d개)\n", i+1, item.getName(), item.getPiece());
		}
		int total = calculateTotal();
		System.out.printf("총 금액 : %d원\n", total);
	}
	
	public int calculateTotal() {
		int total = 0;
		
		for(int i=0; i<cartList.size(); i++) {
			Item cart = cartList.get(i);
			for(int j=0; j<itemManager.getSize(); j++) {
				Item item = itemManager.getItem(j);
				if(cart.getName().equals(item.getName())) 
					total += item.getPrice()*cart.getPiece();
			}
		}
		
		return total;
	}
	
	public int cartListSize() {
		return cartList.size();
	}
}
