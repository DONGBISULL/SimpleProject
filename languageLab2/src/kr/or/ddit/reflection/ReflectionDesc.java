package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import kr.or.ddit.reflect.ReflectionTest;
import kr.or.ddit.vo.MemberVO;
//필드 
//단순한 전역 변수


//프로퍼티
//필드가 자바빈의 규약에 따라 만들어졌을 경우 프로퍼티

public class ReflectionDesc {
	
	public static void main(String[] args) {
		Object obj = ReflectionTest.getObject();
		System.out.println(obj);
		//Object 형태에서 기존의 타입을 얻어와서 추적 ??
		//가지고 있는게 인스턴스일때 
		Class type = obj.getClass();//인스턴스에서부터 추적
		System.out.println(type);
		
		Field[ ] fields = type.getDeclaredFields();
		for(Field fld : fields ) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(fld.getName(), type);
				Method getter = pd.getReadMethod();
				Method setter = pd.getWriteMethod();
				System.out.printf("%s = %s \n" ,pd.getName(),getter.invoke(obj));
			
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	}

	public static void reflect1(Object obj) {
		Class type = obj.getClass();//인스턴스에서부터 추적
		System.out.println(type);
		Field[] fields = type.getDeclaredFields();
		for(Field fld  : fields) {
			//fld.setAccessible(true); 
			//member.getMem_id()
			String fldName = fld.getName();
			String getterName = "get" + fldName.substring(0,1).toUpperCase() 
					+fldName.substring(1);
			
			try {
				Method getter = type.getMethod(getterName);
				System.out.printf("%s = %s\n",fldName ,getter.invoke(obj));//파라미터 없을거라 파라미터를 받지 않ㅇ는다
			} catch (IllegalArgumentException | IllegalAccessException e) {
				//obj를 잘못넘겨줬을 경우 생기는 에러
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
