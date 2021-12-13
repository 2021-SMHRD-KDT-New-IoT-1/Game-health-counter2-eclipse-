package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.CharVO;
import com.model.MemberDAO;
import com.model.MemberVO;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("서버진입");
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("m_id");
		String pwd = request.getParameter("m_pwd");
		
		System.out.println("입력받은 id = "+id+", pwd = "+pwd);
		
		String m_id = new MemberDAO().login(id, pwd);

		if(!m_id.equals("로그인실패")) {
		
		System.out.println("조회 후 가지고 온 nickname : "+ m_id);
		try {
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    
			PrintWriter out = response.getWriter();
			out.print(m_id); //nickname을 서버로 보낸다.
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
		

		
		
		
		
		
		
		//		try {
////			String result = member_vo.getM_nickname();
//			
////			System.out.println("nickname = "+result);
//			
//			
//			// 안드에 쏴주기
//			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); //  DateFormat변경후 생성
//			String json = gson.toJson(char_vo);
//			System.out.println("json 객체 = "+json);
//			
//			response.setContentType("application/json");
//		    response.setCharacterEncoding("UTF-8");
//		    response.getWriter().write(json);
//		    
//			
////			PrintWriter out = response.getWriter();
////			out.print(result);
//			
//		} catch (NullPointerException e) {
//			System.out.println("객체 = null");
//		}
		
		
		
//		if(member_vo != null) {
//			
//		} else {
//			
//		}
		
	}

}
