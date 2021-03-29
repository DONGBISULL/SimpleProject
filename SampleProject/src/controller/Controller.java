package controller;

import service.UserService;
import util.ScanUtil;
import util.View;

public class Controller {

	public static void main(String[] args) {

		new Controller().start();
		/*
		 * Controller : 화면 이동
		 * Service : 화면 기능
		 * Dao : 쿼리 작성
		 */
	}

	private UserService userService = UserService.getInstance();
//	private BoardService boardService = BoardService.getInstance();
	
	private void start() {
		int view = View.HOME;//기본형 뷰 
		//어떤 화면으로 이동할지 int 타입으로 리턴 
		
		while (true) {
			switch(view) {//화면전환 
				case View.HOME : view= home(); break;
//				view = 
				case View.JOIN : view = userService.join(); break;
			
				case View.LOGIN :view = UserService.login();break;
//			
//				case View.RSTRNT; break;
//			
//				case View.ORDER; break;
//				
//				case View.REVIEW; break;
//				case View.MYPAGE; break;
//				case View.NOTICE; break;
//				case View.ADMIN; break;
			
			}
			
		}
	}


	private int home() {//번호로 받아야 화면 전환 가능
		
		System.out.println("---------------------------------");
		System.out.println("1.로그인\t2.회원가입\t 0.프로그램 종료");
		System.out.println("---------------------------------");
		System.out.print("입력>");
		
//		 [Alt + Shift + A] 다중 수정 가능 
//		 Alt + Shift + 위/아래 방향키 
		
		int input = ScanUtil.nextInt();
		
		switch(input) {
		case 1 : //로그인
				return View.LOGIN;
		case 2 : //회원가입
				return View.JOIN;
		case 0 : //프로그램 종료
			System.out.println("프로그램이 종료되었습니다.");
			System.exit(0);	
		}
		
		return View.HOME;
	}
	 
	
	
	
	
	
}
