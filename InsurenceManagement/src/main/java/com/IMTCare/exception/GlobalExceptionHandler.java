package com.IMTCare.exception;


import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler  {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> userExceptionHandler(UserException ue, WebRequest req){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ue.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.NOT_FOUND);
	}
		
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> loginExceptionHandler(LoginException le, WebRequest req){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(le.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ClaimException.class)
	public ResponseEntity<MyErrorDetails> ClaimExceptionHandler(ClaimException le, WebRequest req){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(le.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<MyErrorDetails> ClientExceptionHandler(ClientException le, WebRequest req){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(le.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PolicyException.class)
	public ResponseEntity<MyErrorDetails> PolicyExceptionHandler(PolicyException le, WebRequest req){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(le.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}


	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception e, WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	
	
	}
	

	
	
}
