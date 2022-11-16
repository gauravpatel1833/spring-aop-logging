package com.javatechie.spring.aop.api.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import com.javatechie.spring.aop.api.advice.TrackExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.aop.api.model.Product;
import com.javatechie.spring.aop.api.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@PostConstruct
	public void initDB() {
		repository.saveAll(Stream.of(new Product(101, "Book", 499), new Product(102, "laptop", 78000))
				.collect(Collectors.toList()));
	}

	@TrackExecutionTime
	public List<Product> addProduct(List<Product> products) {
		return repository.saveAll(products);
	}

	/**
	 * Used this annotation to log the time taken by this method to execute.
	 */
	@TrackExecutionTime
	public List<Product> findAllProducts() {
		//int i = 1/0;
		return repository.findAll();
	}

}
