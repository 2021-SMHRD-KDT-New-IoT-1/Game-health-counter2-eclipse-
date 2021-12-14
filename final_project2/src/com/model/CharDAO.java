package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CharDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int cnt;
	
	
	// 회원가입시 무조건 캐릭터 생성
	public int CharCreat(String m_id) {
				try {
					connection();
					String sql = "insert into t_character values(T_CHARACTER_SEQ.nextval,'',?,1,0)";
					pst= conn.prepareStatement(sql);
					
					pst.setString(1, m_id);
					
					cnt = pst.executeUpdate();
					
					if(cnt>0) {
						System.out.println("캐릭터 생성 성공");
					}else {
						System.out.println("캐릭터 생성 실패(insert실패)");
					}
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println("캐릭터 생성 실패(예외사항)");
				}finally {
					close();
					
			}
				return cnt;
			}
	
	
	
	// 캐릭터 level 불러오기
	public int CharLv(String m_id) {
		int c_level = -1;
		
		try {
			connection();
			System.out.println("DB 연결 성공");

			// 회원 로그인
			String sql = "select c_level from t_character where m_id=?";
			 
			pst = conn.prepareStatement(sql);

			pst.setString(1, m_id);

			rs = pst.executeQuery();
			
			if (rs.next()) {
				c_level = rs.getInt("c_level");
			
			}else {
				System.out.println("level 조회실패");
			}
		}catch (Exception e) {
				System.out.println("로그인 실패(예외 발생)");
				e.printStackTrace();
			} finally {
				close();
			}
		return c_level;
	}
	
	
	
	// 캐릭터 누적 Exp 불러오기
	public int CharExp(String m_id) {
		int c_exp = -1;
		
		try {
			connection();
			System.out.println("DB 연결 성공");

			
			String sql = "";
			 
			pst = conn.prepareStatement(sql);

			pst.setString(1, m_id);

			rs = pst.executeQuery();
			
			if (rs.next()) {
				c_exp = rs.getInt("c_exp");
			
			}else {
				System.out.println("exp 조회실패");
			}
		}catch (Exception e) {
				System.out.println("exp 조회실패(예외 발생)");
				e.printStackTrace();
			} finally {
				close();
			}
		return c_exp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	public void connection() {

		try {

			// 1. 드라이버 동적 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "campus_a_1_1214";
			String password = "smhrd1";
			// 2. 데이터 베이스 연결 객채(Connection) 생성
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("연결실패");
		}

	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
}
