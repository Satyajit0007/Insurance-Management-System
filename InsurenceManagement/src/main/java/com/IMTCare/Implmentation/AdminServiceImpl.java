package com.IMTCare.Implmentation;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IMTCare.Model.Admin;
import com.IMTCare.Model.CurrentUserSession;
import com.IMTCare.Model.LogInDTO;
import com.IMTCare.Repository.AdminRepository;
import com.IMTCare.Repository.SessionRepository;
import com.IMTCare.Service.AdminService;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.UserException;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private SessionRepository sessionRepo;

	@Override
	public CurrentUserSession logIntoAccount(LogInDTO dto) throws LoginException, UserException {
		Admin existingAdmin = adminRepo.findByMobileNo(dto.getMobileNo());
		if(existingAdmin == null) {
			throw new UserException("Invalid mobile number");
		}
		
		Optional<CurrentUserSession> validUser = sessionRepo.findById(existingAdmin.getAdminId());
		if(validUser.isPresent()) {
			 throw new UserException("You have already logged in");
		}
		if(existingAdmin.getPassword().equals(dto.getPassword())) {
			String uniqueKey = RandomString.make(7);
			CurrentUserSession currentUser = new CurrentUserSession(existingAdmin.getAdminId(), "ADMIN", uniqueKey, LocalDateTime.now());
		    sessionRepo.save(currentUser);
		    return currentUser;
		}else {
			throw new LoginException("Wrong password");
		}
		
		
		
	}

	@Override
	public String LogoutFromAccount(String key) throws LoginException, UserException {
	   CurrentUserSession cUser = sessionRepo.findByUuid(key);
	   if(cUser == null) {
		   throw new LoginException("No Active Session Found");
	   }else{
		   sessionRepo.delete(cUser);
		   return "Logout successfully"; 
	   }
		
		
	}

	@Override
	public Admin registerAdmin(Admin admin) throws UserException {
		Admin newAdmin = adminRepo.findByMobileNo(admin.getMobileNo());
		if(newAdmin == null) {
			return adminRepo.save(admin);
		}else {
			throw new UserException("You are already registered");
		}
	}

}
