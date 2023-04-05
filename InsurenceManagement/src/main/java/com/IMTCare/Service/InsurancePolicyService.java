package com.IMTCare.Service;

import java.util.List;

import com.IMTCare.Model.InsurancePolicy;
import com.IMTCare.exception.ClientException;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.PolicyException;
import com.IMTCare.exception.UserException;

public interface InsurancePolicyService {
	
   
   public  List<InsurancePolicy> getAllPolicies();
   public  InsurancePolicy getPolicyById(String policyNumber) throws PolicyException;
   public InsurancePolicy savePolicy(String uniqueKey, InsurancePolicy policy) throws LoginException, UserException;
   public InsurancePolicy updatePolicy(Long id, InsurancePolicy policy,String key) throws PolicyException, LoginException, UserException;
   public InsurancePolicy deletePolicy(Long id, String key) throws UserException, LoginException; 
   public String  buyInsurancePolicy( String  policyNumber,String key) throws LoginException, PolicyException, ClientException;

}
