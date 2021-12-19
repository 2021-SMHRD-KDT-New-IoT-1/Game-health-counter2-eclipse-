package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.CharDAO;
import com.model.MemberDAO;


@WebServlet("/getExp")
public class getExp extends HttpServlet {


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("m_id");

		int exp = new CharDAO().CharExp(id);
		System.out.println(id);
		
		if (exp != -1) {
			System.out.println("ÃÑ °æÇèÄ¡"+exp);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(exp+"");
		}
		
	

		
		
		
	}

}
