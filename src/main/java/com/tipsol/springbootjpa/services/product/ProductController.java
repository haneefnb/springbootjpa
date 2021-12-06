package com.tipsol.springbootjpa.services.product;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tipsol.springbootjpa.services.ErrorResponse;
import com.tipsol.springbootjpa.services.SuccessResponse;

@RestController
public class ProductController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	ProductService productService;
	

	@GetMapping("/products")
	public ResponseEntity<Object> getProducts() {
		ResponseEntity<Object> returnValue = null;
		List<Product> products = productService.fetchProducts();
		if (products != null) {
			returnValue = new ResponseEntity<>(products, HttpStatus.OK);
		} else {
			returnValue = new ResponseEntity<>(new ErrorResponse("NO_RECORDS", "No Records Found"),
					HttpStatus.NOT_FOUND);
		}
		return returnValue;
	}

	@PostMapping("/product")
	public ResponseEntity<Object> addProduct(@RequestBody Product p) {
		ResponseEntity<Object> returnValue = null;
		Product product = productService.createProduct(p);
		if (product != null) {
			returnValue = new ResponseEntity<Object>(product, HttpStatus.CREATED);
		} else {
			returnValue = new ResponseEntity<Object>(new ErrorResponse("ERROR", "Unabe To Create"), HttpStatus.CREATED);
		}

		return returnValue;
	}

	@PutMapping("/product")
	public ResponseEntity<Object> updateProduct(@RequestBody Product p) {
		ResponseEntity<Object> returnValue = null;
		Product product = null;

		if (p.getId() == null) {
			product = productService.createProduct(p);
		} else {
			product = productService.createProduct(p);
		}

		if (product != null) {
			returnValue = new ResponseEntity<Object>(product, HttpStatus.CREATED);
		} else {
			returnValue = new ResponseEntity<Object>(new ErrorResponse("ERROR", "Unabe To Create"), HttpStatus.CREATED);
		}

		return returnValue;
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable("id") Long id) {
		ResponseEntity<Object> returnValue = null;

		Product product = productService.getProductById(id);
		if (product != null) {
			returnValue = new ResponseEntity<Object>(product, HttpStatus.CREATED);
		} else {
			returnValue = new ResponseEntity<Object>(new ErrorResponse("ERROR", "Unabe To Create"), HttpStatus.CREATED);
		}

		return returnValue;
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
		ResponseEntity<Object> returnValue = null;

		productService.getProductById(id);
		returnValue = new ResponseEntity<Object>(new SuccessResponse("SUCCESS", "Deletion Successful"), HttpStatus.OK);

		return returnValue;
	}
	
	@GetMapping("/product/email/{div}")
	public ResponseEntity<Object> sendEmail(@PathVariable("div") int div) {
		ResponseEntity<Object> returnValue = null;
		productService.sendEmail(div);
		returnValue = new ResponseEntity<Object>
			(new SuccessResponse("SUCCESS", "Email Sent Successfully "+
		Thread.currentThread().getName()),
					HttpStatus.OK);
		
		return returnValue;
	}

}
