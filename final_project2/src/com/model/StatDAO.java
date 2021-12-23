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

	public String statList(String id) { // �ȵ忡�� ����Ʈ �� �������� ����Ʈ ����Ʈ �����ֱ�

//		JsonArray arr = new JsonArray();
		String result = "";
		try {
			connection();

			// ***** ���⼭���� ���� �κ�
			String sql = "select a.*, z.push_tcnt, z.pull_tcnt, z.squart_tcnt from (select m_id, sum(pushup_cnt) push_cnt, sum(pullup_cnt) pull_cnt, sum(squat_cnt) squart_cnt from t_athletic where m_id = ? group by m_id) a, (select a.m_id, a.pull_tcnt, b.push_tcnt, c.squart_tcnt from (select m_id, timeattack_rec pull_tcnt from t_athletic where m_id = ? and time_mode = 'pull' order by timeattack_rec desc) a, (select m_id, timeattack_rec push_tcnt from t_athletic where m_id = ? and time_mode = 'push' order by timeattack_rec desc) b, (select m_id, timeattack_rec squart_tcnt from t_athletic where m_id = ? and time_mode = 'squart' order by timeattack_rec desc) c where a.m_id = b.m_id and b.m_id = c.m_id and rownum < 2) z where a.m_id = z.m_id";

			// ������ pst�� �غ�
			pst = conn.prepareStatement(sql);

			// pst�� �� set
			pst.setString(1, id);
			pst.setString(2, id);
			pst.setString(3, id);
			pst.setString(4, id);

			// pst���� �� rs�� ���
			rs = pst.executeQuery();

			while (rs.next()) {
				int push_cnt = rs.getInt("PUSH_CNT");
				int squart_cnt = rs.getInt("SQUART_CNT");
				int pull_cnt = rs.getInt("PULL_CNT");
				int push_tcnt = rs.getInt("PUSH_TCNT");
				int squart_tcnt = rs.getInt("SQUART_TCNT");
				int pull_tcnt = rs.getInt("PULL_TCNT");

				StatVO stat_VO = new StatVO(push_cnt, squart_cnt, pull_cnt, push_tcnt, squart_tcnt, pull_tcnt);

				// StatVO ��ü�� json ���·� �ٲ�
				result = new Gson().toJson(stat_VO);

			}

		} catch (Exception e) {
			System.out.println("DAO�� statList() ����(���ܹ߻�)");
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
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
