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
	int cnt =0;
	
	// 퀘스트 조건 만족하는 데이터 검색해서 questCheck를 'Y'로 바꿔주는 메소드
	public int questCheck(String id) {
		
		int get_push =0;
		int get_pull =0;
		int get_squart =0;
		
		int get_cnt=0;
		String get_check="";
		int get_label=0;
		int get_exp=0;

		try {
			
			connection();

			String sql = "select m_id, to_char(reg_date,'yyyymmdd') as TODAY, sum(pushup_cnt) as PUSH, sum(pullup_cnt) as PULL, sum(squat_cnt) as SQUART from t_athletic where to_char(reg_date,'yyyymmdd') = to_char(sysdate,'yyyymmdd') AND m_id = ? group by m_id, to_char(reg_date,'yyyymmdd')";
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);

			rs = pst.executeQuery();

			if (rs.next()) {
				System.out.println("오늘 운동한 총합 갯수 조회(퀘스트용) 성공!");

				get_push = rs.getInt(3);
				get_pull = rs.getInt(4);
				get_squart = rs.getInt(5);
				
				// q_label =0(푸쉬업 계열)인 퀘스트 조회
				sql = "select m_id, q_cnt, q_check, q_label from t_quest where m_id =? and q_label = 0";
				pst = conn.prepareStatement(sql);
				pst.setString(1, id);
				
				rs = pst.executeQuery();

				if (rs.next()) {
					System.out.println("q_label =0(푸쉬업 계열)인 퀘스트 조회(퀘스트용) 성공!");

					get_cnt = rs.getInt(2);
					get_check = rs.getString(3);
					get_label = rs.getInt(4);
					System.out.println("get_push: "+get_push+"get_cnt: "+get_cnt+", get_check: "+get_check+", get_label: "+get_label);

				} else {
					System.out.println("q_label =0(푸쉬업 계열)인 퀘스트 조회(퀘스트용) 실패!");
				}
				
				// get_push >= get_cnt 이면 q_check를 Y로 업데이트
				if(get_push >= get_cnt && get_check.equals("N")) {
					
					sql = "update t_quest set q_check = 'Y' where m_id = ? and q_label = 0";
					pst = conn.prepareStatement(sql);
					pst.setString(1, id);

					cnt = pst.executeUpdate();
					
					if (cnt > 0) {
						System.out.println("q_check (푸쉬업 계열)업데이트 성공(N -> Y)");
					} else {
						System.out.println("q_check (푸쉬업 계열)업데이트 실패");
					}
					
					sql = "update t_character set c_exp = (select sum(c_exp+?) updated_exp from t_character where m_id = ?) where m_id = ?";
					pst = conn.prepareStatement(sql);
					pst.setInt(1, get_exp);
					pst.setString(2, id);
					pst.setString(3, id);

					cnt = pst.executeUpdate();
					
					if (cnt > 0) {
						System.out.println("q_check (푸쉬업 계열)경험치 업데이트 성공(N -> Y)");
					} else {
						System.out.println("q_check (푸쉬업 계열)경험치 업데이트 실패");
					}
				}
				
				
				// q_label =1(풀업 계열)인 퀘스트 조회
				sql = "select m_id, q_cnt, q_check, q_label, q_exp from t_quest where m_id =? and q_label = 1";
				pst = conn.prepareStatement(sql);
				pst.setString(1, id);
				
				rs = pst.executeQuery();

				if (rs.next()) {
					System.out.println("q_label =1(풀업 계열)인 퀘스트 조회(퀘스트용) 성공!");

					get_cnt = rs.getInt(2);
					get_check = rs.getString(3);
					get_label = rs.getInt(4);
					get_exp = rs.getInt(5);
					System.out.println("get_pull: "+get_pull+"get_cnt: "+get_cnt+", "
							+ "get_check: "+get_check+", get_label: "+get_label+", get_exp: "+get_exp);

				} else {
					System.out.println("q_label =1(풀업 계열)인 퀘스트 조회(퀘스트용) 실패!");
				}
				
				// get_pull >= get_cnt 이면 q_check를 Y로 업데이트
				if(get_pull >= get_cnt && get_check.equals("N")) {
					
					sql = "update t_quest set q_check = 'Y' where m_id = ? and q_label = 1";
					pst = conn.prepareStatement(sql);
					pst.setString(1, id);

					cnt = pst.executeUpdate();
					
					if (cnt > 0) {
						System.out.println("q_check (풀업 계열)업데이트 성공(N -> Y)");
					} else {
						System.out.println("q_check (풀업 계열)업데이트 실패");
					}
					
					sql = "update t_character set c_exp = (select sum(c_exp+?) updated_exp from t_character where m_id = ?) where m_id = ?";
					pst = conn.prepareStatement(sql);
					pst.setInt(1, get_exp);
					pst.setString(2, id);
					pst.setString(3, id);

					cnt = pst.executeUpdate();
					
					if (cnt > 0) {
						System.out.println("q_check (풀업 계열)경험치 업데이트 성공(N -> Y)");
					} else {
						System.out.println("q_check (풀업 계열)경험치 업데이트 실패");
					}
					
					
				}
				
				
				// q_label =2(스쿼트 계열)인 퀘스트 조회
				sql = "select m_id, q_cnt, q_check, q_label from t_quest where m_id =? and q_label = 2";
				pst = conn.prepareStatement(sql);
				pst.setString(1, id);
				
				rs = pst.executeQuery();

				if (rs.next()) {
					System.out.println("q_label =2(스쿼트 계열)인 퀘스트 조회(퀘스트용) 성공!");

					get_cnt = rs.getInt(2);
					get_check = rs.getString(3);
					get_label = rs.getInt(4);
					System.out.println("get_squart: "+get_squart+"get_cnt: "+get_cnt+", get_check: "+get_check+", get_label: "+get_label);

				} else {
					System.out.println("q_label =2(스쿼트 계열)인 퀘스트 조회(퀘스트용) 실패!");
				}
				
				// get_squart >= get_cnt 이면 q_check를 Y로 업데이트
				if(get_squart >= get_cnt && get_check.equals("N")) {
					
					sql = "update t_quest set q_check = 'Y' where m_id = ? and q_label = 2";
					pst = conn.prepareStatement(sql);
					pst.setString(1, id);

					cnt = pst.executeUpdate();
					
					if (cnt > 0) {
						System.out.println("q_check (스쿼트 계열)업데이트 성공(N -> Y)");
					} else {
						System.out.println("q_check (스쿼트 계열)업데이트 실패");
					}
					
					sql = "update t_character set c_exp = (select sum(c_exp+?) updated_exp from t_character where m_id = ?) where m_id = ?";
					pst = conn.prepareStatement(sql);
					pst.setInt(1, get_exp);
					pst.setString(2, id);
					pst.setString(3, id);

					cnt = pst.executeUpdate();
					
					if (cnt > 0) {
						System.out.println("q_check (스쿼트 계열)경험치 업데이트 성공(N -> Y)");
					} else {
						System.out.println("q_check (스쿼트 계열)경험치 업데이트 실패");
					}
					
				}
				
				
				
			} else {
				System.out.println("운동한 총합 갯수 조회(퀘스트용) 실패!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("questCheck() 실패!(예외발생)");
		} finally {
			close();
		}
		return cnt;
	}
	
	// 안드에서 퀘스트 탭 눌렀을시 퀘스트 리스트 보여주기
	public JsonArray questList(String id) { 

		JsonArray arr = new JsonArray();

		try {
			connection();

			// ***** 여기서부터 쿼리 부분
			String sql = "select * from t_quest where m_id=? order by q_seq asc";

			// 쿼리문 pst에 준비
			pst = conn.prepareStatement(sql);

			// pst에 값 set
			pst.setString(1, id);
//				pst.setInt(2, num);

			// pst실행 후 rs에 담기
			rs = pst.executeQuery();

			while (rs.next()) {
				String q_name = rs.getString("q_name");
				int q_cnt = rs.getInt("q_cnt");
				String q_check = rs.getString("q_check");
				int q_exp = rs.getInt("q_exp");
				Date reg_date = rs.getDate("reg_date");

				QuestVO quest_VO = new QuestVO(q_name, q_cnt, q_check, q_exp, reg_date);

				// QuestVO 객체를 json 형태로 바꾸고 JsonArray에 add
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); // DateFormat변경후 생성
				String json = gson.toJson(quest_VO);
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
	
//	// *** 0~4 랜덤 수에서 중복을 허용하지 않고 3개 뽑기
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
//	System.out.println("뽑은 수: ");
//
//	for(int i = 0;i<3;i++) {
//		num = list.get(i);
//		// 뽑은 숫자 확인용
//		System.out.println(num);
//	}

	
}
