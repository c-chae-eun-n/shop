package shop;

import java.util.ArrayList;

public class ItemManager {
	private ArrayList<Item> itemList;
	
	private ItemManager() {
		itemList = new ArrayList<>();
	}
	
	private static ItemManager instance = new ItemManager();
	
	public static ItemManager getInstance() {
		return instance;
	}
	
	// create
	public Item createItem(String name, int price) {
		if(isValidName(name)) {
			Item item = new Item(name, price);
			itemList.add(item);
			return item.clone();
		}
		return new Item();
	}
	
	private boolean isValidName(String name) {
		Item item = findItemByName(name);
		if(item.getName() == null)
			return true;
		return false;
	}
	
	public Item findItemByName(String name) {
		for(Item item : itemList) {
			if(item.getName().equals(name))
				return item.clone();
		}
		return new Item();
	}
	
	public int findItemIndexByName(String name) {
		for(int i=0; i<itemList.size(); i++) {
			Item item = itemList.get(i);
			if(item.getName().equals(name))
				return i;
		}
		return -1;
	}
}
