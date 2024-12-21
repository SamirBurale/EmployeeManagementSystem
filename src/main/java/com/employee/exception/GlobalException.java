package com.employee.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	   @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>(); 
	        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	            errors.put(error.getField(), error.getDefaultMessage());
	        }
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
	   
	   @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ExceptionHandler(EmployeeNotFoundException.class)
	    public Set<String> handlePatientNotFoundException(EmployeeNotFoundException ex){

	       // return  new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	        return  Collections.singleton(ex.getMessage());

	    }
}
