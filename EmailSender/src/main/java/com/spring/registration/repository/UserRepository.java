package pl.kurs.spring.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kurs.spring.registration.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	boolean existsByEmail(String email);

	User findByEmail(String email);

	List<User> findByIsActive(boolean isActive);
}
