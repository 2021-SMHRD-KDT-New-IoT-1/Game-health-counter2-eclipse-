package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.RaidDAO;


@WebServlet("/RaidUpdate")
public class RaidUpdate extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 참가하기, 포기하기 구분법
		// 내 아이디로 app 조회후 정보 안나오면 참가하기
		// 나오면 포기하기로
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("m_id");
		String seq = request.getParameter("raid_seq");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		RaidDAO dao = new RaidDAO();
		String[] result = dao.appRecord(id, seq);
		System.out.println(result[0].toString() + result[1].toString());
		
		if(result[1].equals("-1")) { // 인서트해야함
			System.out.println(result[1].toString());
			if(dao.insertAppRaid(id, seq)) {
				out.print("true");
			}else {
				out.print("false");
			}
			
		}else { // 딜리트해야함
			System.out.println(result[1]);
			if(dao.deleteAppRaid(id, seq)) {
				out.print("true");
			}else {
				out.print("false");
			}
		}
	
		
	}

}
