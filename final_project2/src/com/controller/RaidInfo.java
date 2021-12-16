package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.RaidDAO;
import com.model.RaidVO;

 
@WebServlet("/RaidInfo")
public class RaidInfo extends HttpServlet {


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		// 레이드 화면(정보화면X) 정보 뿌리는 서블릿
		// 안드에서 m_id 받는다.
		// RaidVO(raid_seq, raid_kind, raid_name, raid_cnt, reg_date)를 반환한다.

		String m_id = request.getParameter("m_id");
		
		RaidDAO dao = new RaidDAO();
		ArrayList<RaidVO> raid_al = new ArrayList<RaidVO>();
		raid_al = dao.raidInfo();
		
		
		try {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			String json = new Gson().toJson(raid_al);
			System.out.println("json 객체 = "+json);
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
