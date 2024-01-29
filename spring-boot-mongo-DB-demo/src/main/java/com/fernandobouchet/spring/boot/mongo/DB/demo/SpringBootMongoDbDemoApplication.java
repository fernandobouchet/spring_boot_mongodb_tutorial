package com.fernandobouchet.spring.boot.mongo.DB.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringBootMongoDbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoDbDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address("Argentina","Buenos Aires", "1710");
			Student student = new Student("Fernando", "Bouchet","fernandobouchet@gmail.com", Gender.MALE, address, List.of("Objetos 2", "Programación funcional"), BigDecimal.TEN, LocalDateTime.now());

			//usingMongoTemplateAndQuery(repository, mongoTemplate, student);

			repository.findStudentByEmail(student.getEmail())
					.ifPresentOrElse(s -> {
						System.out.println(s + " already exists");
					}, ()-> {
						System.out.println("Inserting student " + student);
						repository.insert(student);
					});
		};
	}

	private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(student.getEmail()));

		List<Student> students = mongoTemplate.find(query, Student.class);

		if(students.size() > 1) {
			throw new IllegalStateException("found a student with same email " + student.getEmail());
		}

		if(students.isEmpty()) {
			System.out.println("Inserting student " + student);
			repository.insert(student);
		} else {
			System.out.println(student + " already exists");
		}
	}
}

