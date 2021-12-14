package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.CharDAO;
import com.model.MemberDAO;
import com.model.MemberVO;

@WebServlet("/Join")
public class Join extends HttpServlet {
	// �� ���������� ȸ������ ������ ĳ���� �������� �ϰ� 
	// "����"�� �����Ѵ�.

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String admin_yn = "N";

		String m_id = request.getParameter("m_id");
		String m_pwd = request.getParameter("m_pwd");
		
		
		String m_gender = request.getParameter("m_gender");
		
		if(m_gender != null) {
			if(m_gender.equals("����")) {
				m_gender = "M";
			} else {
				m_gender = "F";
			}
		}
		
		
		System.out.println(m_gender);
		String m_name = request.getParameter("m_name");
		String m_nickname = request.getParameter("m_nickname");
		String m_email = request.getParameter("m_email");
		String m_phone = request.getParameter("m_phone");
		String m_push_yn = request.getParameter("m_push_yn");

		// ���⼭�� joindate�� ���� ����. joindate�� db�� insert�� �� sysdate�� ����.

//		if (m_id.equals("�̰��̰����ھ��̵��")) {
//			admin_yn = "Y";
//		}

		int cnt_join = new MemberDAO().join(m_id, m_pwd, m_gender, m_name, m_nickname, m_email, m_phone, m_push_yn,
				admin_yn);

		try {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			if (cnt_join > 0) {
				System.out.println("����: ȸ�����Լ���");

				int cnt_charCreate = new CharDAO().CharCreat(m_id);
				
				if (cnt_charCreate > 0) { //�� if���� ȸ�����԰� ĳ���ͻ��� �� �� �������� �� ������ if��
					out.print("����"); // ���� ����
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
	}
}

