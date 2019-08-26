package com.spring.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.registration.token.VeryficationToken;

public interface VeryficationTokenRepository extends JpaRepository<VeryficationToken, String> {
	List<VeryficationToken> findByUserEmail(String email);

	boolean existsByToken(String token);

	VeryficationToken findByToken(String token);
}
