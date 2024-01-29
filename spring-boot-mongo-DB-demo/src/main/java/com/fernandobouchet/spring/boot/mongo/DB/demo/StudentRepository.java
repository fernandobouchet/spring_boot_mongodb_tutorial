package com.fernandobouchet.spring.boot.mongo.DB.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {
}
