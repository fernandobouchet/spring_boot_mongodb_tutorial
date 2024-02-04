package com.fernandobouchet.spring.boot.mongo.DB.demo;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public void updateStudent(String id,Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);

        existingStudent.ifPresentOrElse(updateStudent ->
                {
                    updateStudent.setFirstName(student.getFirstName());
                    updateStudent.setEmail(student.getEmail());
                    updateStudent.setLastName(student.getLastName());
                    updateStudent.setGender(student.getGender());
                    updateStudent.setAddress(student.getAddress());
                    updateStudent.setFavouriteSubjects(student.getFavouriteSubjects());
                    updateStudent.setTotalSpentInBooks(student.getTotalSpentInBooks());
                    studentRepository.save(updateStudent);
                },
        () -> {
             new ResponseEntity<>(
                    "student not found",
                    HttpStatus.BAD_REQUEST
            );
            }
        );
    }
}
