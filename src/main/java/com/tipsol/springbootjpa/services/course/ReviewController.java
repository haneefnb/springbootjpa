package com.tipsol.springbootjpa.services.course;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tipsol.springbootjpa.services.ErrorResponse;

@RestController
public class ReviewController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	ReviewService reviewService;

	@PostMapping("/review")
	public ResponseEntity<Object> createReview(@RequestBody Review c) {
		ResponseEntity<Object> returnValue = null;
		Review review = reviewService.save(c);
		if (review != null) {
			returnValue = new ResponseEntity<Object>(review, HttpStatus.CREATED);
		} else {
			returnValue = new ResponseEntity<Object>(new ErrorResponse("ERROR", "Unabe To Create"), HttpStatus.CREATED);
		}

		return returnValue;
	}
	
	@GetMapping("/review/{id}")
	public ResponseEntity<Object> getReview(@PathVariable("id") Long id) {
		ResponseEntity<Object> returnValue = null;

		Review review = reviewService.findById(id);
		if (review != null) {
			returnValue = new ResponseEntity<Object>(review, HttpStatus.CREATED);
		} else {
			returnValue = new ResponseEntity<Object>(new ErrorResponse("ERROR", "Unabe To Create"), HttpStatus.CREATED);
		}

		return returnValue;
	}

}
