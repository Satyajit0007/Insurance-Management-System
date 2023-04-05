package com.IMTCare.Service;

import java.util.List;

import com.IMTCare.Model.Claim;
import com.IMTCare.Model.ClaimDto;
import com.IMTCare.exception.ClaimException;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.PolicyException;
import com.IMTCare.exception.UserException;

public interface ClaimService {
    public List<ClaimDto> getAllClaims(String key) throws UserException, LoginException;
    public ClaimDto getClaimById(Long policyId,String key) throws LoginException, ClaimException;
	public Claim saveClaim(Claim claim, String key, String policyNumber) throws LoginException, ClaimException, PolicyException, UserException;
	public Claim updateClaim(Long policyId, String key,  String claimStatus) throws LoginException, UserException, ClaimException;
	public Claim deleteClaim(Long id,String key) throws LoginException, ClaimException, UserException;
}

