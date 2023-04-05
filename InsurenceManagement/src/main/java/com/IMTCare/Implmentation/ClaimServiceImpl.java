package com.IMTCare.Implmentation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IMTCare.Model.Admin;
import com.IMTCare.Model.Claim;
import com.IMTCare.Model.ClaimDto;
import com.IMTCare.Model.ClaimStatus;
import com.IMTCare.Model.Client;
import com.IMTCare.Model.CurrentUserSession;
import com.IMTCare.Model.InsurancePolicy;
import com.IMTCare.Repository.AdminRepository;
import com.IMTCare.Repository.ClaimRepository;
import com.IMTCare.Repository.ClientRepository;
import com.IMTCare.Repository.InsurancePolicyRepository;
import com.IMTCare.Repository.SessionRepository;
import com.IMTCare.Service.ClaimService;
import com.IMTCare.exception.ClaimException;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.PolicyException;
import com.IMTCare.exception.UserException;

@Service
public class ClaimServiceImpl  implements ClaimService{

	@Autowired
	private ClaimRepository claimRepo;
	
	@Autowired
	private SessionRepository sessionRepo; 
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private InsurancePolicyRepository insurancePolicyRepo;
	
	@Override
	public List<ClaimDto> getAllClaims(String key) throws UserException, LoginException {

		CurrentUserSession activeSession = sessionRepo.findByUuid(key);
		if(activeSession == null) {
			throw new UserException("Please login to see all claims");
		}
		Admin admin = adminRepo.findById(activeSession.getCurrentUserId()).orElse(null);
		
		if(admin == null) {
			throw new LoginException("Admin Access Only");
		}
		
		 List<Claim> claims = claimRepo.findAllClaims();
		    return claims.stream().map(claim -> {
		        ClaimDto claimDto = new ClaimDto();
		        BeanUtils.copyProperties(claim, claimDto);
		        return claimDto;
		    }).collect(Collectors.toList());
	}



	
	@Override
	public Claim saveClaim(Claim claim, String key, String  policyNumber) throws LoginException, ClaimException, PolicyException, UserException {
		
		 CurrentUserSession activeSession = sessionRepo.findByUuid(key);
		    if(activeSession == null) {
		        throw new LoginException("Please login first");
		    } else {
		       
		        InsurancePolicy policy = insurancePolicyRepo.findByPolicyNumber(policyNumber);
		        if (policy == null) {
		            throw new PolicyException("Invalid policy Number");
		        }

		        Client exsistingClient = clientRepo.findById(activeSession.getCurrentUserId()).orElse(null);
		        
		        if(!exsistingClient.getInsurancePolicies().contains(policy)) {
		        	throw new UserException("User dont have this policy");
		        }
		        
		        
		        List<Claim> existingClaim = claimRepo.findClaimsByPolicyIdAndUserId(policy.getId(), activeSession.getCurrentUserId());
		        if (existingClaim.size() != 0 ) {
		            throw new ClaimException("A claim already exists for this policy and user");
		        }
		        
		        claim.setUserId(activeSession.getCurrentUserId());
		        claim.setClaimDate(LocalDate.now());
		        claim.setClaimStatus(ClaimStatus.PENDING);

		        claim.setInsurancePolicy(policy);
		        
		        return claimRepo.save(claim);
		    }
		
	}

	
	@Override
	@Transactional
	public Claim updateClaim(Long id,  String key,  String claimStatus) throws LoginException, UserException, ClaimException {
		
		CurrentUserSession activeSession = sessionRepo.findByUuid(key);
		if(activeSession == null) {
			throw new LoginException("Please to update claim");
			
		}
		Claim claim = claimRepo.findById(id).orElse(null);
		if(claim == null) {
			throw new ClaimException("You have not apllied claim with this claim id : " + id);
		}
		Optional<Admin> validAdmin = adminRepo.findById(activeSession.getCurrentUserId());
		  if(validAdmin.isPresent()) {
		        switch (claimStatus) {
		            case "OPEN":
		                claim.setClaimStatus(ClaimStatus.OPEN);
		                break;
		            case "CLOSED":
		                claim.setClaimStatus(ClaimStatus.CLOSED);
		                break;
		            case "REJECTED":
		                claim.setClaimStatus(ClaimStatus.REJECTED);
		                break;
		            case "PENDING":
		                claim.setClaimStatus(ClaimStatus.PENDING);
		                break;
		            case "ACCEPTED":
		                claim.setClaimStatus(ClaimStatus.ACCEPTED);
		                break;
		            default:
		                throw new IllegalArgumentException("Invalid claim status value: Please choose from (OPEN/CLOSED/REJECTED/PENDING) ");
		        }
		        
		        return claimRepo.save(claim);
		    } else {
		        throw new UserException("Only admin is allowed to update the claim");
		    }
		
		
	}

	
	@Override
	public Claim deleteClaim(Long id,String key) throws LoginException, ClaimException, UserException {                   
		CurrentUserSession activeSession = sessionRepo.findByUuid(key);
		if(activeSession == null) {
			throw new LoginException("Please login to delete claim");
		}
		
		Optional<Admin> validAdmin = adminRepo.findById(activeSession.getCurrentUserId());
		  if(validAdmin.isPresent()) {
		        Optional<Claim> deletedclaim = claimRepo.findById(id);
		        if(deletedclaim.isPresent()) {
		        	claimRepo.delete(deletedclaim.get());
		        	return deletedclaim.get();
		        }else {
		        	throw new ClaimException("Invalid claim id");
		        }
		    } else {
		        throw new UserException("Only admin is allowed to Delete the claim");
		    }
	}


	@Override
	public ClaimDto getClaimById(Long id, String key) throws LoginException, ClaimException {
	    CurrentUserSession activeSession = sessionRepo.findByUuid(key);
	    if (activeSession == null) {
	        throw new LoginException("Please login to access");
	    } else {
	        Claim claim = claimRepo.findById(id).orElse(null);
	        if (claim == null) {
	            throw new ClaimException("Invalid claim ID");
	        } else {
	            return mapClaimToClaimDto(claim);
	        }
	    }
	}

	private ClaimDto mapClaimToClaimDto(Claim claim) {
	    ClaimDto claimDto = new ClaimDto();
	    claimDto.setId(claim.getId());
	    claimDto.setUserId(claim.getUserId());
	    claimDto.setDescription(claim.getDescription());
	    claimDto.setClaimDate(claim.getClaimDate());
	    claimDto.setClaimStatus(claim.getClaimStatus());
	    claimDto.setInsurancePolicy(claim.getInsurancePolicy());
	    return claimDto;
	}


}
