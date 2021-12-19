package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.CharDAO;

@WebServlet("/CharInfo")
public class CharInfo extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		serverLog("CharInfo");
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("m_id");
		
		System.out.println("요청하는 id(안드 캐릭터 메인화면 부분) ="+id);
		
		String charInfo = new CharDAO().charInfo(id);
		
		response.setContentType("text/html; charset=UTF-8");
	   
		PrintWriter out = response.getWriter();
		out.print(charInfo); //캐릭터 정보 조회 결과를 서버로 보낸다.
		System.out.println("캐릭터 정보 전송 성공!");
	}
	
	public void serverLog(String serverName) {
		Date dt = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String time = sdf.format(dt);
		System.out.println();
		System.out.println(serverName+"서버 진입(" + time + ")");
	}

}
