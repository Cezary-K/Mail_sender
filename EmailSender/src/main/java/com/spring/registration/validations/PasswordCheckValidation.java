package pl.kurs.spring.registration.validations;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.kurs.spring.registration.model.RegistrationCommand;

public class PasswordCheckValidation implements ConstraintValidator<PasswordCheck, RegistrationCommand> {

	@Override
	public boolean isValid(RegistrationCommand value, ConstraintValidatorContext context) {
		return Optional.ofNullable(value.getPassword()).map(p -> p.equals(value.getCheckPassword())).orElse(false);
	}

}
