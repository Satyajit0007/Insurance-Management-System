package com.IMTCare.Implmentation;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IMTCare.Model.Admin;
import com.IMTCare.Model.Client;
import com.IMTCare.Model.CurrentUserSession;
import com.IMTCare.Model.InsurancePolicy;
import com.IMTCare.Model.LogInDTO;
import com.IMTCare.Repository.AdminRepository;
import com.IMTCare.Repository.ClientRepository;
import com.IMTCare.Repository.SessionRepository;
import com.IMTCare.Service.ClientService;
import com.IMTCare.exception.ClientException;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.UserException;

import net.bytebuddy.utility.RandomString;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	@Override
	public List<Client> getAllClients(String key) throws LoginException, UserException {
		CurrentUserSession activeSession = sessionRepo.findByUuid(key);
        if(activeSession == null ) {
       	 throw new LoginException("PLease Login first");
        }
        Optional<Admin> existingAdmin = adminRepo.findById(activeSession.getCurrentUserId());
        if(existingAdmin.isPresent()) {
        	 return clientRepo.findAll();
        }else {
         	 throw new UserException("Only admin can view all client");
        }
		
	}

	@Override
	public Client getClientById(Long id, String key) throws LoginException,ClientException {
		CurrentUserSession activeSession = sessionRepo.findByUuid(key);
		 if(activeSession == null ) {
	       	 throw new LoginException("PLease Login first");
	        }else{
	        	Optional<Client> fetchClient = clientRepo.findById(id);
	        	if(fetchClient.isEmpty()) {
	        		throw  new ClientException("Invalid client id");
	        	}else {
	        		return fetchClient.get();
	        	}
	        }
	}

	@Override
	public Client saveClient(Client client) throws ClientException {
		Client exsitingClient = clientRepo.findByMobileNo(client.getMobileNo());
		if(exsitingClient != null) {
			throw new ClientException("Mobile number is already registered");
		}else {
			return clientRepo.save(client);
		}
		
	}

	
	@Override
	public Client updateClient(Long id, Client client, String key) throws UserException{
	     CurrentUserSession activeSession = sessionRepo.findByUuid(key);
	     Optional<Client> exsistingClient =  clientRepo.findById(id);
	     if(activeSession != null && exsistingClient.isPresent()) {
	    	 exsistingClient.get().setFirstName(client.getFirstName());
	    	 exsistingClient.get().setMiddleName(client.getMiddleName());
	    	 exsistingClient.get().setLastName(client.getLastName());
	    	 exsistingClient.get().setAddress(client.getAddress());
	    	 exsistingClient.get().setDob(client.getDob());
	    	 exsistingClient.get().setPassword(client.getPassword());
	    	 if (!exsistingClient.get().getMobileNo().equals(client.getMobileNo())) {
	    		   throw new UserException("Currently Client not allowed to update mobile number");
	    		}else {
	    			exsistingClient.get().setMobileNo(client.getMobileNo());
	    		}
	    	 return clientRepo.save(exsistingClient.get());
	     }else {
	    	 throw new UserException("Please login to update client profile");
	     }
		
	}

	
	@Override
	public Client deleteClient(Long id, String key) throws UserException, LoginException {
		 CurrentUserSession activeSession = sessionRepo.findByUuid(key);
		    if (activeSession == null) {
		        throw new LoginException("No active session found, please login");
		    }
		    Optional<Admin> existingAdmin = adminRepo.findById(activeSession.getCurrentUserId());
		    if (existingAdmin.isEmpty()) {
		        throw new UserException("Only admin can delete any client");
		    }
		    
		    Optional<Client> clientOptional = clientRepo.findById(id);
		    if (clientOptional.isPresent()) {
		        Client client = clientOptional.get();
		       
		        // Remove the client's association with the insurance policies
		        List<InsurancePolicy> insurancePolicies = client.getInsurancePolicies();
		        for (InsurancePolicy policy : insurancePolicies) {
		            policy.getClients().remove(client);
		        }
		        client.setInsurancePolicies(Collections.emptyList());
		        
		        // Delete the client instance
		        clientRepo.delete(client);
		        return client;
		    } else {
		        throw new UserException("Client with id " + id + " not found");
		    }
	}

	
	@Override
	public CurrentUserSession logIntoAccount(LogInDTO dto) throws LoginException, UserException {
		Client existingClient = clientRepo.findByMobileNo(dto.getMobileNo());
		if(existingClient == null) {
			throw new LoginException("Mobile number invalid");
		}
		Optional<CurrentUserSession> validSession = sessionRepo.findById(existingClient.getId());
	    if(validSession.isPresent()) {
	    	throw new UserException("You have already logged in");
	    }else {
	    	String uniqueKey = RandomString.make(7);
	    	CurrentUserSession newLoginSession = new CurrentUserSession(existingClient.getId(),"CLIENT",uniqueKey,LocalDateTime.now());
	        return sessionRepo.save(newLoginSession);
	        
	    }
	
	}

	@Override
	public String LogoutFromAccount(String key) throws LoginException, UserException {
		CurrentUserSession activeSession = sessionRepo.findByUuid(key);
		if(activeSession == null) {
			throw new LoginException("Your are not logged in");
		}else{
			sessionRepo.delete(activeSession);
			return "Logout Succefull";
		}
	}

}
