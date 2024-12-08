package com.demo.service;

import java.util.List;

import com.demo.entity.Product;

public interface ProductService {

	void addProduct(Product product);
	
	List<Product> allProducts();
}
