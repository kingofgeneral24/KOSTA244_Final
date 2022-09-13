package com.kosta.finalproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.finalproject.dto.MemberDTO;

@Controller
public class MainController {
	// URL을 /로 접근하거나 /home/main으로 접근할 수 있다.
	@GetMapping ({"/","/home/main"})
	public String home(HttpSession session) {
		
		
		
		
		return "home/main"; 
	}

}
