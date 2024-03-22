package shop;

import java.util.Scanner;

public class Shop {
	
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOG_IN = 3;
	private final int LOG_OUT = 4;
	private final int SHOPPING = 5;
	private final int MY_PAGE = 6;
	private final int MANAGER = 7;
	private final int EXIT = 0;
	
	private final int MY_CART = 1;
	private final int DELETE_CART = 2;
	private final int UPDATE_CART = 3;
	
	private final int ITEM = 1;
	private final int VIEW_SALE = 2;
	
	private final int ENROLL_ITEM = 1;
	private final int DELETE_ITEM = 2;
	private final int UPDATE_ITEM = 3;
	
	private final int TYPE_OUT = 1;
	private final int TYPE_IN = 2;
	
	private Scanner scan = new Scanner(System.in);
	
	private UserManager userManager = UserManager.getInstance();
	private ItemManager itemManager = ItemManager.getInstance();
	
	private int sale;
	
	private int log = -1;
	
	private boolean isExit;
	
	public Shop(String message) {
		System.out.println(message);
	}
	
	private boolean checkLog(int typeCode) {
		boolean result = false;
		
		if(typeCode == TYPE_OUT) {
			if(log == -1)
				result = true;
			else
				System.err.println("로그아웃 후 사용 가능합니다.");
		}else if(typeCode == TYPE_IN) {
			if(log != -1)
				result = true;
			else
				System.err.println("로그인 후 사용 가능합니다.");
		}
		
		return result;
	}
	
	private void printMainMenu() {
		System.out.println("[1] 회원가입");
		System.out.println("[2] 탈퇴");
		System.out.println("[3] 로그인");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 쇼핑하기");
		System.out.println("[6] 마이페이지");
		System.out.println("[7] 관리자");
		System.out.println("[0] 종료");
	}
	
	private void printMyPageMenu() {
		System.out.println("[1] 내 장바구니");
		System.out.println("[2] 항목 삭제");
		System.out.println("[3] 수량 수정");
		System.out.println("[4] 결제");
	}
	
	private void printManagerMenu() {
		System.out.println("[1] 아이템");
		System.out.println("[2] 조회(총 매출)");
	}
	
	private void printManagerSubMenu() {
		System.out.println("[1] 등록");
		System.out.println("[2] 삭제");
		System.out.println("[3] 수정");
	}
	
	private int inputNumber(String message) {
		int number = -1;
		
		try {
			System.out.print(message + " : ");
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자만 입력.");
		}
		
		return number;
	}
	
	private String inputString(String message) {
		System.out.print(message + " : ");
		return scan.next();
	}
	
	private void runMainMenu(int select) {
		if(select == JOIN && checkLog(TYPE_OUT)){
			join();	
		}
		else if(select == LEAVE && checkLog(TYPE_IN)) {
//			leave();
		}
		else if(select == LOG_IN && checkLog(TYPE_OUT)) {
			login();
		}
		else if(select == LOG_OUT && checkLog(TYPE_IN)) {
			logout();
		}
		else if(select == SHOPPING && checkLog(TYPE_IN)) {
			shopping();
		}
		else if(select == MY_PAGE && checkLog(TYPE_IN)) {
//			mypage();
		}
		else if(select == MANAGER && checkLog(TYPE_IN)) {
			manager();
		}
		else if(select == EXIT) {
			exit();
		}
	}
	
	private void join() {
		String id = inputString("id");
		String password = inputString("password");
		
		User user = userManager.createUser(id, password);
		if(user.getId() == null) {
			System.err.println("이미 존재하는 아이디입니다.");
			return;
		}
		
		System.out.println("회원가입 완료");
	}
	
	private void login() {
		String id = inputString("id");
		String password = inputString("password");
		
		int userIndex = userManager.findUserIndexById(id);
		if(userIndex == -1 || !userManager.getUser(userIndex).getPassword().equals(password)) {
			System.err.println("올바르지 않은 회원정보입니다.");
			return;
		}
		
		log = userIndex;
		System.out.printf("%s님 환영합니다.\n", userManager.getUser(userIndex).getId());
	}
	
