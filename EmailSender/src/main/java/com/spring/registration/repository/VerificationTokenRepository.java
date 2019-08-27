package com.spring.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.registration.token.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
	List<VerificationToken> findByUserEmail(String email);

	boolean existsByToken(String token);

	VerificationToken findByToken(String token);
}
