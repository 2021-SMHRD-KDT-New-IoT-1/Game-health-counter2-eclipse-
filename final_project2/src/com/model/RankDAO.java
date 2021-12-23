package com.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class RankDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public JsonArray rankList(String id) { // 안드에서 퀘스트 탭 눌렀을시 퀘스트 리스트 보여주기

		JsonArray arr = new JsonArray();

		try {
			connection();

			// ***** 여기서부터 쿼리 부분
			String sql = "select ROWNUM, temp.* from (select a.m_nickname, b.m_id, b.c_exp, b.lv from t_member a, (select m_id, c_exp, trunc(c_exp/100+1) lv from t_character) b where a.m_id = b.m_id order by b.c_exp desc) temp";
			// 쿼리문 pst에 준비
			pst = conn.prepareStatement(sql);

			// pst실행 후 rs에 담기
			rs = pst.executeQuery();

			while (rs.next()) {
				int rowNum = rs.getInt("ROWNUM");
				String m_nickname = rs.getString("m_nickname");
				int c_level = rs.getInt("LV");
				int total_exp = rs.getInt("c_exp");

				RankVO rank_vo = new RankVO(rowNum, m_nickname, c_level, total_exp);

				// QuestVO 객체를 json 형태로 바꾸고 JsonArray에 add
				Gson gson = new Gson();
				String json = gson.toJson(rank_vo);
				arr.add(json);
			}
			

		} catch (Exception e) {
			System.out.println("DAO의 questList() 실패(예외발생)");
			e.printStackTrace();
		} finally {
			close();
		}
		return arr;
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
