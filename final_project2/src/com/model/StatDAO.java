package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class StatDAO {
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public String statList(String id) { // 안드에서 퀘스트 탭 눌렀을시 퀘스트 리스트 보여주기

//		JsonArray arr = new JsonArray();
		String result = "";
		try {
			connection();

			// ***** 여기서부터 쿼리 부분
			String sql = "select a.*, z.push_tcnt, z.pull_tcnt, z.squart_tcnt from (select m_id, sum(pushup_cnt) push_cnt, sum(pullup_cnt) pull_cnt, sum(squat_cnt) squart_cnt from t_athletic where m_id = ? group by m_id) a, (select a.m_id, a.pull_tcnt, b.push_tcnt, c.squart_tcnt from (select m_id, timeattack_rec pull_tcnt from t_athletic where m_id = ? and time_mode = 'pull' order by timeattack_rec desc) a, (select m_id, timeattack_rec push_tcnt from t_athletic where m_id = ? and time_mode = 'push' order by timeattack_rec desc) b, (select m_id, timeattack_rec squart_tcnt from t_athletic where m_id = ? and time_mode = 'squart' order by timeattack_rec desc) c where a.m_id = b.m_id and b.m_id = c.m_id and rownum < 2) z where a.m_id = z.m_id";

			// 쿼리문 pst에 준비
			pst = conn.prepareStatement(sql);

			// pst에 값 set
			pst.setString(1, id);
			pst.setString(2, id);
			pst.setString(3, id);
			pst.setString(4, id);

			// pst실행 후 rs에 담기
			rs = pst.executeQuery();

			while (rs.next()) {
				int push_cnt = rs.getInt("PUSH_CNT");
				int squart_cnt = rs.getInt("SQUART_CNT");
				int pull_cnt = rs.getInt("PULL_CNT");
				int push_tcnt = rs.getInt("PUSH_TCNT");
				int squart_tcnt = rs.getInt("SQUART_TCNT");
				int pull_tcnt = rs.getInt("PULL_TCNT");

				StatVO stat_VO = new StatVO(push_cnt, squart_cnt, pull_cnt, push_tcnt, squart_tcnt, pull_tcnt);

				// StatVO 객체를 json 형태로 바꿈
				result = new Gson().toJson(stat_VO);

			}

		} catch (Exception e) {
			System.out.println("DAO의 statList() 실패(예외발생)");
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
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
