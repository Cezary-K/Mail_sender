package com.spring.registration.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Service;

import com.spring.registration.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailExistValidation implements ConstraintValidator<EmailExist, String> {
	private final UserRepository userRepo;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		return !userRepo.existsByEmail(value);
	}

}
