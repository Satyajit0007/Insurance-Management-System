package com.IMTCare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTCare.Model.InsurancePolicy;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long>{

	InsurancePolicy findByPolicyNumber(String policyNumber);

}
