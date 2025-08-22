package com.example.WeatherInfoManager.Weather.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
	 @ExceptionHandler(InvalidPincodeException.class)
	    public ResponseEntity<Object> handleInvalidPincode(InvalidPincodeException ex) 
	 {
	        Map<String, Object> body = new HashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("status", HttpStatus.BAD_REQUEST.value());
	        body.put("error", "Invalid Pincode");
	        body.put("message", ex.getMessage());
	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	  }
	 
	 @ExceptionHandler(InvalidDateException.class)
	    public ResponseEntity<Object> handleInvalidDate(InvalidDateException ex)
	 {
	        Map<String, Object> body = new HashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("status", HttpStatus.BAD_REQUEST.value());
	        body.put("error", "Invalid Date");
	        body.put("message", ex.getMessage());
	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	  }
	 
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<Object> handleGeneralError(Exception ex)
	 {
	        Map<String, Object> body = new HashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        body.put("error", "Internal Server Error");
	        body.put("message", ex.getMessage());
	        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
