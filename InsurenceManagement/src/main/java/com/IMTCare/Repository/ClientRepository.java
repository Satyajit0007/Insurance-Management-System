package com.IMTCare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.IMTCare.Model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findByMobileNo(String mobileNo);
	
}