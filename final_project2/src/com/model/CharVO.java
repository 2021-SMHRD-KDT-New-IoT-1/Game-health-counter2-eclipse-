package com.model;

import java.util.Date;

public class CharVO {
	 // ĳ���� ���� 
    private int c_seq;

//    // ĳ���� �̸� 
//    private String c_name; //������.

    // ĳ���� �޸� 
    private String c_memo;

    // ���� ���� 
    private Date reg_date;

    // ȸ�� ���̵� 
    private String m_id;

    // ĳ���� ���� 
    private int c_level;

    // ĳ���� ��� 
    private String c_file; //�ȵ忡�� �����������.


    //ĳ���� ���� �̾ƿ� �� (������, �޸�, ���̵�, ����)
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
