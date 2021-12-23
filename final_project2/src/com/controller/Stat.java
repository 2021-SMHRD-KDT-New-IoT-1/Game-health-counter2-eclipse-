package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.model.QuestDAO;
import com.model.StatDAO;

@WebServlet("/Stat")
public class Stat extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serverLog("Stat");
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("m_id");
		System.out.println("��û�ϴ� id(�ȵ� ���Ⱥκ�) = "+id);
		
		String result = new StatDAO().statList(id);
		System.out.println("������ ��ü ����: "+result);
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);		
	}
	
	public void serverLog(String serverName) {
		Date dt = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm�� ss��");
		String time = sdf.format(dt);
		System.out.println();
		System.out.println(serverName+"���� ����(" + time + ")");
	}

}
