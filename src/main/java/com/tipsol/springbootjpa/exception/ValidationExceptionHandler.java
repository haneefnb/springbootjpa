package com.tipsol.springbootjpa.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tipsol.springbootjpa.commondto.ErrorInfo;

@ControllerAdvice
public class ValidationExceptionHandler {

	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<Object> handlePrefixInValidException(ConstraintViolationException e, WebRequest request) {
		
		List<String> messages =  e.getConstraintViolations().parallelStream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
		
		ErrorInfo error = new ErrorInfo("ERR2",messages);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleException(MethodArgumentNotValidException e, WebRequest request) {
		
		List<String> errorMessages = e.getBindingResult().
				getAllErrors().stream().
				map(x->x.getDefaultMessage()).
				collect(Collectors.toList());
		
		ErrorInfo error = new ErrorInfo("ERR1", errorMessages);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
