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
		
		// 시간까지 필요? 운동기록을 어떤 방식으로 저장할 건지.
		// 총 개수만 뜨게 하면 된다.
		// 그날 한 총 개수 // 타임모드의 최고기록
		// 그날 한 총 개수는 당일 날짜로 검색하고
		// 타임모드의 최고기록은 
		// 서버에 요청 보내는 시간을 제한해야한다.
		
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
		response.getWriter().write("성공");	
		
	}
	
	public void serverLog(String serverName) {
		Date dt = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String time = sdf.format(dt);
		System.out.println();
		System.out.println(serverName+"서버 진입(" + time + ")");
	}

}
