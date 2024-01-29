package com.fernandobouchet.spring.boot.mongo.DB.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student,String> {
    Optional<Student> findStudentByEmail(String email);

}
