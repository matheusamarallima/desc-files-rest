package com.files.filesdemo.service;

import com.files.filesdemo.entity.Student;
import com.files.filesdemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<Student> searchStudentById(Long id){

        if(studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(studentRepository.findById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public Page<Student> searchAllStudent(
            PageRequest pageRequest
    ){
        return studentRepository.findAll(pageRequest);
    }

    public ResponseEntity<Student> addStudent(Student student){
        Student studentFound = student;
        if(studentRepository.existsById(studentFound.getId())){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(null);
        }
        studentRepository.save(studentFound);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentFound);
    }

    public ResponseEntity<Student> updateStudent(Student student){
        if(studentRepository.existsById(student.getId()) == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    public ResponseEntity<String> deleteStudent(Long id){
        if(studentRepository.existsById(id) == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        studentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
    }
}