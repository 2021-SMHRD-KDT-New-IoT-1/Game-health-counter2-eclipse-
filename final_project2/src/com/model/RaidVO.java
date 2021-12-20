package com.model;

import java.sql.Date;

public class RaidVO {
	// 안드에서 onCreateView 할 때 VO객체로 보내줄 것임
	private String raid_seq; // 레이드 시퀀스
	private String raid_kind; // 레이드 종류 
	private String raid_name; // 레이드 이름
	private String raid_cnt; // 레이드 미션 횟수
	private Date reg_date; // 레이드 기간 . 일단 3일로 치고. 시작날짜에다가 +3일하면 종료날짜.
	private String check;
	
	
	private String applier_record;// applier용 필드 
	
	
	
	
	public RaidVO(String raid_seq, String raid_kind, String raid_name, String raid_cnt, Date reg_date, String check) {
		super();
		this.raid_seq = raid_seq;
		this.raid_kind = raid_kind;
		this.raid_name = raid_name;
		this.raid_cnt = raid_cnt;
		this.reg_date = reg_date;
		this.check = check;
	}
	
	
	// applierRaid
	public RaidVO(String raid_seq,String applier_record) {
		this.raid_seq = raid_seq;
		this.applier_record = applier_record;
	}

	


	public String getApplier_record() {
		return applier_record;
	}


	public void setApplier_record(String applier_record) {
		this.applier_record = applier_record;
	}


	public String getRaid_seq() {
		return raid_seq;
	}




	public void setRaid_seq(String raid_seq) {
		this.raid_seq = raid_seq;
	}




	public String getRaid_kind() {
		return raid_kind;
	}




	public void setRaid_kind(String raid_kind) {
		this.raid_kind = raid_kind;
	}




	public String getRaid_name() {
		return raid_name;
	}




	public void setRaid_name(String raid_name) {
		this.raid_name = raid_name;
	}




	public String getRaid_cnt() {
		return raid_cnt;
	}




	public void setRaid_cnt(String raid_cnt) {
		this.raid_cnt = raid_cnt;
	}




	public Date getReg_date() {
		return reg_date;
	}




	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	
	public String getCheck() {
		return check;
	}




	public void setCheck(String check) {
		this.check = check;
	}

	 
}
