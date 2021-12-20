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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
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
		String raid_seq_pull = request.getParameter("raid_seq_pull");
		String raid_seq_sqt = request.getParameter("raid_seq_sqt");
		String raid_seq_push = request.getParameter("raid_seq_push");
		
		System.out.println("겟태그한 레이드시퀀스 : "+ raid_seq_pull);
		System.out.println("겟태그한 레이드시퀀스 : "+ raid_seq_sqt);
		System.out.println("겟태그한 레이드시퀀스 : "+ raid_seq_push);
		
		//String raid_seq = request.getParameter("raid_seq");
		
		System.out.println(m_id);
		
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); //  DateFormat변경후 생성
		RaidDAO dao = new RaidDAO();
		JsonArray arr = new JsonArray();
		
		
		ArrayList<RaidVO> appRaid_al = dao.appRecord(m_id, raid_seq_pull, raid_seq_sqt, raid_seq_push);
		// 여기서 참가중인 raid_seq와 기록이 나온다.
		
		
		ArrayList<RaidVO> raidInfo_al = dao.raidInfo(m_id);
		// 레이드 화면에 뿌릴 데이터들
		

		for(int i = 0; i < raidInfo_al.size(); i++) {
			for(int j = 0; j < appRaid_al.size(); j++) {
				if(raidInfo_al.get(i).getRaid_seq().equals(appRaid_al.get(j).getRaid_seq())) {
					raidInfo_al.get(i).setCheck("true");
				}
			}
			arr.add(gson.toJson(raidInfo_al.get(i)));
		}
		
		
		
//		for(int i = 0; i<raidInfo_al.size(); i++) {
//			if(result[1]!=null) {
//				if(result[1].equals(raidInfo_al.get(i).getRaid_seq())) {
//					raidInfo_al.get(i).setCheck("true");				
//			}
//		}		
//			arr.add(gson.toJson(raidInfo_al.get(i)));
//		}
//		
		
		System.out.println(arr.toString());
		
		// 해당 레이드에 참가중인지 아닌지
		
		
		
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
