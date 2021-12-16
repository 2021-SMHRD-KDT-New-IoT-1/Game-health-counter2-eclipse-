package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.AthleDAO;

@WebServlet("/Athle_Time")
public class Athle_Time extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("��������");
		request.setCharacterEncoding("UTF-8");
		
		String date = request.getParameter("a_date");
		String id = request.getParameter("m_id");
		
		System.out.println("��û�ϴ� id(�ȵ� Ÿ�Ӿ��ø�� �κ�) ="+id);
		
		AthleDAO dao = new AthleDAO();
		
		int push = dao.time_look(date, id, "push");
		int pull = dao.time_look(date, id, "pull");
		int squart = dao.time_look(date, id, "squart");
		
		response.setContentType("text/html; charset=UTF-8");
	    
		PrintWriter out = response.getWriter();
		out.print(push+","+pull+","+squart); //Ÿ�� ���� ��� ��ȸ ����� ������ ������.
	}

}
