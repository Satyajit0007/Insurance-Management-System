package com.IMTCare.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table  (name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

	@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
	@Pattern(regexp = "^[a-zA-Z]*$" , message ="First name should not Contain Number and Special Character ")
    @Column(name = "firstname")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]*$" , message ="Middle name should not Contain Number and Special Character ")
    @Column(name = "middletname")
    private String middleName;
    
    @NotBlank
   	@Pattern(regexp = "^[a-zA-Z]*$" , message ="Last name should not Contain Number and Special Character ")
    @Column(name = "lastname")
    private String lastName;

    @NotNull
    @Column(name = "dob")
    private LocalDate dob;

    @NotBlank
    @Column(name = "address")
    private String address;

 

    @NotBlank
    @Column(name = "mobile")
    @Size(min =10 , max =10 , message = "Mobile number must have 10 digits")
    private String mobileNo;
    
    
    @NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}$", message = "Password should be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit")
	private String password;
    
    
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<InsurancePolicy> insurancePolicies = new ArrayList<>();

    
}

