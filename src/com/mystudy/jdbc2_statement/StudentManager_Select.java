package com.mystudy.jdbc2_statement;

import java.sql.Connection;
import java.sql.DriverManager;
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
		Statement stmt = null;
		//달라지는 거 333
		ResultSet rs = null;

		try {
			//1. JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. DB연결  - Connection 객체 생성 <-DriverManager
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. Statement문 실행(SQL문 실행)
			//3-1. Connection 객체로 부터 Statement 객체 생성
			stmt = conn.createStatement();
			
			//3-2. Statement 객체를 사용해서 SQL문 실행
			String id = "ID2021002";
			
			//sql문장 부분만 달라짐 11
			String sql = "";
			sql += "SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG ";
			sql += "	FROM STUDENT ";
//			sql += " WHERE ID = 'ID2021001'";
			sql += " WHERE ID = '"+ id + "' ";
			System.out.println("sql : " + sql);
			//실행메소드도 달라짐!!2 (Update가 아니라 Query로)
			rs= stmt.executeQuery(sql);
			//rs도 만들었기 때문에 int result 이렇게 안해줘도 된다.
			
			
			//4. SQL 실행 결과에 대한 처리
			//   -SELECT : 조회(검색) 데이타 결과 값에 대한 처리
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
				if (stmt != null) stmt.close();
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