package com.tipsol.springbootjpa.services.course;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tipsol.springbootjpa.commondto.ResponseInfo;
import com.tipsol.springbootjpa.services.ErrorResponse;

@RestController
public class StudentController {

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	ResourceBundleMessageSource messageSource;
	

	@Autowired
	StudentService studentService;

	@PostMapping("/student")
	public ResponseEntity<Object> createStudent(@Valid @RequestBody Student stud) {
		ResponseEntity<Object> returnValue = null;

		Student student = studentService.save(stud);
		if (student != null) {
			returnValue = new ResponseEntity<Object>(
					new ResponseInfo("Success",messageSource.getMessage("Sudent_Create_success_message",null,
							LocaleContextHolder.getLocale()) ,
							student), 
					HttpStatus.CREATED);
		} else {
			returnValue = new ResponseEntity<Object>(new ErrorResponse("ERROR", "Unabe To Create"), HttpStatus.CREATED);
		}

		return returnValue;
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Object> getStudent(@PathVariable("id") Long id) {
		ResponseEntity<Object> returnValue = null;

		Student student = studentService.findById(id);
		if (student != null) {
			returnValue = new ResponseEntity<Object>(student, HttpStatus.CREATED);
		} else {
			returnValue = new ResponseEntity<Object>(new ErrorResponse("ERROR", "Unabe To Create"), HttpStatus.CREATED);
		}

		return returnValue;
	}

}
