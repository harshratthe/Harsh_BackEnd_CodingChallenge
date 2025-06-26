package com.example.demo.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(BookNotFoundException.class)
	    public ResponseEntity<ErrorDetails> handleBookNotFound(BookNotFoundException ex, WebRequest request) {
	        ErrorDetails error = new ErrorDetails(
	                LocalDateTime.now(),
	                ex.getMessage(),
	                request.getDescription(false),
	                "BOOK_NOT_FOUND"
	        );
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }

	 
	    @ExceptionHandler(ISBNAlreadyExistsException.class)
	    public ResponseEntity<ErrorDetails> handleDuplicateIsbn(ISBNAlreadyExistsException ex, WebRequest request) {
	        ErrorDetails error = new ErrorDetails(
	                LocalDateTime.now(),
	                ex.getMessage(),
	                request.getDescription(false),
	                "DUPLICATE_ISBN"
	        );
	        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	    }

	   
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorDetails> handleOtherExceptions(Exception ex, WebRequest request) {
	        ErrorDetails error = new ErrorDetails(
	                LocalDateTime.now(),
	                ex.getMessage(),
	                request.getDescription(false),
	                "INTERNAL_SERVER_ERROR"
	        );
	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
	
	
}
