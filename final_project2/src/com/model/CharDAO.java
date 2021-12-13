package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CharDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int cnt;
	
	
	// ȸ�����Խ� ������ ĳ���� ����
	public int CharCreat(String m_id) {
		int level = 1; // ȸ�����Խ� ó�� �ο��Ǵ� ����
				try {
					connection();
					
					String sql = "insert into t_character values(T_CHARACTER_SEQ.nextval,'-','-',TO_DATE(SYSDATE),?,?,'-')";
					pst= conn.prepareStatement(sql);
					
					pst.setString(1, m_id);
					pst.setInt(2, level);
					
					cnt = pst.executeUpdate();
					
					if(cnt>0) {
						System.out.println("ĳ���� ���� ����");
					}else {
						System.out.println("ĳ���� ���� ����(insert����)");
					}
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println("ĳ���� ���� ����(���ܻ���)");
				}finally {
					close();
					
			}
				return cnt;
			}
	
	
	
	// ĳ���� lv �ҷ�����
	public int CharLv(String m_id) {
		int c_level = -1;
		
		try {
			connection();
			System.out.println("DB ���� ����");

			// ȸ�� �α���
			String sql = "select c_level from t_character where m_id=?";
			 
			pst = conn.prepareStatement(sql);

			pst.setString(1, m_id);

			rs = pst.executeQuery();
			
			if (rs.next()) {
				c_level = rs.getInt("c_level");
			
			}else {
				System.out.println("level ��ȸ����");
			}
		}catch (Exception e) {
				System.out.println("�α��� ����(���� �߻�)");
				e.printStackTrace();
			} finally {
				close();
			}
		return c_level;
	}
	
	
	
	// ĳ���� Exp �ҷ�����
	public int CharExp(String m_id) {
		int c_exp = -1;
		
		try {
			connection();
			System.out.println("DB ���� ����");

			// ȸ�� �α���
			String sql = "select c_level from t_character where m_id=?";
			 
			pst = conn.prepareStatement(sql);

			pst.setString(1, m_id);

			rs = pst.executeQuery();
			
			if (rs.next()) {
				c_exp = rs.getInt("c_level");
			
			}else {
				System.out.println("level ��ȸ����");
			}
		}catch (Exception e) {
				System.out.println("�α��� ����(���� �߻�)");
				e.printStackTrace();
			} finally {
				close();
			}
		return c_exp;
	}
	
	
	
	
	
	
	
	
	
	


	public void connection() {

		try {

			// 1. ����̹� ���� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//		String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			//		String user = "campus_a_5_1025";
			//		String password = "smhrd5";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";
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
