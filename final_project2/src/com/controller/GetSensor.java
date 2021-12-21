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
		
		// 시간까지 필요? 운동기록을 어떤 방식으로 저장할 건지.
		// 총 개수만 뜨게 하면 된다.
		// 그날 한 총 개수 // 타임모드의 최고기록
		// 그날 한 총 개수는 당일 날짜로 검색하고
		// 타임모드의 최고기록은 
		// 서버에 요청 보내는 시간을 제한해야한다.
		
		request.setCharacterEncoding("UTF-8");
		pull_cnt = request.getParameter("");
		sqt_cnt = request.getParameter("");
		push_cnt = request.getParameter("");
		date = request.getParameter("");
		
		
		
		
	}

}
