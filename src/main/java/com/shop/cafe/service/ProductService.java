package com.shop.cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.cafe.dao.ProductDao;
import com.shop.cafe.dto.Product;

@Service // Component로 해도 되지만 Service 컴포넌트임을 명시
public class ProductService {
	@Autowired // DI(Dependency Injection): new를 사용하지 않고, 스프링이 자동으로 객체를 생성하고 주입, 스프링 빈(Bean)으로 등록된 클래스(Component, Service, Repository 등)에만 사용 가능
	ProductDao productDao;
	
	public List<Product> getAllProducts() throws Exception{
		return productDao.getAllProducts();
	}
}
