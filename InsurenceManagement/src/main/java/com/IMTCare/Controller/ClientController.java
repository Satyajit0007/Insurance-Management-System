package com.IMTCare.Controller;

import java.util.List;

import javax.validation.Valid;

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
import com.IMTCare.Model.Client;
import com.IMTCare.Model.CurrentUserSession;
import com.IMTCare.Model.LogInDTO;
import com.IMTCare.Service.ClientService;
import com.IMTCare.exception.ClientException;
import com.IMTCare.exception.LoginException;
import com.IMTCare.exception.UserException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
     
	@Autowired
	private ClientService clientService;
	

	@PostMapping("/")
	public ResponseEntity<Client> registerClientHandler(@Valid @RequestBody Client cilent) throws ClientException{
		Client newClient = clientService.saveClient(cilent);
		return new ResponseEntity<Client>(newClient,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> loginClientHandler(@RequestBody LogInDTO dto) throws LoginException, UserException{
		CurrentUserSession activeClient = clientService.logIntoAccount(dto);
		return new ResponseEntity<CurrentUserSession>(activeClient,HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logoutClientHandler(@RequestParam String key  ) throws LoginException, UserException{
		String activeClient = clientService.LogoutFromAccount(key);
		return new ResponseEntity<String>(activeClient,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<Client>> getAllClienstHandler(@RequestParam String key  ) throws LoginException, UserException{
		List<Client> allClient = clientService.getAllClients(key);
		return new ResponseEntity<List<Client>>(allClient,HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientByIdHandler(@RequestParam String key ,@PathVariable("id") Long id ) throws LoginException, ClientException {
		Client client = clientService.getClientById(id, key);
		return new ResponseEntity<Client>(client,HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClientByIdHandler(@RequestParam String key , @RequestBody Client client ,@PathVariable("id") Long id ) throws LoginException, ClientException, UserException {
		Client updatedClient = clientService.updateClient(id, client, key);
		return new ResponseEntity<Client>(updatedClient,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Client> deleteClientByIdHandler(@RequestParam String key,@PathVariable("id") Long id ) throws LoginException, ClientException, UserException {
		Client updatedClient = clientService.deleteClient(id, key);
		return new ResponseEntity<Client>(updatedClient,HttpStatus.OK);
	}
	
	
}
