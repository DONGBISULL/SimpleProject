package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class UserDao {//DB와 연결하는 쿼리 작성 클래스
	
	//싱글톤 패턴
	private UserDao() {} //하나의 클래스에서 하나의 인스턴스만 생성
	
	private static UserDao instance;
	public static UserDao getInstance() { //외부에서 호출이 가능한 정적 메소드인 getInstance()정의
		//getInstance 메서드 정의 
		//정적 필드에서 생성한 인스턴스 리턴값으로 돌려준다 
		// 외부 클래스에서는 싱글톤으로 정의된 클래스의 인스턴스를 얻기 위해서는 getInstance() 메서드를 호출
		
				if(instance ==null) {
					instance = new UserDao();//자신의 인스턴스 생성 
				}
				return instance;
			}
	
	//자바 유틸에서 instance를 가져와서 사용하기 위해 싱글톤 인스턴스를 호출 
			private JDBCUtil jdbc = JDBCUtil.getInstance();
			
			public boolean CheckuserId(String userId) {
		
				String sql = "select user_id from tb_user(User_id) where user_id=?";
				
				List<Object> p= new ArrayList<>();
				p.add(userId);
				
				return jdbc.Check(sql);
				
	
		}
			
			public Map<String, Object> selectAdmin(){
			
				String sql = "select MNGR_ID from tb_admin";
				
				return jdbc.selectOne(sql);
			}
			
			

		public static int insertUser(Map<String, Object> param) {
			
			String sql ="insert into tb_user (USER_ID, USER_NM,MNGR_ID"
					+ 	" USER_MBTLNUM, USER_PASSWORD, USER_ADRES "
					+ 	" values (?,?,?,?,?,?)";
			
			
			
//			String MNGR_ID 
			
			List <Object> p = new ArrayList<>();
			
			p.add(param.get("USER_ID"));
			p.add(param.get("USER_NM"));
			p.add(param.get("USER_MBTLNUM"));
			p.add(param.get("USER_PASSWORD"));
			p.add(param.get("USER_ADRES"));
			
			return 0;
		}
	
	}
	

