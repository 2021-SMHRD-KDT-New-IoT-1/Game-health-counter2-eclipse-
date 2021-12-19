package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.RaidDAO;


@WebServlet("/DeleteRaid")
public class DeleteRaid extends HttpServlet {


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("m_id");
		String seq = request.getParameter("raid_seq");
		
		RaidDAO dao = new RaidDAO();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(dao.deleteAppRaid(id, seq)) {
			out.print("true");
		}else {
			out.print("false");
		}
				
		
		
		
		
	}

}
