package com.model;

import java.util.Date;

public class CharVO {
	 // 캐릭터 순번 
    private int c_seq;

//    // 캐릭터 이름 
//    private String c_name; //사용안함.

    // 캐릭터 메모 
    private String c_memo;

    // 생성 일자 
    private Date reg_date;

    // 회원 아이디 
    private String m_id;

    // 캐릭터 레벨 
    private int c_level;

    // 캐릭터 경로 
    private String c_file; //안드에서 지정해줘야함.


    //캐릭터 정보 뽑아올 때 (시퀀스, 메모, 아이디, 레벨)
    public CharVO(int c_seq, String c_memo, String m_id, int c_level) {
		super();
		this.c_seq = c_seq;
		this.c_memo = c_memo;
		this.m_id = m_id;
		this.c_level = c_level;
	}



	public int getC_seq() {
		return c_seq;
	}


	public void setC_seq(int c_seq) {
		this.c_seq = c_seq;
	}


	public String getC_memo() {
		return c_memo;
	}

	public void setC_memo(String c_memo) {
		this.c_memo = c_memo;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public int getC_level() {
		return c_level;
	}

	public void setC_level(int c_level) {
		this.c_level = c_level;
	}

	public String getC_file() {
		return c_file;
	}

	public void setC_file(String c_file) {
		this.c_file = c_file;
	}
    
    
    
    

}
