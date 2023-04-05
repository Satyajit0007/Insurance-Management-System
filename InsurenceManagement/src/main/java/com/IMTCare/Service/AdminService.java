package com.IMTCare.Service;

import com.IMTCare.Model.Admin;
import com.IMTCare.Model.CurrentUserSession;
import com.IMTCare.Model.LogInDTO;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.UserException;

public interface AdminService {
  
	public Admin registerAdmin(Admin admin) throws UserException;
    public CurrentUserSession logIntoAccount(LogInDTO dto) throws LoginException, UserException;
	public String LogoutFromAccount(String key) throws LoginException, UserException;
	
}
