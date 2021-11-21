package com.tipsol.springbootjpa.services.product;

import java.util.List;

public interface ProductService {
	
	public List<Product> fetchProducts();
	
	public Product createProduct(Product product);
	
	public Product getProductById(Long id);
	
	public void deleteProductById(Long id);
	
}
