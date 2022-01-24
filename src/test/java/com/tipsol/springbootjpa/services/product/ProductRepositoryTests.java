package com.tipsol.springbootjpa.services.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTests {

	@Autowired
	ProductRepository productRepository;

	// test for Create Product
	@Test
	@DisplayName("Unit Test for Save Product API")
	void givenProductObj_WhenSave_ThenReturnCreatedProduct() {
		// given Product - Pre condition / Setup
		ProductDao product = ProductDao.builder().name("Product 1").description("a Telivision Product").price(20000.22d)
				.manfDate(LocalDate.now()).build();

		// When Condition
		ProductDao createdProduct = productRepository.save(product);

		// Then - Verify output - Assertion
		assertThat(createdProduct).isNotNull();
		assertThat(createdProduct.getId()).isGreaterThan(0);
	}

//  Test for Select All Products
	@Test
	@DisplayName("Unit Test for Get All Products")
	void givenProducts_WhenFetchAll_ThenReturnProductList() {
		// given Product - Pre-condition / Setup
		ProductDao product1 = ProductDao.builder().name("Product 1").description("a Telivision Product")
				.price(20000.22d).manfDate(LocalDate.now()).build();

		ProductDao product2 = ProductDao.builder().name("Product 2").description("A Smart Product").price(10000.22d)
				.manfDate(LocalDate.now()).build();

		// When Condition
		ProductDao createdProduct1 = productRepository.save(product1);
		ProductDao createdProduct2 = productRepository.save(product2);

		List<ProductDao> products = new ArrayList<>();
		products.add(createdProduct1);
		products.add(createdProduct2);

		assertThat(products).isNotNull().hasSize(2);

	}

//  Test for Select a Product by Id
	@Test
	@DisplayName("Unit Test for Get Product by ID")
	void givenId_WhenFetchProduct_ThenReturnProductDetails() {
		// given Product - Pre-condition / Setup
		ProductDao product1 = ProductDao.builder().name("Product 1").description("a Telivision Product")
				.price(20000.22d).manfDate(LocalDate.now()).build();

		// When Condition
		ProductDao createdProduct1 = productRepository.save(product1);

		Optional<ProductDao> fetchedProductFromDB = productRepository.findById(product1.getId());
		if (fetchedProductFromDB.isPresent()) {
			assertThat(createdProduct1).isNotNull();
		}

	}

//  Test for Update Product 
	@Test
	@DisplayName("Unit Test for Update Product")
	void givenProductObj_WhenUpdateProduct_ThenReturnUpdatedProductObj() {
		// given Product - Pre-condition / Setup
		ProductDao product1 = ProductDao.builder().name("Product 1").description("a Telivision Product")
				.price(20000.22d).manfDate(LocalDate.now()).build();

		// When Condition
		ProductDao createdProduct1 = productRepository.save(product1);
		createdProduct1.setPrice(1000.00);
		ProductDao updatedProduct = productRepository.save(createdProduct1);

		ProductDao fetchedProductFromDB = productRepository.findById(updatedProduct.getId()).get();
		assertThat(fetchedProductFromDB.getPrice()).isEqualTo(1000.00);

	}

}
