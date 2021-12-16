package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.AthleDAO;

@WebServlet("/Athle")
public class Athle extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서버진입");
		request.setCharacterEncoding("UTF-8");
		
		String date = request.getParameter("a_date");
		String id = request.getParameter("m_id");
		
		System.out.println("요청하는 id(안드 기록부분) ="+id);
		
		String athleResult = new AthleDAO().look(date, id);
		
		response.setContentType("text/html; charset=UTF-8");
	    
		PrintWriter out = response.getWriter();
		out.print(athleResult); //운동 기록 조회 결과를 서버로 보낸다.
	
	}

}
