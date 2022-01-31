package com.tipsol.springbootjpa.services.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tipsol.springbootjpa.dao.ProductDao;
import com.tipsol.springbootjpa.dto.Product;
import com.tipsol.springbootjpa.repository.ProductRepository;
import com.tipsol.springbootjpa.service.ProductServiceImpl;;


@ExtendWith(SpringExtension.class)
class ProductServiceTests {
	
	@Mock
	ProductRepository productRepository;
	
	@Mock
	ModelMapper mapper;
	
	@InjectMocks
	ProductServiceImpl productService;
	

	@Test
	@DisplayName("givenProductObjWhenSaveProductThenReturnSavedProduct")
	void givenProductObjWhenSaveProductThenReturnSavedProduct() {

		Product product = Product.builder()
				.name("Product1")
				.description("A Test Product")
				.manfDate(LocalDate.now())
				.price(10000.00d).build();
		
		ProductDao productDao = ProductDao.builder()
				.name("Product1")
				.description("A Test Product")
				.manfDate(LocalDate.now())
				.price(10000.00d).build();


		
		BDDMockito.given(mapper.map(product,ProductDao.class)).willReturn(productDao);
		
		BDDMockito.given(mapper.map(productDao,Product.class)).willReturn(product);
		
		BDDMockito.given(productRepository.save(productDao)).willReturn(productDao);
		

		// Calling Business Method
		Product createdProduct = productService.createProduct(product);

		// Testing
		assertThat(createdProduct).isNotNull();

	}

}
