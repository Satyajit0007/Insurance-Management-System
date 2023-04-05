package com.IMTCare.Service;

import java.util.List;

import com.IMTCare.Model.Client;
import com.IMTCare.Model.CurrentUserSession;
import com.IMTCare.Model.LogInDTO;
import com.IMTCare.exception.ClientException;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.UserException;

public interface ClientService {
	
   public CurrentUserSession logIntoAccount(LogInDTO dto) throws LoginException, UserException;
   public String LogoutFromAccount(String key) throws LoginException, UserException;
   public List<Client> getAllClients(String key) throws LoginException, UserException;
   public Client getClientById(Long id, String key) throws LoginException,ClientException;
   public Client saveClient(Client client) throws ClientException;
   public Client updateClient(Long id, Client client, String key) throws UserException;
   public Client deleteClient(Long id, String key) throws UserException,LoginException;
   
}
