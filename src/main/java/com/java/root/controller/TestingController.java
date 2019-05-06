package com.java.root.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.root.service.TestingServiceInterface;
import com.java.root.utile.HttpUtile;
import com.java.root.utile.SessionUtile;

@Controller
public class TestingController {

	@Autowired
	TestingServiceInterface tsi;
	
	@Autowired
	SqlSession session;
	
	/************************
	 *evaluate.jsp functions*
	 ************************/
	
	@RequestMapping(value = "/testing/sendEval", method = RequestMethod.POST)
	public void sendEval (HttpSession sess, HttpServletResponse resp, HttpServletRequest req) {
		if (SessionUtile.checkSession(sess)) {
			HashMap<String, Object> paramMap = HttpUtile.getParam(req);
			int state = session.insert("testing.sendEval", paramMap);
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("state", state);
			HttpUtile.printJson(resp, resultMap);
		} else {
			// 예외 처리 넣어라.
		}
	}
	
	
}
