package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetSensor")
public class GetSensor extends HttpServlet {


	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pull_cnt = "0";
		String sqt_cnt = "0";
		String push_cnt = "0";
		String time_attack = "0";
		String time_mode = "0";
		String date;
		
		// �ð����� �ʿ�? ������ � ������� ������ ����.
		// �� ������ �߰� �ϸ� �ȴ�.
		// �׳� �� �� ���� // Ÿ�Ӹ���� �ְ���
		// �׳� �� �� ������ ���� ��¥�� �˻��ϰ�
		// Ÿ�Ӹ���� �ְ����� 
		// ������ ��û ������ �ð��� �����ؾ��Ѵ�.
		
		request.setCharacterEncoding("UTF-8");
		pull_cnt = request.getParameter("");
		sqt_cnt = request.getParameter("");
		push_cnt = request.getParameter("");
		date = request.getParameter("");
		
		
		
		
	}

}
