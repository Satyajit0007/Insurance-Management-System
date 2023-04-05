package com.IMTCare.Controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IMTCare.Model.InsurancePolicy;
import com.IMTCare.Service.InsurancePolicyService;
import com.IMTCare.exception.ClientException;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.PolicyException;
import com.IMTCare.exception.UserException;

@RestController
@RequestMapping("/api/policies")
public class InsurancePolicyController {

	@Autowired
	private InsurancePolicyService policyService;

	@GetMapping("/")
	public ResponseEntity<List<InsurancePolicy>> getAllPolicyHolder(){
		List<InsurancePolicy> allPolicy = policyService.getAllPolicies();
		return new ResponseEntity<List<InsurancePolicy>>(allPolicy,HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<InsurancePolicy> getPolicyByIdHandler(@PathVariable("id") String policyNumber) throws PolicyException{
		InsurancePolicy allPolicy = policyService.getPolicyById(policyNumber);
		return new ResponseEntity<InsurancePolicy>(allPolicy,HttpStatus.OK);
	}
	 
	@PostMapping("/")
	public ResponseEntity<InsurancePolicy> savePolicyHandler(@RequestBody InsurancePolicy policy, @RequestParam String key) throws LoginException, UserException{
		InsurancePolicy savePolicy = policyService.savePolicy(key, policy);
		return new ResponseEntity<InsurancePolicy>(savePolicy,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<InsurancePolicy> updatePolicyHandler(@PathVariable("id") Long id, @RequestBody InsurancePolicy policy, @RequestParam String key) throws PolicyException, LoginException, UserException {
		InsurancePolicy savePolicy = policyService.updatePolicy(id, policy, key);
		return new ResponseEntity<InsurancePolicy>(savePolicy,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<InsurancePolicy> deletePolicyHandler(@PathVariable("id") Long id,  @RequestParam String key) throws PolicyException, LoginException, UserException {
		InsurancePolicy savePolicy = policyService.deletePolicy(id, key);
		return new ResponseEntity<InsurancePolicy>(savePolicy,HttpStatus.OK);
	}

	   @PostMapping("/buyPolicy/{id}")
	    public ResponseEntity<String> buyPolicyHandler(@PathVariable("id") String policyNumber, @RequestParam String key) throws LoginException, PolicyException, ClientException {
	    String res = policyService.buyInsurancePolicy(policyNumber, key);
	    return new  ResponseEntity<String>(res,HttpStatus.OK);
	    }
}
