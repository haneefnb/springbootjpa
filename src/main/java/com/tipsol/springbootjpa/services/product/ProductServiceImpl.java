package com.tipsol.springbootjpa.services.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repository;

	@Autowired
	ModelMapper mapper;

	@Autowired
	EntityManager em;

	@Override
	public List<Product> fetchProducts() {
		List<Product> products = new ArrayList<Product>();

		Iterator<ProductDao> productDaos = repository.findAll().iterator();
		while (productDaos.hasNext()) {
			ProductDao dao = productDaos.next();
			Product dto = mapper.map(dao, Product.class);
			products.add(dto);
		}

		return products;
	}

	public List<Product> fetchProductsByNamedQuery() {
		List<Product> products = new ArrayList<Product>();

		List<ProductDao> productDaos = em.createNamedQuery("ProductDao.products", ProductDao.class).getResultList();

		for (ProductDao dao : productDaos) {
			products.add(mapper.map(dao, Product.class));
		}
		return products;
	}

	@Override
	public Product createProduct(Product product) {
		ProductDao dao = mapper.map(product, ProductDao.class);
		dao = repository.save(dao);
		Product returnValue = mapper.map(dao, Product.class);
		return returnValue;
	}

	@Override
	public Product getProductById(Long id) {
		ProductDao dao = repository.findById(id).get();
		Product dto = mapper.map(dao, Product.class);
		return dto;
	}

	@Override
	public void deleteProductById(Long id) {
		repository.deleteById(id);
	}

}
