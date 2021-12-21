package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class SensorDAO {

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	
	
	public void rankList(String id) { 

		JsonArray arr = new JsonArray();

		try {
			connection();

			String sql = "select rowNum, a.m_nickname, b.c_level, b.total_exp from t_member a, (select * from (select m_id, c_level, sum(q_exp+applier_exp) as total_exp from (select a.m_id, a.c_level, b.q_exp, b.q_check, c.applier_exp from t_character a, (select b.m_id, b.q_exp, b.q_check from t_quest b where b.q_check = 'Y') b, (select c.m_id, c.applier_exp from T_RAID_APPLIER c where c.raid_seq = 12) c where a.m_id = b.m_id(+) and b.m_id = c.m_id(+) and not a.m_id = 'admin' order by m_id) GROUP BY m_id, c_level) where total_exp is not null order by total_exp desc) b where a.m_id = b.m_id";

			pst = conn.prepareStatement(sql);

			rs = pst.executeQuery();

			while (rs.next()) {
				int rowNum = rs.getInt("rownum");
				String m_nickname = rs.getString("m_nickname");
				int c_level = rs.getInt("c_level");
				int total_exp = rs.getInt("total_exp");

				RankVO rank_vo = new RankVO(rowNum, m_nickname, c_level, total_exp);

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