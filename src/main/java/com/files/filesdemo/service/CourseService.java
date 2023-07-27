package com.files.filesdemo.service;

import com.files.filesdemo.entity.Book;
import com.files.filesdemo.entity.Course;
import com.files.filesdemo.entity.Student;
import com.files.filesdemo.repository.CourseEvaluationRepository;
import com.files.filesdemo.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CourseService {


    CourseRepository courseRepository;
    CourseEvaluationRepository courseEvaluationRepository;

    public ResponseEntity<Course> searchCourseById(Long id){

        if(courseRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(courseRepository.findById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public Page<Course> searchAllCourse(
            PageRequest pageRequest
    ){
        return courseRepository.findAll(pageRequest);
    }

    public ResponseEntity<Course> addCourse(Course course){
        if(courseRepository.existsByCourseName(course.getCourseName())){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(null);
        }

        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    public ResponseEntity<Course> updateCourse(Long id,Course course){
        if(courseRepository.existsById(id)){
            course.setId(id);
            courseRepository.save(course);
            return ResponseEntity.status(HttpStatus.OK).body(course);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    public ResponseEntity<String> deleteCourse(Long id){
        if(courseRepository.existsById(id) == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        courseRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
    }
}
