package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.CharVO;
import com.model.MemberDAO;
import com.model.MemberVO;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		System.out.println("서버진입");
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("m_id");
		String pwd = request.getParameter("m_pwd");
		
		System.out.println("id = "+id+", pwd = "+pwd);
		
		CharVO char_vo = new MemberDAO().login(id, pwd);
		
		
		try {
//			String result = member_vo.getM_nickname();
			
//			System.out.println("nickname = "+result);
			
			
			// 안드에 쏴주기
			String json = new Gson().toJson(char_vo);
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
			
//			PrintWriter out = response.getWriter();
//			out.print(result);
			
		} catch (NullPointerException e) {
			System.out.println("객체 = null");
		}
		
		
		
//		if(member_vo != null) {
//			
//		} else {
//			
//		}
		
	}

}
