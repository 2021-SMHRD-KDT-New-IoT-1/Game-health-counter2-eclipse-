package com.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

public class RaidDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int cnt;
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); //  DateFormat변경후 생성
	
//	ArrayList<Integer> raidSeq_al = new ArrayList<Integer>();
	ArrayList<RaidVO> raidVO_al = new ArrayList<RaidVO>();
	
	
	public ArrayList<RaidVO> raidInfo(String id) { // 레이드버튼 눌렀을 때 레이드화면 정보
		// 여기서 레이드 세개 다 가져온 다음에
		// 안드에서 참가중인지 아닌지 알려주기.
		JsonArray arr = new JsonArray();
		try {
			connection();

				String sql = "select * from (select * from T_RAID order by RAID_SEQ desc) where rownum < 4";
				
				pst = conn.prepareStatement(sql);
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
			
				while(rs.next()){
					String raid_seq = rs.getString("raid_seq");
					String raid_kind = rs.getString("raid_kind");
					String raid_name = rs.getString("raid_name");
					String raid_cnt = rs.getString("raid_mission");
					Date reg_date = rs.getDate("reg_date");
				
					RaidVO raid_vo = new RaidVO(raid_seq, raid_kind, raid_name, raid_cnt, reg_date,"false");
					raidVO_al.add(raid_vo);
	
	
			
					    
					
					// 참가중인 레이드 알려주기
					// 참가하기 누르면 참가중으로 바뀌고 다시 그 화면 갔을 때 참가중인 거는 유지가 되어야 한다.
					// 내가 이 시퀀스를 넘겨주면 안드에서 참가중인 시퀀스 보관해놨다가 그 시퀀스만 참여중으로 바꾼다.
					// sharedPreference 
					
					}
				
		}catch (Exception e) {
				System.out.println("DAO의 raidInfo() 실패(예외발생)");
				e.printStackTrace();
			} finally {
				close();
			}
		return raidVO_al;
	}
	
	
	public String[] appRecord(String m_id, String raid_seq){
		// 참여중인 레이드 시퀀스를 안드에서 받아온다. 안드에도 raid객체가 있어야한다..?? 의문이 들긴 하지만 있다는 가정하에
		// 여기서 뽑아줄 정보 
		// 내가 한 개수
		String[] result = {"-1","-1"};
			
		try {
			connection();

				String sql = "select applier_record, raid_seq from t_raid_applier where m_id = ? and raid_seq = ?";
				
				pst = conn.prepareStatement(sql);
				pst.setString(1,m_id);
				pst.setString(2,raid_seq);
				
				rs = pst.executeQuery();
				
			
				if(rs.next()){
					String applier_record = rs.getString("applier_record");
					String raid_seq1 = rs.getString("raid_seq");
					System.out.println("DB조회 성공 : appRecord()");
					
					result[0] = applier_record;
					result[1] = raid_seq1;
					
					return result;
					}else {
						System.out.println("DB조회 실패 : appRecord()");
					}
				
				
		}catch (Exception e) {
				System.out.println("예외발생 : DAO의 appRecord()");
				e.printStackTrace();
			} finally {
				close();
			}
		
		return result;
	}
	
	
	// 해당 레이드에 참가자가 기여한 총 횟수(총!!!!!)
	public int raidAllRecord(String raid_seq){

		int applier_record = 0;
		
		try {
			connection();

				String sql = "select sum(applier_record) from t_raid_applier where raid_seq = ?";
				
				pst = conn.prepareStatement(sql);
				pst.setString(1,raid_seq);
				
				rs = pst.executeQuery();
				
			
				if(rs.next()){
					applier_record = rs.getInt("sum(applier_record)");
					System.out.println("DB조회 성공 : raidAllRecord()");
					return applier_record;
					
					
					}else {
						System.out.println("DB조회 실패 : raidAllRecord()");
						
					}
				
				
		}catch (Exception e) {
				System.out.println("DAO의 riadAllRecord()실패(예외발생)");
				e.printStackTrace();
			} finally {
				close();
			}
		return applier_record;
	}

	
	
	
	
//	// 레이드 정보 뿌려주는 getRaidInfo() {} 중괄호 안에 이 메서드 사용할 거임. 
//	// 참가중인 레이드 찾아내기.
//	public ArrayList<RaidVO> getAppRaidInfo(ArrayList<Integer> al) {
//
//		try {
//			connection();
//
//			for(int i = 0; i>al.size();i++) {
//				String sql = "select * from t_raid where m_id = ?";
//				pst = conn.prepareStatement(sql);
//				pst = conn.prepareStatement(sql);
//				pst.setInt(1, al.get(i));
//				rs = pst.executeQuery();
//			
//				if(rs.next()){
//					String raid_kind = rs.getString("raid_kind");
//					String raid_name = rs.getString("raid_name");
//					String raid_cnt = rs.getString("raid_cnt");
//					Date reg_date = rs.getDate("reg_date");
//				
//					RaidVO raid_vo = new RaidVO(raid_kind, raid_name, raid_cnt, reg_date);
//					raidVO_al.add(raid_vo);
//					}
//				}	
//		}catch (Exception e) {
//				System.out.println("DAO의 getAppRaidInfo() 실패(예외발생)");
//				e.printStackTrace();
//			} finally {
//				close();
//			}
//		return raidVO_al;
//	}
	
	
	
	
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
	
	
	// 안드에서 레이드 버튼 눌렀을 때 요청이 들어온다.
	// 레이드 정보(이 정보로 레이드 화면 꾸민다)
//	public ArrayList<RaidVO> raidInfo(String id) {
//		// 멤버변수라서 필드의 변수만 m_ 라는 뜻이 멤버변수 아이디다ㅏ.
//		
//
//		// 내가 참여중인 레이드인지 아닌지 판단. 
//		//select * from T_RAID_APPLIER where m_id = 'shj'
//		// 참여중인 레이드면 참여중인 레이드 리턴
//		//select * from T_RAID where RAID_SEQ = 12
//		
//		// 참여중이 아니면 레이드 전부 조회해서 가장 최근의 레이드 뿌리기.(order by desc 필요)
//		// 그냥 레이드 세개만 만들어놓자.
//		
//		try {
//			connection();
//			
//			// 1. m_id로 사용자가 참여중인 레이드가 있는지 없는지 판단
//			String sql = "select raid_seq from T_RAID_APPLIER where m_id = ?";
//			pst = conn.prepareStatement(sql);
//			pst.setString(1, id);
//			rs = pst.executeQuery();
//			
//			while(rs.next()) {
//				raidSeq_al.add(rs.getInt("raid_seq"));
//			}
//			
//			if(rs.next()) { // 있을 때
//				raidVO_al = getAppRaidInfo(raidSeq_al);
//			}else { // 없을 때. 참여중인 레이드 없는 거니까 가장 최근 생성된 레이드 세개 뽑아서 보여주기. 
//				raidVO_al = getRaidInfo();
//			}
//			
//			
//		}catch (Exception e) {
//				System.out.println("raidInfo(예외 발생)");
//				e.printStackTrace();
//			} finally {
//				close();
//			}
//		return raidVO_al;
//	}
	
}
