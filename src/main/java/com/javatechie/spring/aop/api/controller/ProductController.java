package com.javatechie.spring.aop.api.controller;

import java.util.List;

import com.javatechie.spring.aop.api.advice.ExecutionTimeTrackerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.aop.api.model.Product;
import com.javatechie.spring.aop.api.service.ProductService;

@RestController
public class ProductController {

	Logger logger=LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;


	@PostMapping("/save")
	public List<Product> saveProducts(@RequestBody List<Product> products) {

		List<Product> pList = service.addProduct(products);

		return pList;
	}

	@GetMapping("/findProducts")
	public List<Product> getProducts() {
		List<Product> products = null;
		try {
			products = service.findAllProducts();
		} catch (Exception e) {
			logger.error("Exception in controller " + e.getMessage());
		}
	
		return products;
	}

}
