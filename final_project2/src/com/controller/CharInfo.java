package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.CharDAO;

@WebServlet("/CharInfo")
public class CharInfo extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("��������");
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("m_id");
		
		System.out.println("��û�ϴ� id(�ȵ� ĳ���� ����ȭ�� �κ�) ="+id);
		
		String charInfo = new CharDAO().charInfo(id);
		
		response.setContentType("text/html; charset=UTF-8");
	   
		PrintWriter out = response.getWriter();
		out.print(charInfo); //ĳ���� ���� ��ȸ ����� ������ ������.
		System.out.println("ĳ���� ���� ���� ����!");
	}

}
