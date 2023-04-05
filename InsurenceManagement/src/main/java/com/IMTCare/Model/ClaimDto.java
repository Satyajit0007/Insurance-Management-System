package com.IMTCare.Model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClaimDto {
    private Long id;
    private Long userId;
    private String description;
    private LocalDate claimDate;
    private ClaimStatus claimStatus;
    private InsurancePolicy insurancePolicy;
    
 
}
