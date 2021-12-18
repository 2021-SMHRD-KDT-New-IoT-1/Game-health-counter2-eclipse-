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
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); //  DateFormat������ ����
	
//	ArrayList<Integer> raidSeq_al = new ArrayList<Integer>();
	ArrayList<RaidVO> raidVO_al = new ArrayList<RaidVO>();
	
	
	public ArrayList<RaidVO> raidInfo(String id) { // ���̵��ư ������ �� ���̵�ȭ�� ����
		// ���⼭ ���̵� ���� �� ������ ������
		// �ȵ忡�� ���������� �ƴ��� �˷��ֱ�.
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
	
	
			
					    
					
					// �������� ���̵� �˷��ֱ�
					// �����ϱ� ������ ���������� �ٲ�� �ٽ� �� ȭ�� ���� �� �������� �Ŵ� ������ �Ǿ�� �Ѵ�.
					// ���� �� �������� �Ѱ��ָ� �ȵ忡�� �������� ������ �����س��ٰ� �� �������� ���������� �ٲ۴�.
					// sharedPreference 
					
					}
				
		}catch (Exception e) {
				System.out.println("DAO�� raidInfo() ����(���ܹ߻�)");
				e.printStackTrace();
			} finally {
				close();
			}
		return raidVO_al;
	}
	
	
	public String[] appRecord(String m_id, String raid_seq){
		// �������� ���̵� �������� �ȵ忡�� �޾ƿ´�. �ȵ忡�� raid��ü�� �־���Ѵ�..?? �ǹ��� ��� ������ �ִٴ� �����Ͽ�
		// ���⼭ �̾��� ���� 
		// ���� �� ����
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
					System.out.println("DB��ȸ ���� : appRecord()");
					
					result[0] = applier_record;
					result[1] = raid_seq1;
					
					return result;
					}else {
						System.out.println("DB��ȸ ���� : appRecord()");
					}
				
				
		}catch (Exception e) {
				System.out.println("���ܹ߻� : DAO�� appRecord()");
				e.printStackTrace();
			} finally {
				close();
			}
		
		return result;
	}
	
	
	// �ش� ���̵忡 �����ڰ� �⿩�� �� Ƚ��(��!!!!!)
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
					System.out.println("DB��ȸ ���� : raidAllRecord()");
					return applier_record;
					
					
					}else {
						System.out.println("DB��ȸ ���� : raidAllRecord()");
						
					}
				
				
		}catch (Exception e) {
				System.out.println("DAO�� riadAllRecord()����(���ܹ߻�)");
				e.printStackTrace();
			} finally {
				close();
			}
		return applier_record;
	}

	
	
	
	
//	// ���̵� ���� �ѷ��ִ� getRaidInfo() {} �߰�ȣ �ȿ� �� �޼��� ����� ����. 
//	// �������� ���̵� ã�Ƴ���.
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
//				System.out.println("DAO�� getAppRaidInfo() ����(���ܹ߻�)");
//				e.printStackTrace();
//			} finally {
//				close();
//			}
//		return raidVO_al;
//	}
	
	
	
	
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
	
	
	// �ȵ忡�� ���̵� ��ư ������ �� ��û�� ���´�.
	// ���̵� ����(�� ������ ���̵� ȭ�� �ٹδ�)
//	public ArrayList<RaidVO> raidInfo(String id) {
//		// ��������� �ʵ��� ������ m_ ��� ���� ������� ���̵�٤�.
//		
//
//		// ���� �������� ���̵����� �ƴ��� �Ǵ�. 
//		//select * from T_RAID_APPLIER where m_id = 'shj'
//		// �������� ���̵�� �������� ���̵� ����
//		//select * from T_RAID where RAID_SEQ = 12
//		
//		// �������� �ƴϸ� ���̵� ���� ��ȸ�ؼ� ���� �ֱ��� ���̵� �Ѹ���.(order by desc �ʿ�)
//		// �׳� ���̵� ������ ��������.
//		
//		try {
//			connection();
//			
//			// 1. m_id�� ����ڰ� �������� ���̵尡 �ִ��� ������ �Ǵ�
//			String sql = "select raid_seq from T_RAID_APPLIER where m_id = ?";
//			pst = conn.prepareStatement(sql);
//			pst.setString(1, id);
//			rs = pst.executeQuery();
//			
//			while(rs.next()) {
//				raidSeq_al.add(rs.getInt("raid_seq"));
//			}
//			
//			if(rs.next()) { // ���� ��
//				raidVO_al = getAppRaidInfo(raidSeq_al);
//			}else { // ���� ��. �������� ���̵� ���� �Ŵϱ� ���� �ֱ� ������ ���̵� ���� �̾Ƽ� �����ֱ�. 
//				raidVO_al = getRaidInfo();
//			}
//			
//			
//		}catch (Exception e) {
//				System.out.println("raidInfo(���� �߻�)");
//				e.printStackTrace();
//			} finally {
//				close();
//			}
//		return raidVO_al;
//	}
	
}
