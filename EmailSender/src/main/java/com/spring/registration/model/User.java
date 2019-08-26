package pl.kurs.spring.registration.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kurs.spring.registration.token.VeryficationToken;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String passoword;
	private boolean isActive;
	@OneToOne()
	@JoinColumn(name = "user_id")
	private VeryficationToken token;
}
