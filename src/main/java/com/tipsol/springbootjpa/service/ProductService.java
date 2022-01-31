package com.tipsol.springbootjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tipsol.springbootjpa.dao.ProductDao;
import com.tipsol.springbootjpa.dto.Product;

@Service
public interface ProductService {
	
	public List<Product> fetchProducts();
	
	public Product createProduct(Product product);
	
	public Product getProductById(Long id);
	
	public ProductDao getProductByName(String name);
	
	public void deleteProductById(Long id);
	
	public void sendEmail(int i) throws InterruptedException;
	
}
