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
		// ���̵� ȭ��(����ȭ��X) ���� �Ѹ��� ����
		// �ȵ忡�� m_id �޴´�.
		// RaidVO(raid_seq, raid_kind, raid_name, raid_cnt, reg_date)�� ��ȯ�Ѵ�.
		
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
