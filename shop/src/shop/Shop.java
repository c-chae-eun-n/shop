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
	
	private int log = -1;
	
	public Shop(String message) {
		
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
	}
	
	private void printMyPageMenu() {
		System.out.println("[1] 내 장바구니");
		System.out.println("[2] 항목 삭제");
		System.out.println("[3] 수량 수정");
		System.out.println("[4] 결제");
	}
	
	private int inputNumber(String message) {
		int number = -1;
		
		try {
			System.out.println(message + " : ");
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자만 입력.");
		}
		
		return number;
	}
	
	private String inputString(String message) {
		System.out.println(message + " : ");
		return scan.next();
	}
	
	private void runMainMenu(int select) {
		if(select == JOIN && checkLog(TYPE_OUT)){
//			join();	
		}
		else if(select == LEAVE && checkLog(TYPE_IN)) {
//			leave();
		}
		else if(select == LOG_IN && checkLog(TYPE_OUT)) {
//			login();
		}
		else if(select == LOG_OUT && checkLog(TYPE_IN)) {
//			logout();
		}
		else if(select == SHOPPING && checkLog(TYPE_IN)) {
//			shopping();
		}
		else if(select == MY_PAGE && checkLog(TYPE_IN)) {
//			mypage();
		}
		else if(select == MANAGER && checkLog(TYPE_IN)) {
//			manager();
		}
	}
	
	public void run() {
		
	}
}
