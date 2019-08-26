package com.spring.registration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.registration.model.User;
import com.spring.registration.repository.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AutoSender {

	private final UserRepository userRepo;
	AutoEmailSender aes;

	@Scheduled(cron = "0 * * * * *")
	public void sendEmail() {
		System.out.println(" --------------------------------- ");
		List<User> activeUsers = userRepo.findByIsActive(true);
		for (User u : activeUsers) {
			aes.sendMail(u);
			System.out.println("sending email to : " + u.getEmail());
		}

	}
}
