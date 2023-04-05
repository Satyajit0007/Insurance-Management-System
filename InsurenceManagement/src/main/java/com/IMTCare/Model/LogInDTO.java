package com.IMTCare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInDTO {
	private String MobileNo;
	private String password;
	
	@JsonIgnore
	private String role;
}