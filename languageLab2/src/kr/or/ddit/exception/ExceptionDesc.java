package kr.or.ddit.exception;

import java.io.IOException;

/**
 * 에러 ? 예외 : 예상하지 않았던 비정상 상황(Throwable)
 * 
 *  Error : 개발자가 적극적으로 처리하지 않는 비정상 ,compile error 
 *  
 *  Exception : 개발자가 처리할수 있는 비정상 예외
 *  	checked exception (Exception): 예외가 던져질 경우 반드시 처리해야 하는 예외  
 *  		- IOException 
 *  		- SQLException 
 *  
 *  	unchecked exception(RuntimeException) : 예외가 던쳐지고 명시적인 예외 처리 코드가 없으면 자동으로 VM에게 제어가 전달되는 예외
 * 			- null pointerException
 * 예외 처리 방법 
 * 1. 적극적인 처리 
 *  try{
 * 
 * 	//예외 발생 가능 코드 
 * 	}catch(Exception e try 블럭 내에서 발생하는 예외 타입 e ){
 * 	//예외 처리 코드 발생 예외를 변경 가능 ,발생한 예외를 없애는 것도 가능
 * 
 *  }finally{
 *  //예외 발생 여부와 무관하게 처리하는 구문(자원의 해제) 
 *  
 *  }
 * 2. 호출자에게 전달 : throws  
 *	
 * ** Custom exception 정의 
 *		: 예외의 특성을 경정하고 처리 정책에 따라 상위가 ㅈ결정됨 
 *	throw new CustomException();
 */

public class ExceptionDesc {

	public static void main(String[] args) throws RuntimeException  {
		try {
			test1();
		} catch (UserNotFoundException e) {
			System.err.println(e.getMessage());
			throw e;//e ==> 이미 객체가 생성된 상태라서 보내짐
		}
	}

private static void test1()  {//throws RuntimeException
		try {
			if(1==1)
			throw new IOException("강제 발생 예외");
		} catch (IOException e) {
			throw new UserNotFoundException(e);//NullPointerException(e.getMessage());
			//d원래의 에러를 표시할 수 있도록 설정 
		}//강제로 에러 발생시 throw 사용 
}

}
