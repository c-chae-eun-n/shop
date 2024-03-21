package shop;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> userList;
	
	private UserManager() {
		userList = new ArrayList<>();
	}
	
	private static UserManager instance = new UserManager();
	
	public static UserManager getInstance() {
		return instance;
	}
	
	// create
	public User createUser(String id, String password) {
		if(isValidId(id)) {
			User user = new User(id, password);
			userList.add(user);
			return user.clone();
		}
		return new User();
	}
	
	private boolean isValidId(String id) {
		User user = findUserById(id);
		if(user.getId().equals(""))
			return true;
		return false;
	}
	
	public User findUserById(String id) {
		for(User user : userList) {
			if(user.getId().equals(id))
				return user.clone();
		}
		return new User();
	}
	
	// Read
	public User getUser(int index) {
		User user = userList.get(index);
		return user.clone();
	}
	
	// Update
	public void setUser(int index, User user) {
		User temp = userList.get(index);
		
		if(temp.getId().equals(user.getId()))
			temp.setCart(user.getCart());
	}
	
	// Delete
	public void removeUser(int index) {
		if(index < 0 || index >= userList.size()) {
			System.err.println("유효하지 않은 범위입니다.");
			return;
		}
		
		userList.remove(index);
	}
}
