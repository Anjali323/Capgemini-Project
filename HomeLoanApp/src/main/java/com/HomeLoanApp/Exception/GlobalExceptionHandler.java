package com.HomeLoanApp.Exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmptyInputException.class)		//user generated
	public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException){
		return new ResponseEntity<String>("Exception Occured "+emptyInputException.getErrorCode()+" : "+emptyInputException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)								//predefined
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException){
		return new ResponseEntity<String>("Request can't be completed",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NullPointerException.class)								//predefined
	public ResponseEntity<String> handleNullPointerException(NullPointerException e){
		return new ResponseEntity<String>("Requested Property not availaible",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NumberFormatException.class)								//predefined
	public ResponseEntity<String> handleNumberFormatException(NumberFormatException e){
		return new ResponseEntity<String>("Requested Property not valid",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidFormatException.class)								//predefined
	public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException e){
		return new ResponseEntity<String>("Requested Property not valid",HttpStatus.BAD_REQUEST);
	}
	
}
