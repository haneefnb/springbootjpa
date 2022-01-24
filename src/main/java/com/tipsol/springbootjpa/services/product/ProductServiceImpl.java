package com.tipsol.springbootjpa.services.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	public static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductRepository repository;

	@Autowired
	ModelMapper mapper;

	@Autowired
	EntityManager em;

	@Override
	public List<Product> fetchProducts() {
		List<Product> products = new ArrayList<>();

		Iterator<ProductDao> productDaos = repository.findAll().iterator();
		while (productDaos.hasNext()) {
			ProductDao dao = productDaos.next();
			Product dto = mapper.map(dao, Product.class);
			products.add(dto);
		}

		return products;
	}

	public List<Product> fetchProductsByNamedQuery() {
		List<Product> products = new ArrayList<>();

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
		product = mapper.map(dao, Product.class);
		return product;
	}

	@Override
	public Product getProductById(Long id) {
		Optional<ProductDao> prodOp = repository.findById(id);
		if (prodOp.isPresent()) {
			return mapper.map(prodOp.get(), Product.class);
		}
		return null;
	}

	public ProductDao getProductByName(String name) {
		Optional<ProductDao> productDaoOpt = repository.findByName(name);
		if (productDaoOpt.isPresent()) {
			return mapper.map(productDaoOpt.get(), ProductDao.class);
		}
		return null;
	}

	@Override
	public void deleteProductById(Long id) {
		repository.deleteById(id);
	}

	@Async
	public void sendEmail(int i) throws InterruptedException {
		try {
			Thread.sleep(10000);
			// Wontedly throwing an exception, so this will be caught
			int j = 10 / i;
			LOGGER.info("J Value: {}", j);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw e;
		}
		LOGGER.info(Thread.currentThread().getName());
	}

}
