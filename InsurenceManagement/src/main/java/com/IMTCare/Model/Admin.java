package com.IMTCare.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private Long adminId;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z ]{3,20}$" , message = "Name must be 3 chareter and not contain numbers or special characters")
	private String name;
	
	
	@NotNull
	@Size(min =10 , max =10 , message = "Mobile number must have 10 digits")
	private String mobileNo;
	
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}$", message = "Password should be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit")
	private String password;
	
	
}
