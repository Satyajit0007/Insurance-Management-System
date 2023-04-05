package com.IMTCare.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {
    
	@Id
	@Column(unique = true)
	private Long CurrentUserId;
	
	
	private String role;
	 
    private String uuid;

    private LocalDateTime localDateTime;

   
}