	private void logout() {
		log = -1;
		System.out.println("로그아웃 완료");
	}
	
	private boolean checkManager() {
		String id = userManager.getUser(log).getId();
		String target = User.ADMIN;
		if(!id.equals(target)) {
			System.err.println("관리자만 이용 가능한 메뉴입니다.");
			return false;
		}
		return true;
	}
	
	private void manager() {
		if(!checkManager()) {
			return;
		}
		printManagerMenu();
		int select = inputNumber("메뉴 번호 입력");
		if(select == ITEM) {
			printManagerSubMenu();
			int sel = inputNumber("메뉴 번호 입력");
			
			if(sel == ENROLL_ITEM) {
				enrollItem();
			}
			else if(sel == DELETE_ITEM) {
				deleteItem();
			}
			else if(sel == UPDATE_ITEM) {
				updateItem();
			}
		}
		else if(select == VIEW_SALE) {
			viewSale();
		}
	}
	
	private boolean checkItemExist() {
		if(itemManager.getSize() == 0) {
			System.err.println("등록된 상품이 없습니다.");
			return false;
		}
		System.out.println("[현재 상품 목록]");
		itemManager.printItemAll();
		return true;
	}
	
	private void enrollItem() {
		checkItemExist();
		
		String name = inputString("name");
		int price = inputNumber("price");
		if(price < 1) {
			System.err.println("1원 이상부터 등록 가능합니다.");
			return;
		}
		
		Item item = itemManager.createItem(name, price);
		if(item.getName() == null) {
			System.err.println("이미 존재하는 상품입니다.");
			return;
		}
		
		System.out.println("상품 등록 완료");
	}
	
	private void deleteItem() {
		if(!checkItemExist()) 
			return;
		
		String name = inputString("name");
		int itemIndex = itemManager.findItemIndexByName(name);
		if(itemIndex == -1) {
			System.err.println("존재하지 않는 상품입니다.");
			return;
		}
		
		itemManager.removeItem(itemIndex);
		System.out.println("상품 삭제 완료");
	}
	
	private void updateItem() {
		if(!checkItemExist()) 
			return;
		
		String name = inputString("name");
		int itemIndex = itemManager.findItemIndexByName(name);
		if(itemIndex == -1) {
			System.err.println("존재하지 않는 상품입니다.");
			return;
		}
		
		int price = inputNumber("수정할 가격 입력");
		if(price < 1) {
			System.err.println("1원 이상부터 등록 가능합니다.");
			return;
		}
		Item item = itemManager.getItem(itemIndex);
		item.setPrice(price);
		
		itemManager.setItem(itemIndex, item);
		System.out.println("수정 완료");
	}
	
	private void viewSale() {
		System.out.printf("총 매출 : %d원\n", sale);
	}
	
	private void shopping() {
		if(!checkItemExist()) 
			return;
		
		String name = inputString("구매할 상품 이름 입력");
		int itemIndex = itemManager.findItemIndexByName(name);
		if(itemIndex == -1) {
			System.err.println("존재하지 않는 상품입니다.");
			return;
		}
		
		int piece = inputNumber("구매할 개수 입력");
		if(piece < 1) {
			System.err.println("1개 이상부터 등록 가능합니다.");
			return;
		}
		
		Cart cart = userManager.getUser(log).getCart();
		cart.createCart(name, piece);

		System.out.println("쇼핑 완료");
	}
	
	private void exit() {
		isExit = true;
	}
	
	private boolean isRun() {
		return !isExit;
	}
	
	public void run() {
		while(isRun()) {
			userManager.printUserAll();
			printMainMenu();
			int select = inputNumber("메뉴 번호 입력");
			runMainMenu(select);
		}
	}
}
