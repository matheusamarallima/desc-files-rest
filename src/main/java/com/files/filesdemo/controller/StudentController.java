package com.files.filesdemo.controller;

import com.files.filesdemo.entity.Student;
import com.files.filesdemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("students/{id}")
    private ResponseEntity<Student> searchStudentById(@PathVariable Long id){
        return studentService.searchStudentById(id);

    }

    @GetMapping("/students")
    private Page<Student> searchAllStudent(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer itensByPage
    ){
        return studentService.searchAllStudent(PageRequest.of(page, itensByPage));
    }

    @PostMapping("/students")
    private ResponseEntity<Student> addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PutMapping("students/{id}")
    private ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("students/{id}")
    private ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }
}
