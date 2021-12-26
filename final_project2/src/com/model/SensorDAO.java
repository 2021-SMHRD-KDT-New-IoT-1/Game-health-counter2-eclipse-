package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class SensorDAO {

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int cnt;
	
	// 임시로 serial 변수를 m_id = 'bang9'로 대체 했음
	public void getSensor(String push_cnt, String pull_cnt, String sqt_cnt, String serial, String time_mode) { 

		try {
			connection();

			String sql = "insert into t_athletic (PUSHUP_CNT, PULLUP_CNT, SQUAT_CNT, M_ID, TIME_MODE) values(?, ?, ?, ?, ?)";

			pst = conn.prepareStatement(sql);
			
			pst.setString(1, push_cnt);
			pst.setString(2, pull_cnt);
			pst.setString(3, sqt_cnt);
			pst.setString(4, serial);
			pst.setString(5, time_mode);

			cnt = pst.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("센서값 DB에 저장 성공");
			} else {
				System.out.println("센서값 DB에 저장 실패(insert실패)");
			}

			
		} catch (Exception e) {
			System.out.println("DAO의 getSensor() 실패(예외발생)");
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	// 임시로  m_id = 'bang9', raid_seq = 12 로 대체 했음
	// 레이드 어플라이어 테이블에 레코드 컬럼 업데이트 
	public void updateRecord(String push_cnt, String pull_cnt, String sqt_cnt, String m_id, int raid_seq) { 

		try {
			connection();

			String sql = "update t_raid_applier set applier_record = (select sum(applier_record+?) from t_raid_applier where m_id = ? and raid_seq = ?) where m_id = ? and raid_seq = ?";

			pst = conn.prepareStatement(sql);
			
			pst.setString(1, pull_cnt);
			pst.setString(2, m_id);
			pst.setInt(3, raid_seq);
			pst.setString(4, m_id);
			pst.setInt(5, raid_seq);

			cnt = pst.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("레이드 레코드 업데이트 성공");
			} else {
				System.out.println("레이드 레코드 업데이트(update 실패)");
			}

			
		} catch (Exception e) {
			System.out.println("DAO의 updateRecord() 실패(예외발생)");
			e.printStackTrace();
		} finally {
			close();
		}
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