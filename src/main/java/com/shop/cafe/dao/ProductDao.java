package com.shop.cafe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.shop.cafe.dto.Product;

@Component
public class ProductDao {
	@Value("${spring.datasource.driver-class-name}")
	private String DB_DRIVER;
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String DB_USER;
	
	@Value("${spring.datasource.password}")
	private String DB_PW;
	
	public List<Product> getAllProducts() throws Exception {
		// JDBC 6단계
		// 1. 드라이버 등록
		Class.forName(DB_DRIVER);

		String sql = "select * from product";

		// try-with-resources 기능: AutoCloseable을 구현한 객체만 try 메서드 안에 표현 가능
		try (
				// 2. 연결
				Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);

				// 3. Statement 생성
				PreparedStatement stmt=con.prepareStatement(sql);

				// 4. SQL 전송
				ResultSet rs = stmt.executeQuery();

		) {

			// 5. 결과 받기
			List<Product> list = new ArrayList<>();
			while (rs.next()) {
				int prodcode = rs.getInt("prodcode");
				String prodname = rs.getString("prodname");
				int price = rs.getInt("price");
				String pimg = rs.getString("pimg");

				list.add(new Product(prodcode, price, prodname, pimg));
			}

			return list;

		}
	}

}
