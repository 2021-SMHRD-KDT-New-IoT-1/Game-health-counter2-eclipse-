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

	public JsonArray rankList(String id) { // �ȵ忡�� ����Ʈ �� �������� ����Ʈ ����Ʈ �����ֱ�

		JsonArray arr = new JsonArray();

		try {
			connection();

			// ***** ���⼭���� ���� �κ�
			String sql = "select ROWNUM, temp.* from (select a.m_nickname, b.m_id, b.c_exp, b.lv from t_member a, (select m_id, c_exp, trunc(c_exp/100+1) lv from t_character) b where a.m_id = b.m_id order by b.c_exp desc) temp";
			// ������ pst�� �غ�
			pst = conn.prepareStatement(sql);

			// pst���� �� rs�� ���
			rs = pst.executeQuery();

			while (rs.next()) {
				int rowNum = rs.getInt("ROWNUM");
				String m_nickname = rs.getString("m_nickname");
				int c_level = rs.getInt("LV");
				int total_exp = rs.getInt("c_exp");

				RankVO rank_vo = new RankVO(rowNum, m_nickname, c_level, total_exp);

				// QuestVO ��ü�� json ���·� �ٲٰ� JsonArray�� add
				Gson gson = new Gson();
				String json = gson.toJson(rank_vo);
				arr.add(json);
			}
			

		} catch (Exception e) {
			System.out.println("DAO�� questList() ����(���ܹ߻�)");
			e.printStackTrace();
		} finally {
			close();
		}
		return arr;
	}

	public void connection() {

		try {
			// 1. ����̹� ���� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "campus_a_1_1214";
			String password = "smhrd1";
			// 2. ������ ���̽� ���� ��ä(Connection) ����
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�������");
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
