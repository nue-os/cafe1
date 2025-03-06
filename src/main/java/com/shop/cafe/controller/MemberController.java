package com.shop.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Member;
import com.shop.cafe.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@PostMapping("login")
	public Map<String, String> login(@RequestBody Member m, HttpServletRequest request) { // Spring Framework는 HTTP 요청이 들어오면 서블릿 컨테이너가 HttpServletRequest 객체를 생성하고, 컨트롤러 메서드에 자동 주입
		Map<String, String> responseData = new HashMap<>();
		try {
			m = memberService.login(m); // return 받은 Member 객체가 m과 같은 주소를 가리키기 때문에 새로운 Member 객체를 만들지 않고 기존 m에 할당
			if(m != null) { // login 성공
				// 사용자 정보 유지를 위해 session 사용
				HttpSession session = request.getSession();
				System.out.println(session.getId()); // 부여받은 sessionID
				session.setAttribute("member", m);
				
				responseData.put("msg", "ok");
			} else { // login 실패
				responseData.put("msg", "다시 로그인 해주세요.");
			}
		} catch (Exception e) { // login 에러
			e.printStackTrace();
			responseData.put("msg", "다시 로그인 해주세요.");
		}
		return responseData;
	}
	
	@PostMapping("insertMember")
	public Map<String, String> insertMember(@RequestBody Member m) {
		Map<String, String> responseData = new HashMap<>();
		
		try {
			memberService.insertMember(m);
			responseData.put("msg", "ok");
		} catch(Exception e) {
			e.printStackTrace();
			responseData.put("msg", e.getMessage());
		}
		
		return responseData;
	}
}
