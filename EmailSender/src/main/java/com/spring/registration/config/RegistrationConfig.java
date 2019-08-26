package pl.kurs.spring.registration.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrationConfig {
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
}
