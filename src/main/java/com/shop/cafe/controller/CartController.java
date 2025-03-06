package com.shop.cafe.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
public class CartController {
	@PostMapping("addCart")
	public String addCarT(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		System.out.println("addCart 호출됨 " +  session);
		return "ok";
	}
}
