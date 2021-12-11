package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.MemberDAO;
import com.model.MemberVO;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("서버진입");
		MemberDAO member_dao = new MemberDAO();
		
		request.setCharacterEncoding("euc-kr");
		
		String id = request.getParameter("m_id");
		String pwd = request.getParameter("m_pwd");
		
		System.out.println(id+ pwd);
		
		String result = member_dao.login(id, pwd);
		System.out.println(result);
		PrintWriter out = response.getWriter();
		out.print(result);
		
		
		
	}

}
