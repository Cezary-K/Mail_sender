package pl.kurs.spring.registration.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import pl.kurs.spring.registration.model.User;

@Service
@AllArgsConstructor
public class EmailSender {
	private final JavaMailSender mailSender;

	@Async
	public void sendMail(User newUser, String token) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(newUser.getEmail());
		msg.setFrom("a@a");
		msg.setSubject("Email Veryfication");
		msg.setText(
				"Dziekujemy za rejestracje \n potwierdz swojego maila klikajac na link ponizej \n http://localhost:8080/register/?token="
						+ token + "&" + "email=" + newUser.getEmail());
		mailSender.send(msg);
	}
}
