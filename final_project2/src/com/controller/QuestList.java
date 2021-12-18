package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.model.QuestDAO;

@WebServlet("/QuestList")
public class QuestList extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("��������");
		request.setCharacterEncoding("UTF-8");
		
//		String id = request.getParameter("m_id");
		String id = "bang9";
		System.out.println("��û�ϴ� id(�ȵ� ����Ʈ�κ�) = "+id);
		
		JsonArray arr = new JsonArray();
		
		arr = new QuestDAO().questList(id);
		System.out.println("������ ��ü ����: "+arr.toString());
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		response.getWriter().write(arr.toString());		
	}

}
