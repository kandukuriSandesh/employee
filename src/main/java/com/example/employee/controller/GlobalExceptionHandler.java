package com.example.employee.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, Object> handleIllegalArgument(IllegalArgumentException ex) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", ex.getMessage());
		return response;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, Object> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Validation failed");
		return response;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, Object> handleValidation(Exception ex) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Validation failed");
		return response;
	}
}
