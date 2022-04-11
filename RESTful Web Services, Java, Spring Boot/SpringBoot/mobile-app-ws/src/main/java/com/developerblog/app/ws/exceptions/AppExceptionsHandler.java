package com.developerblog.app.ws.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.developerblog.app.ws.ui.modal.response.ErrorMessage;

import java.util.Date;


@ControllerAdvice
public class AppExceptionsHandler {

	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object>handleUserServiceException(
							UserServiceException ex, WebRequest request){
		
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	//A universal exception handling method
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object>handleUserOtherExceptions(Exception ex, WebRequest request){
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
}
