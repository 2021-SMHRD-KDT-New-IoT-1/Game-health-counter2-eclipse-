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

			// 시간까지 필요? 운동기록을 어떤 방식으로 저장할 건지.
			// 총 개수만 뜨게 하면 된다.
			// 그날 한 총 개수 // 타임모드의 최고기록
			// 그날 한 총 개수는 당일 날짜로 검색하고
			// 타임모드의 최고기록은
			// 서버에 요청 보내는 시간을 제한해야한다.

			request.setCharacterEncoding("UTF-8");

			serial = request.getParameter("serial");
			push_cnt = request.getParameter("push_cnt");
			pull_cnt = request.getParameter("pull_cnt");
			sqt_cnt = request.getParameter("sqt_cnt");
			time_mode = request.getParameter("mode");
			time_cnt = request.getParameter("cnt");

//		reg_date = request.getParameter("reg_date");

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			// 임시로 serial = "bang9"로 대체
			serial = "bang9";

			// 임시로 m_id = 'bang9', raid_seq = 12 로 대체 했음
			int raid_seq = 12;

			SensorDAO dao = new SensorDAO();
			
			
			if(time_mode == null) { // 일반모드일 경우
				dao.getSensor(push_cnt, pull_cnt, sqt_cnt, serial, time_mode);
				dao.updateRecord(push_cnt, pull_cnt, sqt_cnt, serial, raid_seq);
			} else { // 타임어택모드 일 경우
				dao.getSensor(time_cnt, serial, time_mode);
				dao.updateRecord(push_cnt, time_cnt, sqt_cnt, serial, raid_seq);
			}

			result = "push_cnt: " + push_cnt + ", pull_cnt: " + pull_cnt + ", sqt_cnt: " + sqt_cnt + ", time_mode: "
					+ time_mode + ", cnt: "+time_cnt;

			System.out.println(result);

			response.setContentType("text/html;charset=utf-8");
//		    response.setCharacterEncoding("UTF-8");
			response.getWriter().write("응답 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public void serverLog(String serverName) {
		Date dt = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String time = sdf.format(dt);
		System.out.println();
		System.out.println(serverName + "서버 진입(" + time + ")");
	}

}
