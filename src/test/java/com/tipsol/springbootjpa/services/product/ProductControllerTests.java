package com.tipsol.springbootjpa.services.product;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tipsol.springbootjpa.dto.Product;
import com.tipsol.springbootjpa.service.ProductService;

@WebMvcTest
public class ProductControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void given_product_when_savedProduct_thenReturn_created_Product() throws JsonProcessingException, Exception {
		Product product = Product.builder()
				.name("Product1")
				.description("A Test Product")
				.manfDate(LocalDate.now())
				.price(10000.00d).build();
		
		// If you pass any Product Class Type
		// Then it return the same class Type, because createProduct 
		// Method takes & return same type.
		// createProduct accept only one parameter, thats why it is getArgument(0)
		given(productService.
				createProduct(any(Product.class)))
					.willAnswer((invocation)->invocation.getArgument(0));
		
		//This is to calling Controller End point, we are converting product obj to String
		// Using Object mapper.
		ResultActions response =  mockMvc.perform(post("/product").
				contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)));
		
		//Verify the response using Assert statements.
		response
			 .andDo(print())
		     .andExpect(status().isCreated())
			 //.andExpect(status().is4xxClientError())//Negative Test
		     .andExpect(jsonPath("$.name",is(product.getName())));
		
	}
	
}
