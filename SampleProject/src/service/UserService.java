package service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.UserDao;
import util.ScanUtil;

public class UserService {	//	Service : 화면 기능
	
	private UserService() {}//private로 생성 제한 
	
	/*
	 static 
	class 소속 
	다시 선언안해도 다른 클래스 메소드에 호출 가능 
	인스턴스에서 값을 바꿀 경우 class의 값도 바뀌고 다른 인스턴스도 바뀐다
	
	 public 
	instance 소속
	인스턴스 변수와 인스턴스 메소드를 다른 클래스 메소드에 호출하려면 인스턴스화 해야한다 
	클래스명 인스턴스이름 = new 클래스명();
	*/
	
	private static UserService instance;//인스턴스 보관
	
//	인스턴스(instance)는 클래스를 new 명령문으로 메모리에 생성한 객체
	
	public static UserService getInstance() {//인스턴스 생성
		
//싱글톤 클래스의 인스턴스를 타 클래스에서 사용하기 위해서는 getInstance()를 호출		
		if(instance ==null) {
			instance = new UserService();
		}
		return instance;
		//인스턴스 존재하면 인스턴스 반환
	}
	
	private UserDao userDao = UserDao.getInstance();
	//userDao에서 Instance 받아서 사용
	//모든 클래스들이 동일한 인스턴스를 써야 할 경우
	//
	
	public static int join() {
		System.out.println("================회원 가입================");
		
		Map<String ,Object> param = new HashMap<>();		
		
		System.out.print("아이디>");
		String userId = ScanUtil.nextLine();
		//유효성 검사
		String idCheck = "[0-9a-z_-]{5-20}";
		Pattern p = Pattern.compile(idCheck);
		Matcher m = p.matcher(userId);
		
		if(m.matches()==true) {
			param.put("USER_ID",userId);
		}else{
			System.out.println("아이디는 5자 이상 20자 이하 영문 소문자,숫자, 특수기호_-만 사용 가능합니다");
		}
		//일단 여기선 DB로 연결해서 중복확인은 UserDao
		
		System.out.print("비밀번호 1 >");
		String userPs1 =ScanUtil.nextLine();
		System.out.print("비밀번호 2>");
		String userPs2 =ScanUtil.nextLine();
		
		if(userPs1.equals(userPs2)) {
			param.put("USER_PASSWORD",userPs1);
		}else {
			System.out.println("비밀번호가 일치하지 않습니다");
		}
		
		System.out.println("이름>");
		String userNM = ScanUtil.nextLine();
		param.put("USER_NM", userNM);
		
		System.out.println("휴대폰 번호>");
		String userHp = ScanUtil.nextLine();
		String hpCheck = "^\\d{3}[-]\\d{3,4}[-]\\d{4}$";
		Pattern p1 = Pattern.compile(hpCheck);
		Matcher m1 = p1.matcher(userHp);
		
		if(m1.matches()==true) {
			param.put("USER_MBTLNUM", userHp);
		}else {
			System.out.println("휴대폰 번호 형식에 맞지 않습니다");
		}
		
		
		System.out.println("집주소>");
		String userAd =ScanUtil.nextLine();
		param.put("USER_ADRES",userAd);
		
		Map<String ,Object> MNGR = UserDao.selectAdmin();
		param.put("MNGR_ID", userAd);
//		
		int result = UserDao.insertUser(param);//여기서 왜 int로 결과가 나ㅗ는지...
		
		
		
		
	}


		
	
	
}
