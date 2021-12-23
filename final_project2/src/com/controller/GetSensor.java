package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetSensor")
public class GetSensor extends HttpServlet {


	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		serverLog("GetSensor");
		
		request.setCharacterEncoding("UTF-8");
		
		String pull_cnt = "";
		String sqt_cnt = "";
		String push_cnt = "";
		String time_attack = "";
		String time_mode = "";
		String date;
		String result;
		
		// �ð����� �ʿ�? ������ � ������� ������ ����.
		// �� ������ �߰� �ϸ� �ȴ�.
		// �׳� �� �� ���� // Ÿ�Ӹ���� �ְ���
		// �׳� �� �� ������ ���� ��¥�� �˻��ϰ�
		// Ÿ�Ӹ���� �ְ����� 
		// ������ ��û ������ �ð��� �����ؾ��Ѵ�.
		
		request.setCharacterEncoding("UTF-8");
		pull_cnt = request.getParameter("push_cnt");
		sqt_cnt = request.getParameter("pull_cnt");
		push_cnt = request.getParameter("sqt_cnt");
		date = request.getParameter("reg_date");
		
		
//		result = "pull: "+pull_cnt+", sqt: "+sqt_cnt+", push: "+push_cnt+", date: "+date;
		result = "pull: "+pull_cnt+", sqt: "+sqt_cnt+", push: "+push_cnt+", date: "+date;
		System.out.println(result);
		//response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		response.getWriter().write("����");	
		
	}
	
	public void serverLog(String serverName) {
		Date dt = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm�� ss��");
		String time = sdf.format(dt);
		System.out.println();
		System.out.println(serverName+"���� ����(" + time + ")");
	}

}
