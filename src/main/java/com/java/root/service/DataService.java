package com.java.root.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.root.dao.DataDaoInterface;

@Service
public class DataService implements DataServiceInterface {

	@Autowired
	DataDaoInterface ddi;
	
	HashMap<String, Object> paramMap;
	
	@Override
	public void selectList(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String mes = "";
		try {
			boolean check = true;
			String sso = req.getParameter("sso");
			// 파라메터 확인
			if(null == sso || ("").equals(sso)) {
				check = false;
				mes = "파라메터 확인 바랍니다.";
			}	
			
			// 데이터베이스 실행 여부
			if(check) {
				paramMap = new HashMap<String, Object>();
				paramMap.put("sso", sso);
				System.out.println(paramMap.toString());
				List<HashMap<String, Object>> list = ddi.selectList(paramMap);
				if(list.size() > 0) {
					for(int i = 0; i < list.size(); i++) {
						mes += list.get(i).toString() + "\n";
					}
				}else {
					mes = sso + " 값은 검색 내용이 없습니다.";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resp.getWriter().write(mes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
