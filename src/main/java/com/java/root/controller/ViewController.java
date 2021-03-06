package com.java.root.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.root.utile.SessionUtile;

@Controller
public class ViewController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "redirect:/main";
//		return "redirect:user/select?sso=0000";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			return "main";
		}else {
			return "redirect:/login";
		}		
	}
	
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage_professor(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			HashMap<String, Object> userMap = SessionUtile.getSession(session);
			int authorization = (int) userMap.get("authorization");
			
			// authorization: 0=학생, 1=팀장, 2=교수, 3=학과장
			if(authorization == 0 || authorization == 1) {
				return "mypage/student";
			}else if(authorization == 2 || authorization == 3) {
				return "mypage/professor";
			}else {
				return "redirect:/login";
			}
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public String board_detail(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			return "board/detail";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String board_write(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			return "board/write";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/testing/evaluate", method = RequestMethod.GET)
	public String testing_write(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			return "testing/evaluate";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/testing/evalsetting", method = RequestMethod.GET)
	public String evalsetting(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			HashMap<String, Object> userMap = SessionUtile.getSession(session);
			int authorization = (int) userMap.get("authorization");
			if(authorization == 3 || authorization == 4)
				return "testing/evalsetting";
			else
				return "redirect:/main";
		}
		else {
			return "redirect:/main";
		}
	}
	
	@RequestMapping(value = "/testing/finaleval", method = RequestMethod.GET)
	public String finaleval(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			HashMap<String, Object> userMap = SessionUtile.getSession(session);
			int authorization = (int) userMap.get("authorization");
			if(authorization == 3 || authorization == 4)
				return "testing/finaleval";
			else
				return "redirect:/main";
		}
		else {
			return "redirect:/main";
		}
	}
	
	
	@RequestMapping(value = "/testing/evalresult", method = RequestMethod.GET)
	public String testing_evaluatelist(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			return "testing/evalresult";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/testing/finalcheck", method = RequestMethod.GET)
	public String testing_finalcheck(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			return "testing/finalcheck";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/testing/evalread", method = RequestMethod.GET)
	public String evalread(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			return "/testing/evalread";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String manager(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			HashMap<String, Object> userMap = SessionUtile.getSession(session);
			int authorization = (int) userMap.get("authorization");
			if(authorization == 4)
				return "admin";
			else
				return "redirect:/adminlogin";
		}else {
			return "redirect:/adminlogin";
		}
	}
	
	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public String adminlogin() {
		return "adminlogin";
	}
	
	@RequestMapping(value = "/testing/evalselect", method = RequestMethod.GET)
	public String evalselect(HttpSession session) {
		if(SessionUtile.checkSession(session)) {
			return "/testing/evalselect";
		}else {
			return "redirect:/adminlogin";
		}
	}
	
}
