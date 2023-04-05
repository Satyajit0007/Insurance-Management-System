package com.IMTCare.exception;


import java.time.LocalDateTime;

public class MyErrorDetails {
    
	 private LocalDateTime TimeStamp;
	 private String message;
	 private String details;
	
	 
	 public MyErrorDetails() {
		
		// TODO Auto-generated constructor stub
	}


	public MyErrorDetails(LocalDateTime timeStamp, String message, String details) {
		super();
		TimeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}


	public LocalDateTime getTimeStamp() {
		return TimeStamp;
	}


	public void setTimeStamp(LocalDateTime timeStamp) {
		TimeStamp = timeStamp;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}
	
	 
	 
	 
	 
}
