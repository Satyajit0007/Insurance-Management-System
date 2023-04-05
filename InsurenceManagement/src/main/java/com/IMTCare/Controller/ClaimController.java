package com.IMTCare.Controller;

import java.util.List;

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

import com.IMTCare.Model.Claim;
import com.IMTCare.Model.ClaimDto;
import com.IMTCare.Model.InsurancePolicy;
import com.IMTCare.Service.ClaimService;
import com.IMTCare.exception.ClaimException;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.PolicyException;
import com.IMTCare.exception.UserException;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

	@Autowired
	private ClaimService claimService;
	
	
	@GetMapping("/")
	public ResponseEntity<List<ClaimDto>>getAllClaimHandler(@RequestParam String key) throws PolicyException, LoginException, UserException {
		List<ClaimDto> allClaims = claimService.getAllClaims(key); 
		return new ResponseEntity<List<ClaimDto>>(allClaims,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClaimDto>getClaimByIdHandler(@PathVariable("id") Long id, @RequestParam String key) throws PolicyException, LoginException, UserException, ClaimException {
		ClaimDto claim = claimService.getClaimById(id, key); 
		return new ResponseEntity<ClaimDto>(claim,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Claim>saveClaimHandler(@RequestBody Claim claim, @RequestParam String key, @RequestParam String policyNumber) throws PolicyException, LoginException, UserException, ClaimException {
		Claim newClaim = claimService.saveClaim(claim, key,policyNumber);
		return new ResponseEntity<Claim>(newClaim,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Claim>updateClaimHandler(@PathVariable("id") Long id, @RequestBody Claim claim, @RequestParam String key, @RequestParam String claimStatus ) throws PolicyException, LoginException, UserException, ClaimException {
		Claim newClaim = claimService.updateClaim(id,key, claimStatus);
		return new ResponseEntity<Claim>(newClaim,HttpStatus.OK);
	}
    
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Claim>deleteClaimHandler(@PathVariable("id") Long id,@RequestParam String key) throws PolicyException, LoginException, UserException, ClaimException {
		Claim newClaim = claimService.deleteClaim(id, key);
		return new ResponseEntity<Claim>(newClaim,HttpStatus.OK);
	}
}
