package com.shop.cafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Product;
import com.shop.cafe.service.ProductService;

@RestController // 뷰 템플릿을 갖지 않고, JSON이나 XML 같은 응답 본문으로 반환
@CrossOrigin("http://127.0.0.1:5500")
public class ProductController {
	
	@Autowired // DI(Dependency Injection): new를 사용하지 않고, 스프링이 자동으로 객체를 생성하고 주입, 스프링 빈(Bean)으로 등록된 클래스(Component, Service, Repository 등)에만 사용 가능
	ProductService productService;
	
	@GetMapping("getAllProducts")
	public List<Product> getAllProducts() {
		try {
			return productService.getAllProducts();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
