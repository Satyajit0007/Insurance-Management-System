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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "insurance_policies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsurancePolicy {

	@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name = "policy_number", unique = true, columnDefinition = "VARCHAR(255) NOT NULL UNIQUE COMMENT 'Policy number must be unique'")
    @NotBlank
    private String policyNumber;

    @Column(name = "type")
    @NotBlank
    private String type;

    @Column(name = "coverage_amount")
    @NotNull
    private Long coverageAmount;

    @Min(value = 99)
    @Column(name = "premium")
    private Long premium;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @Future(message = "End date must greater then start date")
    @Column(name = "end_date")
    private LocalDate endDate;

     
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Client> clients  = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "insurancePolicy",cascade = CascadeType.MERGE)
    private List<Claim> claims = new ArrayList<>();

   
 
}

