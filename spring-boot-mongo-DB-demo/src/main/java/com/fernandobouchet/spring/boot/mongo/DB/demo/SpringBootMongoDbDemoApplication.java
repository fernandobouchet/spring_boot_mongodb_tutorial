package com.fernandobouchet.spring.boot.mongo.DB.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringBootMongoDbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoDbDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository) {
		return args -> {
			Address address = new Address("Argentina","Buenos Aires", "1710");
			Student student = new Student("Fernando", "Bouchet","fernandobouchet@gmail.com", Gender.MALE, address, List.of("Objetos 2", "Programación funcional"), BigDecimal.TEN, LocalDateTime.now());

			repository.insert(student);
		};
	}
}
