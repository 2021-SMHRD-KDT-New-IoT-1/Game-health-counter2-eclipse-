package com.model;

import java.sql.Date;

public class SensorVO {

	private String pull_cnt; 
	private String sqt_cnt;
	private String push_cnt;
	private String time;
	private Date date;
	
	public SensorVO(String pull_cnt, String sqt_cnt, String push_cnt, String time, Date date) {
		super();
		this.pull_cnt = pull_cnt;
		this.sqt_cnt = sqt_cnt;
		this.push_cnt = push_cnt;
		this.time = time;
		this.date = date;
	}
	

	public String getPull_cnt() {
		return pull_cnt;
	}

	public void setPull_cnt(String pull_cnt) {
		this.pull_cnt = pull_cnt;
	}

	public String getSqt_cnt() {
		return sqt_cnt;
	}

	public void setSqt_cnt(String sqt_cnt) {
		this.sqt_cnt = sqt_cnt;
	}

	public String getPush_cnt() {
		return push_cnt;
	}

	public void setPush_cnt(String push_cnt) {
		this.push_cnt = push_cnt;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "SensorVO [pull_cnt=" + pull_cnt + ", sqt_cnt=" + sqt_cnt + ", push_cnt=" + push_cnt + ", time=" + time
				+ ", date=" + date + "]";
	}
	
	
}
