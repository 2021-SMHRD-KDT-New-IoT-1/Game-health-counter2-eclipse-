package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int cnt;
	MemberVO member_vo;
	
	public MemberVO login(String id, String pwd) {
		try {
			connection();
			System.out.println("DB ���� ����");

			String sql = "select * from t_member where m_id=? and m_pwd=?";

			pst = conn.prepareStatement(sql);

			pst.setString(1, id);
			pst.setString(2, pwd);

			rs = pst.executeQuery();

			if (rs.next()) {
				System.out.println("�α��� ����");

				String m_id= rs.getString("m_id"); 
				String m_pwd= rs.getString("m_pwd");
				String m_gender = rs.getString("m_gender");
				String m_name = rs.getString("m_name");
				String m_nickname = rs.getString("m_nickname");
				String m_email = rs.getString("m_email");
				String m_phone = rs.getString("m_phone");
				String m_push_yn = rs.getString("m_push_yn");
				Date m_joindate = rs.getDate("m_joindate");
				String admin_yn = rs.getString("admin_yn");

				member_vo = new MemberVO(m_id, m_pwd, m_gender, m_name, 
						m_nickname, m_email, m_phone, m_push_yn, m_joindate, admin_yn);
				
			} else {
				System.out.println("�α��� ����(select�ߴµ� ����)");
			}

		} catch (Exception e) {
			System.out.println("�α��� ����(���� �߻�)");
			e.printStackTrace();
			
		} finally {
			close();
		}
		return member_vo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void connection() {

		try {

			// 1. ����̹� ���� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "campus_a_5_1025";
			String password = "smhrd5";
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
