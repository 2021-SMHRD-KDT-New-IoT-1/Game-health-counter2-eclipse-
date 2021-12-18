package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.RaidDAO;
import com.model.RaidVO;


@WebServlet("/AppRaidInfo")
public class AppRaidInfo extends HttpServlet {


	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. �ȵ忡�� m_id, raid_seq �ޱ� -> �̿��ؼ� appRecord(m_id,raid_seq)�� �ش� ���̵��� �⿩Ƚ�� ����
		// 2. 
		
		request.setCharacterEncoding("UTF-8");


		String m_id = request.getParameter("m_id");
		String raid_seq = request.getParameter("raid_seq");

		System.out.println(m_id + "" + raid_seq);
		
		RaidDAO dao = new RaidDAO();
		// �ش� ���̵忡 �⿩�� Ƚ��, 1���ε����� raid_seq 
		String[] applier_record = dao.appRecord(m_id, raid_seq);
		
		// ��� ������ ���̵� �⿩ Ƚ��
		int all_record = dao.raidAllRecord(raid_seq);
		
		System.out.println(applier_record[0]+"/"+all_record);
		
		
		try {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.print(applier_record[0] + "#" + all_record); // �⿩�� Ƚ���� �������ѱ⿩Ƚ���� �ȵ忡��~
			// 13*23 �̷��� �����Ѵ�.
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
		
		
	}

}
