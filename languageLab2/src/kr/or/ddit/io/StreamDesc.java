package kr.or.ddit.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLDecoder;

public class StreamDesc {
/**
 * Stream : 연속성을 가진 일련의 데이터 집합이면서 동시에 전송 (단방향) 통로
 * 
 * 스트림의 종류 
 * 
 * 1.  데이터의 전송 크기에 따른 분류 
 * 		1)byte stream :XXXInputStream /XXXOutpurStream
 * 		
 *  	2)char stream : XXXReader /XXXWriter
 *  	 문자를 표현하기 위한 2단위짜리의 버퍼가 있는 스트림 
 * 
 * 2. 스트림의 생성 방법 및 필터링 여부
 * 		1) 1차 스트림 : 매체를 대상으로 직접 생성 가능한 스트림
 * 			FileInputStream(file), SocketInputStream()  / socket.getInputStream()
 * 
 * 		2) 2차(연결형) 스트림 : 1차 스트림을 대상으로 생성하는 스트림(단독으로 사용 불가능)
 * 		 	BufferedReader, DataInputStream , ObjectInputStream(직렬화나 역직렬화가 가능할 때만 사용 가능)
 * 		 	BufferedWriter, DataInputStream , ObjectInputStream(직렬화나 역직렬화가 가능할 때만 사용 가능) ==> 실제로는 버퍼에 기록하는것
 *			==>FileWriter가 있어야 파일에 기록이 가능
 *
 *			jspWriter도 2차 스트림 ==> 이 때 1차 스트림  socketInputStream()	 
 *
 *3. 스트림의 사용 단계 
 *		1) 매체(media)를 어플리케이션 내에서 제어할 수 있도록 객체화 
 *		2) 매체를 대상으로 1차 스트림 생성 
 *		3) 데이터를 필터링할 수 있는 2차 스트림 사용(optional)
 *		4) 기록이나 읽어들이는 작업 반복(EOF , -1 , null)
 *		***** 자원의 해제 (finally try with resource)
 *		
 *
 */
	public static void main(String[] args) throws Exception{
		URL imageURL = new URL("https://www.google.com/logos/doodles/2021/get-vaccinated-wear-a-mask-save-lives-june-22-6753651837109280-law.gif");
		File saveFile = new File("d:/google.gif");
		byte[] buffer = new byte[1024];
		int pointer = -1 ;
		try(
		InputStream is =   imageURL.openStream();
		FileOutputStream fos = new FileOutputStream(saveFile);
		) {
			while((pointer = is.read(buffer))!=-1){
			 fos.write(buffer, 0, pointer);
		 }
			fos.flush();//버퍼안에 남은 내용을 방출할 때 사용
			System.out.println("다운로드 완료");
		}
	}
	
	
	private static void serialize() throws IOException, ClassNotFoundException{
		File writeFile = new File("d:/test.dat"); 
		/*TestVO vo = new TestVO(23, "text"); //TestVO ==> 직렬화 불가능한 형태라는 오류 터짐
		vo.setRegno1("1234324123");
		//DTO == > Data Transform  Object
		File writeFile = new File("d:/test.dat"); 
		 try(
		FileOutputStream fos = new FileOutputStream(writeFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		){
			oos.writeObject(vo);//직렬화
		} */
	
		try(
			FileInputStream fis = new FileInputStream(writeFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
				){
			//deserialization
			TestVO readData = (TestVO) ois.readObject();
			System.out.println(readData);
		}
		
		
	}
	
	private static void readClassPathResource() throws Exception{
		//객체 생성 
		//	URL url =  StreamDesc.class.getResource("/kr/or/ddit/io/오래된 노래_utf8.txt"); // '/' 없음 상대 경로
			URL url =  StreamDesc.class.getResource("/kr/or/ddit/io/오래된 노래.txt"); // '/' 없음 상대 경로
			// "/kr/or/ddit/id/오래된 노래_utf8.txt"
			
			if(url!=null) {
				File readFile = new File(url.getFile());
				//System.out.println(readFile.getAbsolutePath());
				String filePath =URLDecoder.decode(url.getFile(), "utf-8"); //톰캣에 의해 이뤄지는 작업
				System.out.println(filePath);
				System.out.println(url.getFile());
				try(
						//FileReader reader = new FileReader(filePath);
						
						FileInputStream fis = new FileInputStream(filePath);
						//중간에 젠더 역할을 해주는 inputStreamReader 
						InputStreamReader reader = new InputStreamReader(fis, "ms949");
						BufferedReader br = new BufferedReader(reader);
						){
						String tmp = null;
						//2차부터 닫아야 함 3차라면 3차부터 닫기
						while((tmp =br.readLine())!=null) {
							System.out.println(tmp);
						}
					}//try-catch 
			
			}
			
			
		
	}
	
	
	private static void readFileSystemResourc() throws IOException { //
		File readFile = new File("d:/contents/another day.txt");//파일 시스템의 루트 
		System.out.println(readFile.length());
		try(
		FileInputStream fis = new FileInputStream(readFile);){
			//영어라서 어떤 걸로 읽어도 상관 없음 
			byte[] buffer = new byte[1024];
			int pointer = -1;
			
			while((pointer = fis.read(buffer))!=-1) {//리턴 타입은 int지만 돌아오는 건 바이트 
				System.out.write(buffer, 0, pointer); //버퍼 , 읽어들일 시작 , pointer
			}
		}//
		
	}
	
}
