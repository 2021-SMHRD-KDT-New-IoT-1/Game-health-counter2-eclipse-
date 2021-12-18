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

	public JsonArray questList(String id) { // 안드에서 퀘스트 탭 눌렀을시 퀘스트 리스트 보여주기

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
