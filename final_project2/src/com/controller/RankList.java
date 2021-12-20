package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.model.RankDAO;

@WebServlet("/RankList")
public class RankList extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		serverLog("RankList");

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("m_id");
		System.out.println("요청하는 id(안드 랭크부분) = " + id);

		JsonArray arr = new JsonArray();

		arr = new RankDAO().rankList(id);
		System.out.println("보내는 객체 내용: " + arr.toString());

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(arr.toString());
	}

	public void serverLog(String serverName) {
		Date dt = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String time = sdf.format(dt);
		System.out.println();
		System.out.println(serverName + "서버 진입(" + time + ")");
	}

}
