package com.mystudy.jdbc1;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class StudentManager_Delete {
	
	public static void main (String[] args) {
	//JDBC를 이용한 DB 프로그래밍 절차 ------------------------------
	//0. JDBC 라이브러리 개발환경 설정(빌드경로에 등록)
	//1. JDBC 드라이버 로딩
	//2. DB연결  - Connection 객체 생성 <-DriverManager
	//3. Statement문 실행(SQL문 실행)
	//4. SQL 실행 결과에 대한 처리
	//   -SELECT : 조회(검색) 데이타 결과 값에 대한 처리
	//   -INSERT, UPDATE, DELETE : int값(건수) 처리
	//5. 클로징 처리에 의한 자원 반납
	//-------------------------------------------------
		
	//1. JDBC 드라이버 로딩
//	Class.forName("oracle.jdbc.driver.OracleDriver");
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		System.out.println(">>드라이버 로딩 성공");
	}catch(ClassNotFoundException e) {
		System.out.println(">>드라이버 로딩 실패!!!");
		e.printStackTrace();
	}
	
	//2. DB연결  - Connection 객체 생성 <-DriverManager
	Connection conn = null;
	try {
		conn =  DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", 
				"mystudy", "mystudypw"); //대소문자 상관없음 ! 
		System.out.println(">> DB 연결 성공");
	}catch(SQLException e) {
		System.out.println(">>[예외] DB 연결 실패!!");
		e.printStackTrace();
	}
	
	//3. Statement문 실행(SQL문 실행)
	Statement stmt = null;
	try {
		//3-1. Connection 객체로부터 Statement 객체 생성
		stmt = conn.createStatement();
				
		//3-2. Statement문 실행(SQL 실행)
		String sql = "";
		sql += "DELETE FROM STUDENT WHERE ID = '2021002' ";//자바코드와 구분되게 하려고 대문자로 씀
									//습관적으로 맨 뒤에 한 칸 띄어주는게 좋음
		
		System.out.println("sql : " + sql);
		
		//INSERT, UPDATE, DELETE : executeUpdate()
		int result = stmt.executeUpdate(sql);
		
		//4. SQL 실행 결과에 대한 처리
		System.out.println("처리건수 : " + result);
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	//5. 클로징 처리에 의한 자원 반납(생성순서와 역순)
	try {
		
		stmt.close();
		System.out.println(">> Statement close 처리 완료");
	}catch(SQLException e) {
		e.printStackTrace();
	}
	try {
		conn.close();
		System.out.println(">> Connection close 처리 완료");
	}catch(SQLException e) {
		e.printStackTrace();
	}
	}
}
