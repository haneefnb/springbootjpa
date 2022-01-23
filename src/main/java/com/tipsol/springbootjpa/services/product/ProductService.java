package com.tipsol.springbootjpa.services.product;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {
	
	public List<Product> fetchProducts();
	
	public Product createProduct(Product product);
	
	public Product getProductById(Long id);
	
	public ProductDao getProductByName(String name);
	
	public void deleteProductById(Long id);
	
	public void sendEmail(int i) throws InterruptedException;
	
}
