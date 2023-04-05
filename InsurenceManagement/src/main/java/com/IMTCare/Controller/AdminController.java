package com.IMTCare.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.IMTCare.Model.Admin;
import com.IMTCare.Model.CurrentUserSession;
import com.IMTCare.Model.LogInDTO;
import com.IMTCare.Service.AdminService;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.UserException;

@RestController
@RequestMapping("api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/")
	public ResponseEntity<Admin> adminRegistrationHandler(@RequestBody Admin admin) throws UserException{
		Admin newAdmin = adminService.registerAdmin(admin);
	    return new ResponseEntity<Admin>(newAdmin,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> adminLoginHandler(@RequestBody LogInDTO dto) throws LoginException, UserException {
		CurrentUserSession activeAdmin = adminService.logIntoAccount(dto);
	    return new ResponseEntity<CurrentUserSession>(activeAdmin,HttpStatus.OK);
	}
	
	
	@PostMapping("/logout")
	public ResponseEntity<String> adminLogoutHandler(@RequestParam String key) throws LoginException, UserException {
		String logoutAdmin = adminService.LogoutFromAccount(key);
	    return new ResponseEntity<String>(logoutAdmin,HttpStatus.OK);
	}
	
}
