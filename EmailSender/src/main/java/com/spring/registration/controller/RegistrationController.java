package com.spring.registration.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.catalina.filters.ExpiresFilter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.registration.dto.UserDto;
import com.spring.registration.model.RegistrationCommand;
import com.spring.registration.model.User;
import com.spring.registration.repository.UserRepository;
import com.spring.registration.repository.VeryficationTokenRepository;
import com.spring.registration.service.EmailSender;
import com.spring.registration.token.VeryficationToken;

import lombok.Data;

@Data
@RestController
@RequestMapping("/register")
public class RegistrationController {
	private final UserRepository userRepo;
	private final ModelMapper mapper;
	private final EmailSender veryficationMail;
	private final VeryficationTokenRepository tokenRepository;

	@PostMapping("/")
	public ResponseEntity<UserDto> addNewUser(@RequestBody @Valid RegistrationCommand registrationCommand) {

		String uniqueID = UUID.randomUUID().toString();
		VeryficationToken newToken = VeryficationToken.builder().token(uniqueID).createDate(new Date()).build();
		tokenRepository.saveAndFlush(newToken);
		Date expiration = newToken.getCreateDate();
		Calendar c = Calendar.getInstance();
		c.setTime(expiration);
		c.add(Calendar.DATE, 1);
		expiration = c.getTime();
		newToken.setExpiredDate(expiration);

		VeryficationToken saved = tokenRepository.saveAndFlush(newToken);

		User newUser = User.builder().email(registrationCommand.getEmail()).passoword(registrationCommand.getPassword())
				.token(saved).build();

		veryficationMail.sendMail(newUser, newToken.getToken());

		return new ResponseEntity<UserDto>(mapper.map(userRepo.saveAndFlush(newUser), UserDto.class), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Void> veryfication(@RequestParam("token") String token, @RequestParam("email") String email) {
		if (!tokenRepository.existsByToken(token)) {
			return ResponseEntity.badRequest().build();
		}
		if (userRepo.existsByEmail(email)) {
			User user = userRepo.findByEmail(email);
			user.setActive(true);
			userRepo.saveAndFlush(user);
			if (!user.getToken().getToken().equals(token)) {
				return ResponseEntity.badRequest().build();
			}
		}
		VeryficationToken vt = tokenRepository.findByToken(token);
		Calendar cal = Calendar.getInstance();
		if (vt.getExpiredDate().getTime() - cal.getTime().getTime() <= 0) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.noContent().build();

	}

}
