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
public class PassportController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	PassportService passportService;

	@PostMapping("/passport")
	public ResponseEntity<Object> createPassport(@RequestBody Passport stud) {
		ResponseEntity<Object> returnValue = null;
		Passport passport = passportService.save(stud);
		if (passport != null) {
			returnValue = new ResponseEntity<Object>(passport, HttpStatus.CREATED);
		} else {
			returnValue = new ResponseEntity<Object>(new ErrorResponse("ERROR", "Unabe To Create"), HttpStatus.CREATED);
		}

		return returnValue;
	}
	
	@GetMapping("/passport/{id}")
	public ResponseEntity<Object> getPassport(@PathVariable("id") Long id) {
		ResponseEntity<Object> returnValue = null;

		Passport passport = passportService.findById(id);
		if (passport != null) {
			returnValue = new ResponseEntity<Object>(passport, HttpStatus.CREATED);
		} else {
			returnValue = new ResponseEntity<Object>(new ErrorResponse("ERROR", "Unabe To Create"), HttpStatus.CREATED);
		}

		return returnValue;
	}

}
