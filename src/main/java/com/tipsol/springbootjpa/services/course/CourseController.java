package com.tipsol.springbootjpa.services.course;

import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tipsol.springbootjpa.services.ErrorResponse;
import com.tipsol.springbootjpa.services.SuccessResponse;

@RestController
public class CourseController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	CourseService courseService;

	@PostMapping("/course")
	public ResponseEntity<Object> createCourse(@RequestBody Course c) {
		ResponseEntity<Object> returnValue = null;
		Course course = courseService.save(c);
		if (course != null) {
			returnValue = new ResponseEntity<Object>(course, HttpStatus.CREATED);
		} else {
			returnValue = new ResponseEntity<Object>(new ErrorResponse("ERROR", "Unabe To Create"), HttpStatus.CREATED);
		}

		return returnValue;
	}
	
	@GetMapping("/course/{id}")
	public ResponseEntity<Object> getCourse(@PathVariable("id") Long id) {
		ResponseEntity<Object> returnValue = null;

		Course course = courseService.findById(id);
		if (course != null) {
			returnValue = new ResponseEntity<Object>(course, HttpStatus.CREATED);
		} else {
			returnValue = new ResponseEntity<Object>(new ErrorResponse("ERROR", "Unabe To Create"), HttpStatus.CREATED);
		}

		return returnValue;
	}
	
	@DeleteMapping("/course/{id}")
	public ResponseEntity<Object> deleteCourse(@PathVariable("id") Long id) {
		ResponseEntity<Object> returnValue = null;
		courseService.deleteById(id);
		returnValue = new ResponseEntity<Object>(new SuccessResponse("SUCCESS", "Deletion Successful"), HttpStatus.OK);
		return returnValue;
	}

}
