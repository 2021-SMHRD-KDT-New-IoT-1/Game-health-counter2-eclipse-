package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.CharDAO;

@WebServlet("/CharInfo")
public class CharInfo extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		serverLog("CharInfo");
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("m_id");
		
		System.out.println("��û�ϴ� id(�ȵ� ĳ���� ����ȭ�� �κ�) ="+id);
		
		String charInfo = new CharDAO().charInfo(id);
		
		response.setContentType("text/html; charset=UTF-8");
	   
		PrintWriter out = response.getWriter();
		out.print(charInfo); //ĳ���� ���� ��ȸ ����� ������ ������.
		System.out.println("ĳ���� ���� ���� ����!");
	}
	
	public void serverLog(String serverName) {
		Date dt = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm�� ss��");
		String time = sdf.format(dt);
		System.out.println();
		System.out.println(serverName+"���� ����(" + time + ")");
	}

}
