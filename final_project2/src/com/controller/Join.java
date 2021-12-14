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
	// 이 페이지에서 회원가입 성공시 캐릭터 생성까지 하고 
	// "성공"을 리턴한다.

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String admin_yn = "N";

		String m_id = request.getParameter("m_id");
		String m_pwd = request.getParameter("m_pwd");
		
		
		String m_gender = request.getParameter("m_gender");
		
		if(m_gender != null) {
			if(m_gender.equals("남자")) {
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

		// 여기서도 joindate는 받지 않음. joindate는 db에 insert할 때 sysdate로 들어간다.

//		if (m_id.equals("이것이관리자아이디다")) {
//			admin_yn = "Y";
//		}

		int cnt_join = new MemberDAO().join(m_id, m_pwd, m_gender, m_name, m_nickname, m_email, m_phone, m_push_yn,
				admin_yn);

		try {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			if (cnt_join > 0) {
				System.out.println("서블릿: 회원가입성공");

				int cnt_charCreate = new CharDAO().CharCreat(m_id);
				
				if (cnt_charCreate > 0) { //이 if문은 회원가입과 캐릭터생성 둘 다 성공했을 때 들어오는 if문
					out.print("성공"); // 성공 리턴
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
	}
}

