package pl.kurs.spring.registration.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.kurs.spring.registration.validations.EmailExist;
import pl.kurs.spring.registration.validations.PasswordCheck;

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
