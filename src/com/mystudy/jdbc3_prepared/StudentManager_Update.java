package com.mystudy.jdbc3_prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentManager_Update {

	public static void main(String[] args) {
		//JDBC를 이용한 DB 프로그래밍 절차 ------------------------------
		//0. JDBC 라이브러리 개발환경 설정(빌드경로에 등록)
		//1. JDBC 드라이버 로딩
		//2. DB연결  - Connection 객체 생성 <-DriverManager
		//3. Statement문 실행(SQL문 실행)
		//4. SQL 실행 결과에 대한 처리
		//   -SELECT : 조회(검색) 데이타 결과 값에 대한 처리
		//   -INSERT, UPDATE, DELETE : int값(건수) 처리
		//5. 클로징 처리에 의한 자원 반납
		//---------------------------------------
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. DB연결  - Connection 객체 생성 <-DriverManager
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. Statement문 실행(SQL문 실행)
			//3-1. SQL문 실행을 위한 준비(PreparedStatement 객체 만들기)
			String sql = "";
			sql += "UPDATE STUDENT ";
			sql += "   SET NAME = ? "; //1
			sql += "     , KOR = ? ";  //2
			sql += "     , ENG = ? ";  //3
			sql += "     , MATH = ? "; //4
			sql += " WHERE ID = ? ";   //5
			pstmt = conn.prepareStatement(sql);
			
			//3-2. SQL문장의 ? 위치에 값 매칭(대입)
			String id = "ID2021011";
			String name = "홍길동12";
			int kor = 92;
			int eng = 82;
			int math = 72;
			
			pstmt.setString(1, name);
			pstmt.setInt(2, kor);
			pstmt.setInt(3, eng);
			pstmt.setInt(4, math);
			pstmt.setString(5, id);
			
			//3-3. SQL문 실행
			int result = pstmt.executeUpdate();
			
			//4. SQL문 실행 후 처리
			System.out.println(">> 처리건수 : " + result);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}









