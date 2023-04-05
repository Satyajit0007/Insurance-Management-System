package com.IMTCare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.IMTCare.Model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long>{

	@Query("SELECT c FROM Claim c WHERE c.insurancePolicy.id = :policyId AND c.userId = :userId")
	List<Claim> findClaimsByPolicyIdAndUserId(@Param("policyId") Long policyId, @Param("userId") Long userId);

	@Query("SELECT c FROM Claim c LEFT JOIN FETCH c.insurancePolicy")
	List<Claim> findAllClaims();
}
