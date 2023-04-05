package com.IMTCare.Model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "claims")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Claim {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @JsonIgnore
    private Long userId;
    
    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @JsonIgnore
    @Column(name = "claim_date")
    private LocalDate claimDate;

    
    @JsonIgnore
    @Column(name = "claim_status")
    @Enumerated(EnumType.STRING)
    private ClaimStatus  claimStatus;

    
 
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private InsurancePolicy insurancePolicy;
}



 




