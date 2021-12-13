package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;


// 현재까지 만든 메서드 
// 로그인, 회원가입

public class MemberDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int cnt;
	MemberVO member_vo;
	CharVO char_vo;
	
	public String login(String id, String pwd) {
		String m_id = "로그인실패";
		try {
			connection();
			System.out.println("DB 연결 성공");

			// 회원 로그인
			String sql = "select * from t_member where m_id=? and m_pwd=?";
			 
			pst = conn.prepareStatement(sql);

			pst.setString(1, id);
			pst.setString(2, pwd);

			rs = pst.executeQuery();
			
			if (rs.next()) {
				// 로그인시 안드에서 필요한 정보  // 얘를 리턴할 것임.
				m_id = rs.getString("m_id");
				
				return m_id;
			
			}else {
				System.out.println("로그인실패(로그인조회실패)");
			}
		}catch (Exception e) {
				System.out.println("로그인 실패(예외 발생)");
				e.printStackTrace();
			} finally {
				close();
			}
		return m_id;
		}
	
	
	// 회원가입 메서드(MemberVO 리턴, 서블릿에서 안드에서 필요한 정보만 뽑아서 사용)
	public int join(String m_id, String m_pwd, String m_gender, String m_name, String m_nickname, String m_email,
			String m_phone, String m_push_yn, String admin_yn) {
		// joindate는 어차피 sysdate로 처리할 거니까 매개변수에서 뺐습니다.
			try {
				connection();
				
				String sql = "insert into values(?,?,?,?,?,?,?,?,TO_DATE(SYSDATE),?)";
				pst= conn.prepareStatement(sql);
				
				pst.setString(1, m_id);
				pst.setString(2, m_pwd);
				pst.setString(3, m_gender);
				pst.setString(4, m_name);
				pst.setString(5, m_nickname);
				pst.setString(6, m_email);
				pst.setString(7, m_phone);
				pst.setString(8, m_push_yn);
				pst.setString(9, admin_yn);
				
				cnt = pst.executeUpdate();
				
				if(cnt>0) {
					System.out.println("회원가입성공");
				}else {
					System.out.println("회원가입실패(insert실패)");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("회원가입실패(예외사항)");
			}finally {
				close();
				
		}
			return cnt;
		}
	
	
	
				
				
				
				
				
				
				
				
				
//				
//				
//				
//				if(member_vo != null) {
//					
//					// 회원 로그인 성공시, 회원 ID에 맞는 캐릭터 찾기
//					sql = "SELECT * FROM t_character WHERE m_id =?";
//
//					pst = conn.prepareStatement(sql);
//
//					pst.setString(1, id);
//
//					rs = pst.executeQuery();
//
//					if (rs.next()) {
//						System.out.println("로그인 성공");
//
//						int c_seq= rs.getInt("c_seq"); 
//						String c_name= rs.getString("c_name");
//						String c_memo = rs.getString("c_memo"); 
//						Date reg_date = rs.getDate("reg_date");
////						String m_id = rs.getString("m_id");
//						int c_level = rs.getInt("c_level");
//						String c_file = rs.getString("c_file");
//
//						char_vo = new CharVO(c_seq, c_name, c_memo, reg_date, m_id, c_level, c_file);
//						
//					} else {
//						System.out.println("로그인 실패(select했는데 없음)");
//					}
//				}
//				
//			} else {
//				System.out.println("로그인 실패(select했는데 없음)");
//			}
//
//		} catch (Exception e) {
//			System.out.println("로그인 실패(예외 발생)");
//			e.printStackTrace();
//			
//		} finally {
//			close();
//		}
//		return char_vo;
//	}
//	
//	
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void connection() {

		try {

			// 1. 드라이버 동적 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

//			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
//			String user = "campus_a_5_1025";
//			String password = "smhrd5";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";
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
