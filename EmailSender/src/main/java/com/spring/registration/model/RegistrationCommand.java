package com.spring.registration.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

import com.spring.registration.validations.EmailExist;
import com.spring.registration.validations.PasswordCheck;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@PasswordCheck
public class RegistrationCommand {
	@Email
	@EmailExist
	private String email;
	
	private String password;
	private String checkPassword;
}
