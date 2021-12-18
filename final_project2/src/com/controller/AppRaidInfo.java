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
		// 1. 안드에서 m_id, raid_seq 받기 -> 이용해서 appRecord(m_id,raid_seq)로 해당 레이드의 기여횟수 리턴
		// 2. 
		
		request.setCharacterEncoding("UTF-8");


		String m_id = request.getParameter("m_id");
		String raid_seq = request.getParameter("raid_seq");

		System.out.println(m_id + "" + raid_seq);
		
		RaidDAO dao = new RaidDAO();
		// 해당 레이드에 기여한 횟수, 1번인덱스엔 raid_seq 
		String[] applier_record = dao.appRecord(m_id, raid_seq);
		
		// 모든 참가자 레이드 기여 횟수
		int all_record = dao.raidAllRecord(raid_seq);
		
		System.out.println(applier_record[0]+"/"+all_record);
		
		
		try {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.print(applier_record[0] + "#" + all_record); // 기여한 횟수와 참가자총기여횟수를 안드에게~
			// 13*23 이렇게 구분한다.
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
		
		
	}

}
