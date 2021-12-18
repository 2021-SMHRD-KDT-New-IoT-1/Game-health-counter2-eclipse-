package com.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

public class QuestDAO {

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public JsonArray questList(String id) { // �ȵ忡�� ����Ʈ �� �������� ����Ʈ ����Ʈ �����ֱ�

		JsonArray arr = new JsonArray();

		try {
			connection();

			// ***** ���⼭���� ���� �κ�
			String sql = "select * from t_quest where m_id=? order by q_seq asc";

			// ������ pst�� �غ�
			pst = conn.prepareStatement(sql);

			// pst�� �� set
			pst.setString(1, id);
//				pst.setInt(2, num);

			// pst���� �� rs�� ���
			rs = pst.executeQuery();

			while (rs.next()) {
				String q_name = rs.getString("q_name");
				int q_cnt = rs.getInt("q_cnt");
				String q_check = rs.getString("q_check");
				int q_exp = rs.getInt("q_exp");
				Date reg_date = rs.getDate("reg_date");

				QuestVO quest_VO = new QuestVO(q_name, q_cnt, q_check, q_exp, reg_date);

				// QuestVO ��ü�� json ���·� �ٲٰ� JsonArray�� add
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); // DateFormat������ ����
				String json = gson.toJson(quest_VO);
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
	
//	// *** 0~4 ���� ������ �ߺ��� ������� �ʰ� 3�� �̱�
//	int num = 0;
//
//	Set<Integer> set = new HashSet<>();
//
//	while(set.size()<3) {
//		Double d = Math.random() * 5;
//		set.add(d.intValue());
//	}
//
//	List<Integer> list = new ArrayList<>(set);Collections.sort(list);
//
//	System.out.println("���� ��: ");
//
//	for(int i = 0;i<3;i++) {
//		num = list.get(i);
//		// ���� ���� Ȯ�ο�
//		System.out.println(num);
//	}

	
}
