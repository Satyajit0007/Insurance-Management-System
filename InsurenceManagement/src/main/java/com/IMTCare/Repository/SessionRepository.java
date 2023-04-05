package com.IMTCare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTCare.Model.CurrentUserSession;

public interface SessionRepository extends JpaRepository<CurrentUserSession, Long> {
	public  CurrentUserSession findByUuid(String uuid);
}
