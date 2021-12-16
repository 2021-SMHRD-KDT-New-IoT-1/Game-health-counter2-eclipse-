package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AthleDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	// 운동  기록 조회
	public String look(String date, String id) {
		
		int push_cnt = 0;
		int pull_cnt = 0;
		int sqt_cnt = 0;
		
		try {
			connection();
			System.out.println("DB 연결 성공");

			// 운동  기록 조회
			String sql = "select sum(pushup_cnt) as PUSH, sum(pullup_cnt) as PULL, sum(squat_cnt) as SQUART"
					+ " from t_athletic where to_char(reg_date,'yyyymmdd')=? AND m_id=?";

			pst = conn.prepareStatement(sql);

			pst.setString(1, date);
			pst.setString(2, id);

			rs = pst.executeQuery();

			if (rs.next()) {
				System.out.println("운동 조회 성공");
				
				push_cnt = rs.getInt("PUSH");
				pull_cnt = rs.getInt("PULL");
				sqt_cnt = rs.getInt("SQUART");

			} else {
				System.out.println("운동 조회 실패");
			}
		} catch (Exception e) {
			System.out.println("운동 조회 실패(예외 발생)");
			e.printStackTrace();
		} finally {
			close();
		}
		return push_cnt+","+pull_cnt+","+sqt_cnt;
	}
	
	
	// 타임 어택 모드 기록 조회
	public int time_look(String date, String id, String mode) {
		
		int time_cnt = 0;
		
		try {
			connection();
			System.out.println("DB 연결 성공");

			// 타임 어택 모드 기록 조회
			String sql = "select adate, max(timeattack_rec) as TIME_REC from (select to_char(reg_date,'yyyymmdd') as adate, timeattack_rec from t_athletic where m_id=? AND to_char(reg_date,'yyyymmdd')=? AND time_mode =?) group by adate";

			pst = conn.prepareStatement(sql);

			pst.setString(1, id);
			pst.setString(2, date);
			pst.setString(3, mode);

			rs = pst.executeQuery();

			if (rs.next()) {
				System.out.println("타임 어택 모드 기록 조회 성공");
				
				time_cnt = rs.getInt("TIME_REC");

			} else {
				System.out.println("타임 어택 모드 기록 조회 실패");
			}
		} catch (Exception e) {
			System.out.println("타임 어택 모드 기록 조회 실패(예외 발생)");
			e.printStackTrace();
		} finally {
			close();
		}
		return time_cnt;
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
