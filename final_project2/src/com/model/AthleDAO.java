package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AthleDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public String look(String date, String id) {
		
		int push_cnt = 0;
		int pull_cnt = 0;
		int sqt_cnt = 0;
		
		try {
			connection();
			System.out.println("DB ���� ����");

			// �  ��� ��ȸ
			String sql = "select sum(pushup_cnt) as PUSH, sum(pullup_cnt) as PULL, sum(squat_cnt) as SQUART"
					+ " from t_athletic where to_char(reg_date,'yyyymmdd')=? AND m_id=?";

			pst = conn.prepareStatement(sql);

			pst.setString(1, date);
			pst.setString(2, id);

			rs = pst.executeQuery();

			if (rs.next()) {
				System.out.println("� ��ȸ ����");
				
				push_cnt = rs.getInt("PUSH");
				pull_cnt = rs.getInt("PULL");
				sqt_cnt = rs.getInt("SQUART");

			} else {
				System.out.println("� ��ȸ ����");
			}
		} catch (Exception e) {
			System.out.println("� ��ȸ ����(���� �߻�)");
			e.printStackTrace();
		} finally {
			close();
		}
		return push_cnt+","+pull_cnt+","+sqt_cnt;
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
