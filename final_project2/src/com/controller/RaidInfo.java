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
		// ���̵� ȭ��(����ȭ��X) ���� �Ѹ��� ����
		// �ȵ忡�� m_id �޴´�.
		// RaidVO(raid_seq, raid_kind, raid_name, raid_cnt, reg_date)�� ��ȯ�Ѵ�.
		
		String m_id = request.getParameter("m_id");
		String raid_seq_pull = request.getParameter("raid_seq_pull");
		String raid_seq_sqt = request.getParameter("raid_seq_sqt");
		String raid_seq_push = request.getParameter("raid_seq_push");
		
		System.out.println("���±��� ���̵������ : "+ raid_seq_pull);
		System.out.println("���±��� ���̵������ : "+ raid_seq_sqt);
		System.out.println("���±��� ���̵������ : "+ raid_seq_push);
		
		//String raid_seq = request.getParameter("raid_seq");
		
		System.out.println(m_id);
		
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); //  DateFormat������ ����
		RaidDAO dao = new RaidDAO();
		JsonArray arr = new JsonArray();
		
		
		ArrayList<RaidVO> appRaid_al = dao.appRecord(m_id, raid_seq_pull, raid_seq_sqt, raid_seq_push);
		// ���⼭ �������� raid_seq�� ����� ���´�.
		
		
		ArrayList<RaidVO> raidInfo_al = dao.raidInfo(m_id);
		// ���̵� ȭ�鿡 �Ѹ� �����͵�
		

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
		
		// �ش� ���̵忡 ���������� �ƴ���
		
		
		
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
//		String json; //�Ľ��� ���� ������ ����ֱ� ���� ���ڿ�
//		
//		//������ ���� JSON����
//		public JSONAdd(){
//			//JSON������ ������ ���κ��� ä��������
//			//JSONObject 3���� ���� ������ ����
//			Gson gson = new Gson();
//			gson.toJson(src)data1 = new JSONObject();
//			data1.put("professor", "�豳��");
//			data1.put("student", "���л�");
//
//			JSONObject data2 = new JSONObject();
//			data2.put("professor", "�ڱ���");
//			data2.put("student", "���л�");
//			
//			JSONObject data3 = new JSONObject();
//			data3.put("professor", "�ѱ���");
//			data3.put("student", "Ȳ�л�");
//			
//			//������ ���� 3���� ������Ʈ �����͸� JSONArray�� ������� ����
//			JSONArray arr = new JSONArray();
//			arr.add(data1);//0���ε���
//			arr.add(data2);//1���ε���
//			arr.add(data3);//2���ε���
//			
//			//���������� univ������Ʈ�� JSON�迭 ����
//			JSONObject univ = new JSONObject();
//			univ.put("univ", arr);
//			
//			//�Ľ��� ������ ����
//			json = univ.toJSONString();
//			
//			//�׽�Ʈ�� ���
//			//System.out.println(json);
//		}
//	}
