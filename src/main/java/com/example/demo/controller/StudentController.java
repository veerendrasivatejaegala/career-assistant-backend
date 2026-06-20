package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/post")
    public Student saveStudent(
            @RequestBody Student student) {

        return service.save(student);
    }
    @GetMapping
    public List<Student> getAllStudents() {

        return service.getAllStudents();
    }
}