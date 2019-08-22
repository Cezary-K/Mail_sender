package pl.kurs.spring.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kurs.spring.registration.token.VeryficationToken;

public interface VeryficationTokenRepository extends JpaRepository<VeryficationToken, String> {
	List<VeryficationToken> findByUserEmail(String email);

	boolean existsByToken(String token);

	VeryficationToken findByToken(String token);
}
