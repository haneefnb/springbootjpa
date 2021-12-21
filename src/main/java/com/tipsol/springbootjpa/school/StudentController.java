package com.tipsol.springbootjpa.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipsol.springbootjpa.commondto.ErrorInfo;
import com.tipsol.springbootjpa.commondto.ResponseInfo;
import com.tipsol.springbootjpa.commondto.ResponseType;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/student")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {

		Student s = studentService.createStudent(student);
		try {
			if (s != null) {
				return new ResponseEntity<Object>(
						new ResponseInfo(ResponseType.SUCCESS, "Student Created Successfully!!!", s),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Object>(new ErrorInfo("E101", "Some thing went wrong"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ErrorInfo("E101", "Some thing went wrong"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/students")
	public ResponseEntity<Object> getStudents() {
		ResponseEntity<Object> retList = null;
		List<Student> studList = studentService.getStudents();
		try {
			if (studList != null) {
				retList = new ResponseEntity<Object>(
						new ResponseInfo(ResponseType.SUCCESS, "", studList),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			retList = new ResponseEntity<Object>(new ErrorInfo("E101", "Some thing went wrong"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return retList;

	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Object> getStudent(@PathVariable("id") Long id) {
		ResponseEntity<Object> returnValue = null;
		Student student = studentService.getStudent(id);
		try {
			if (student != null) {
				returnValue = new ResponseEntity<Object>(
						new ResponseInfo(ResponseType.SUCCESS, "", student),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			returnValue = new ResponseEntity<Object>(new ErrorInfo("E101", "Some thing went wrong"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return returnValue;

	}
}
