package com.files.filesdemo.service;

import com.files.filesdemo.entity.Book;
import com.files.filesdemo.entity.Student;
import com.files.filesdemo.repository.BooksRepository;
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

import java.util.*;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BooksRepository booksRepository;

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
        if(studentRepository.existsByName(student.getName())){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(null);
        }
        Set<Book> bookSet = student.getBooks();
        student.setBooks(new HashSet<>());
        Student student1 = studentRepository.save(student);
        for(Book book : bookSet){
            book.setStudent(Student.builder().id(student.getId()).build());
            student.getBooks().add(booksRepository.save(book));
        }

        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    public ResponseEntity<Student> updateStudent(Student student){
        if(studentRepository.existsById(student.getId()) == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        student.setId(student.getId());
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