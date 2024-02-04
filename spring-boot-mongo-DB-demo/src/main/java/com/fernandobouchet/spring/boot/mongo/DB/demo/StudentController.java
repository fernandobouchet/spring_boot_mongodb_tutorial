package com.fernandobouchet.spring.boot.mongo.DB.demo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> fetchAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PutMapping("/{studentId}")
    public void updateStudent(@PathVariable("studentId") String id, @RequestBody Student student) {
        studentService.updateStudent(id,student);
    }

    @GetMapping("/{studentId}")
    public Optional<Student> getStudentById(@PathVariable("studentId") String id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudentById(@PathVariable("studentId") String id) {
        studentService.deleteStudent(id);
    }
}
