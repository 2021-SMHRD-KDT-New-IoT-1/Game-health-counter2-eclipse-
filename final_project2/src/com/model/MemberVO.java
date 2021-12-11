package com.model;

import java.util.Date;

public class MemberVO {

	private String m_id;
	private String m_pwd;
	private String m_gender;
	private String m_name;
	private String m_nickname;
	private String m_email;
	private String m_phone;
	private String m_push_yn;
	private Date m_joindate;
	private String admin_yn;
	
	public MemberVO(String m_id, String m_pwd, String m_gender, String m_name, String m_nickname, String m_email,
			String m_phone, String m_push_yn, Date m_joindate, String admin_yn) {
		super();
		this.m_id = m_id;
		this.m_pwd = m_pwd;
		this.m_gender = m_gender;
		this.m_name = m_name;
		this.m_nickname = m_nickname;
		this.m_email = m_email;
		this.m_phone = m_phone;
		this.m_push_yn = m_push_yn;
		this.m_joindate = m_joindate;
		this.admin_yn = admin_yn;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_pwd() {
		return m_pwd;
	}

	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}

	public String getM_gender() {
		return m_gender;
	}

	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_nickname() {
		return m_nickname;
	}

	public void setM_nickname(String m_nickname) {
		this.m_nickname = m_nickname;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public String getM_push_yn() {
		return m_push_yn;
	}

	public void setM_push_yn(String m_push_yn) {
		this.m_push_yn = m_push_yn;
	}

	public Date getM_joindate() {
		return m_joindate;
	}

	public void setM_joindate(Date m_joindate) {
		this.m_joindate = m_joindate;
	}

	public String getAdmin_yn() {
		return admin_yn;
	}

	public void setAdmin_yn(String admin_yn) {
		this.admin_yn = admin_yn;
	}

	@Override
	public String toString() {
		return "MemberVO [m_id=" + m_id + ", m_pwd=" + m_pwd + ", m_gender=" + m_gender + ", m_name=" + m_name
				+ ", m_nickname=" + m_nickname + ", m_email=" + m_email + ", m_phone=" + m_phone + ", m_push_yn="
				+ m_push_yn + ", m_joindate=" + m_joindate + ", admin_yn=" + admin_yn + "]";
	}
	
	
	
	

}
