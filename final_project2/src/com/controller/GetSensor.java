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

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

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

			String time_cnt = "";

			// �ð����� �ʿ�? ������ � ������� ������ ����.
			// �� ������ �߰� �ϸ� �ȴ�.
			// �׳� �� �� ���� // Ÿ�Ӹ���� �ְ���
			// �׳� �� �� ������ ���� ��¥�� �˻��ϰ�
			// Ÿ�Ӹ���� �ְ�����
			// ������ ��û ������ �ð��� �����ؾ��Ѵ�.

			request.setCharacterEncoding("UTF-8");

			serial = request.getParameter("serial");
			push_cnt = request.getParameter("push_cnt");
			pull_cnt = request.getParameter("pull_cnt");
			sqt_cnt = request.getParameter("sqt_cnt");
			time_mode = request.getParameter("mode");
			time_cnt = request.getParameter("cnt");

//		reg_date = request.getParameter("reg_date");

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			// �ӽ÷� serial = "bang9"�� ��ü
			serial = "bang9";

			// �ӽ÷� m_id = 'bang9', raid_seq = 12 �� ��ü ����
			int raid_seq = 12;

			SensorDAO dao = new SensorDAO();
			
			
			if(time_mode == null) { // �Ϲݸ���� ���
				dao.getSensor(push_cnt, pull_cnt, sqt_cnt, serial, time_mode);
				dao.updateRecord(push_cnt, pull_cnt, sqt_cnt, serial, raid_seq);
			} else { // Ÿ�Ӿ��ø�� �� ���
				dao.getSensor(time_cnt, serial, time_mode);
				dao.updateRecord(push_cnt, time_cnt, sqt_cnt, serial, raid_seq);
			}

			result = "push_cnt: " + push_cnt + ", pull_cnt: " + pull_cnt + ", sqt_cnt: " + sqt_cnt + ", time_mode: "
					+ time_mode + ", cnt: "+time_cnt;

			System.out.println(result);

			response.setContentType("text/html;charset=utf-8");
//		    response.setCharacterEncoding("UTF-8");
			response.getWriter().write("���� ����");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public void serverLog(String serverName) {
		Date dt = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm�� ss��");
		String time = sdf.format(dt);
		System.out.println();
		System.out.println(serverName + "���� ����(" + time + ")");
	}

}
