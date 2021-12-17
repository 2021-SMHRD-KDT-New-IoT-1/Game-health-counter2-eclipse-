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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
		System.out.println(m_id);
		
		
		
		RaidDAO dao = new RaidDAO();
		ArrayList<RaidVO> raid_al = new ArrayList<RaidVO>();
		
		JsonArray arr = new JsonArray();
		
		arr = dao.raidInfo(m_id);
		System.out.println(arr.toString());
  

		
		try {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(arr.toString());
		    
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}


//	class JSONAdd{
//		String json; //파싱할 최종 데이터 담아주기 위한 문자열
//		
//		//생성자 통해 JSON생성
//		public JSONAdd(){
//			//JSON생성시 언제나 내부부터 채워나가기
//			//JSONObject 3개에 각각 데이터 저장
//			Gson gson = new Gson();
//			gson.toJson(src)data1 = new JSONObject();
//			data1.put("professor", "김교수");
//			data1.put("student", "이학생");
//
//			JSONObject data2 = new JSONObject();
//			data2.put("professor", "박교수");
//			data2.put("student", "최학생");
//			
//			JSONObject data3 = new JSONObject();
//			data3.put("professor", "한교수");
//			data3.put("student", "황학생");
//			
//			//위에서 만든 3개의 오브젝트 데이터를 JSONArray에 순서대로 저장
//			JSONArray arr = new JSONArray();
//			arr.add(data1);//0번인덱스
//			arr.add(data2);//1번인덱스
//			arr.add(data3);//2번인덱스
//			
//			//최종적으로 univ오브젝트에 JSON배열 저장
//			JSONObject univ = new JSONObject();
//			univ.put("univ", arr);
//			
//			//파싱할 데이터 저장
//			json = univ.toJSONString();
//			
//			//테스트용 출력
//			//System.out.println(json);
//		}
//	}
