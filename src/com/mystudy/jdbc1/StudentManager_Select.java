package com.mystudy.jdbc1;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class StudentManager_Select {
	
	public static void main (String[] args) {
	//JDBC를 이용한 DB 프로그래밍 절차 ------------------------------
	//0. JDBC 라이브러리 개발환경 설정(빌드경로에 등록)
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
								//localhost대신에 127.0.0.1:1521:xe 가능
				"mystudy", "mystudypw"); //대소문자 상관없음 ! 
		System.out.println(">> DB 연결 성공");
	}catch(SQLException e) {
		System.out.println(">>[예외] DB 연결 실패!!");
		e.printStackTrace();
	}
	
	//3. Statement문 실행(SQL문 실행)
	Statement stmt = null;
	ResultSet rs = null; //SELECT 결과값 리턴 담기 위한 변수
	
	try {
		//Connection 객체로부터 Statement 객체 생성
		stmt = conn.createStatement();
		String sql = "";
		sql += "SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG "; 
//		sql += "SELECT ID, NAME, KOR, ENG, MATH, TOT, AVGFROM STUDENTORDER BY NAME ";
		//띠어쓰기 안하면 이렇게 됨 ... 
		sql += "  FROM STUDENT ";
		sql += " ORDER BY NAME";
		
		rs = stmt.executeQuery(sql);
		
		//4. SQL 실행 결과에 대한 처리
		//   -SELECT : 조회(검색) 데이타 결과 값에 대한 처리
		System.out.println(">> SELECT 결과처리(화면출력)");
		while (rs.next()) {
			String str = "";
			str += rs.getString("ID") + ", ";
			str += rs.getString("NAME") + ", ";
			str += rs.getInt("KOR") + ", ";
			str += rs.getInt("ENG") + ", ";
			str += rs.getInt("MATH") + ", ";
			str += rs.getInt("TOT") + ", ";
			str += rs.getDouble("AVG") + ", ";
			System.out.println(str);
		}
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	
	//5. 클로징 처리에 의한 자원 반납
	try {
		if (rs != null) rs.close();
		System.out.println(">> ResultSet close 처리");
	}catch(SQLException e) {
		e.printStackTrace();
	}
	try {
		if( stmt != null )stmt.close(); //해줘야 더 좋음
		stmt.close();
		System.out.println(">> Statement close 처리 완료");
	}catch(SQLException e) {
		e.printStackTrace();
	}
	try {
		if( conn != null )conn.close();
		conn.close();
		System.out.println(">> Connection close 처리 완료");
	}catch(SQLException e) {
		e.printStackTrace();
	}
	}
}
