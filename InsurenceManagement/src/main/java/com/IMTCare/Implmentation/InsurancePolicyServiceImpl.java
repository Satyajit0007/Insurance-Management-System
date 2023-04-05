package com.IMTCare.Implmentation;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IMTCare.Model.Admin;
import com.IMTCare.Model.Client;
import com.IMTCare.Model.CurrentUserSession;
import com.IMTCare.Model.InsurancePolicy;
import com.IMTCare.Repository.AdminRepository;
import com.IMTCare.Repository.ClientRepository;
import com.IMTCare.Repository.InsurancePolicyRepository;
import com.IMTCare.Repository.SessionRepository;
import com.IMTCare.Service.InsurancePolicyService;
import com.IMTCare.exception.ClientException;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.PolicyException;
import com.IMTCare.exception.UserException;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

	@Autowired
	private SessionRepository sessionRepo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private InsurancePolicyRepository insurenceRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public List<InsurancePolicy> getAllPolicies() {
		return insurenceRepo.findAll();
	}

	@Override
	public InsurancePolicy getPolicyById(String policyNumber) throws PolicyException {
		InsurancePolicy policy = insurenceRepo.findByPolicyNumber(policyNumber);
		if(policy != null){
			return policy;
		}else{
			throw new PolicyException("Policy do not exists");
		}
	}

	@Override
	public InsurancePolicy savePolicy(String uniqueKey, InsurancePolicy policy) throws LoginException, UserException {
         CurrentUserSession activeSession = sessionRepo.findByUuid(uniqueKey);
         if(activeSession == null ) {
        	 throw new LoginException("PLease Login first");
         }
         Optional<Admin> existingAdmin = adminRepo.findById(activeSession.getCurrentUserId());
         if(existingAdmin.isPresent()) {
        	 return insurenceRepo.save(policy);
         }else {
        	 throw new UserException("Only admin can add new policy");
         }
	}

	@Override
	public InsurancePolicy updatePolicy(Long id, InsurancePolicy policy,String key) throws PolicyException, LoginException, UserException {
		Optional<InsurancePolicy> updatePolicy = insurenceRepo.findById(id);
		
		CurrentUserSession validSession = sessionRepo.findByUuid(key);
		if(validSession == null) {
			throw new LoginException("Please login to update policy");
		}
		Optional<Admin> admin = adminRepo.findById(validSession.getCurrentUserId());
		
		
		if(admin.isEmpty()) {
			throw new UserException("Only admin can update the policy");
		}
		else{
			
			if(updatePolicy.isPresent()) {
				InsurancePolicy newUpdatedPolicy = updatePolicy.get();
				newUpdatedPolicy.setPolicyNumber(policy.getPolicyNumber());
				newUpdatedPolicy.setType(policy.getType());
				newUpdatedPolicy.setCoverageAmount(policy.getCoverageAmount());
				newUpdatedPolicy.setPremium(policy.getPremium());
				newUpdatedPolicy.setStartDate(policy.getStartDate());
				newUpdatedPolicy.setEndDate(policy.getEndDate());
				return insurenceRepo.save(newUpdatedPolicy);
			}
			else {
				throw new PolicyException("Invalid policy id");
			}
		
		}
		
	
	}

@Override
public InsurancePolicy deletePolicy(Long id, String key) throws UserException, LoginException {

	
	   Optional<InsurancePolicy> updatePolicy = insurenceRepo.findById(id);
	   
	   CurrentUserSession validSession = sessionRepo.findByUuid(key);
		
	   if(validSession == null) {
			throw new LoginException("Please login to delete policy");
		}
		Optional<Admin> admin = adminRepo.findById(validSession.getCurrentUserId());
		
		if(admin.isEmpty()) {
			throw new UserException("Only admin can delete the policy");
		}else {
			 insurenceRepo.deleteById(id);
			 return updatePolicy.get();
		}
	}

@Override
@Transactional
public String buyInsurancePolicy(String  policyNumber, String key) throws LoginException, PolicyException, ClientException {
	 CurrentUserSession validSession = sessionRepo.findByUuid(key);
	 if(validSession == null) {
			throw new LoginException("Please login to buy policy");
		}
	 
	 InsurancePolicy policy = insurenceRepo.findByPolicyNumber(policyNumber);
	 Client client = clientRepo.findById(validSession.getCurrentUserId()).orElseThrow(()-> new ClientException("Please login first"));
	 
	 if(client.getInsurancePolicies().contains(policy)) {
		  throw new ClientException("Client already has the policy with number : " + policyNumber);
	 }
	 
	 client.getInsurancePolicies().add(policy);
     policy.getClients().add(client);

     clientRepo.save(client);
     insurenceRepo.save(policy);
     return ("You have successfully purchased" + policy.getType());
     
    }


}
