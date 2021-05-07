package com.mystudy.jdbc3_prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager_Select {

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
		ResultSet rs = null;
		

		try {
			//1. JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. DB연결  - Connection 객체 생성 <-DriverManager
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. Statement문 실행(SQL문 실행)
			//3-1. Connection 객체로 부터 PreparedStatement 객체 생성
			String sql = "";
			sql += "SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG ";
			sql += "  FROM STUDENT ";
			//sql += " WHERE ID = ? ";
			sql += " ORDER BY ID ";
			pstmt = conn.prepareStatement(sql); 
			//달라진 부분!!!!! (create가 아니라 prepare)
			
			//3-2. ?(바인드변수) 위치에 값 대입
			String id = "ID2021001";
			pstmt.setString(1, id);
			
			//3-3. Statement(PreparedStatement) 실행
			rs = pstmt.executeQuery();
			
			//4.SQL 실행결과에 대한 처리
			while(rs.next()) {
				String str = "";
				str += rs.getString(1) + ", ";
				str += rs.getString(2) + ", ";
				str += rs.getInt(3) + ", ";
				str += rs.getInt(4) + ", ";
				str += rs.getInt(5) + ", ";
				str += rs.getInt(6) + ", ";
				str += rs.getDouble(7);
				System.out.println(str);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. 클로징 처리에 의한 자원 반납
			try {
				if (rs != null) rs.close();
				System.out.println(">> ResultSet close 처리 완료");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (pstmt != null) pstmt.close();
				System.out.println(">> Statement close 처리 완료");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
				System.out.println(">> Connection close 처리 완료");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
