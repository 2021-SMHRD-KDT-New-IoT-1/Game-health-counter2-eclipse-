package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.SensorDAO;

import sun.management.Sensor;


@WebServlet("/GetSensor")
public class GetSensor extends HttpServlet {


	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		serverLog("GetSensor");
		
		request.setCharacterEncoding("UTF-8");
		
		String serial = "";
		String pull_cnt = "";
		String sqt_cnt = "";
		String push_cnt = "";
		String time_mode = "";
//		String reg_date = "";
		
//		String time_attack = "";
		
		String result = "";
		
		// �ð����� �ʿ�? ������ � ������� ������ ����.
		// �� ������ �߰� �ϸ� �ȴ�.
		// �׳� �� �� ���� // Ÿ�Ӹ���� �ְ���
		// �׳� �� �� ������ ���� ��¥�� �˻��ϰ�
		// Ÿ�Ӹ���� �ְ����� 
		// ������ ��û ������ �ð��� �����ؾ��Ѵ�.
		
		request.setCharacterEncoding("UTF-8");
		
		serial = request.getParameter("serial");
		pull_cnt = request.getParameter("push_cnt");
		sqt_cnt = request.getParameter("pull_cnt");
		push_cnt = request.getParameter("sqt_cnt");
		time_mode = request.getParameter("time_mode");
		
//		reg_date = request.getParameter("reg_date");
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		// �ӽ÷� serial ������ m_id = 'bang9'�� ��ü ����
		serial = "bang9";
		new SensorDAO().getSensor(push_cnt, pull_cnt, sqt_cnt, serial, time_mode);
		
		result = "push_cnt: " + push_cnt + ", pull_cnt: " + pull_cnt + ", sqt_cnt: " + sqt_cnt + ", time_mode: "
				+ time_mode;

		System.out.println(result);
		
		response.setContentType("text/html;charset=utf-8");
//	    response.setCharacterEncoding("UTF-8");
		response.getWriter().write("���� ����");	
		
	}
	
	public void serverLog(String serverName) {
		Date dt = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm�� ss��");
		String time = sdf.format(dt);
		System.out.println();
		System.out.println(serverName+"���� ����(" + time + ")");
	}

}
