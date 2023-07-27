package com.files.filesdemo.service;


import com.files.filesdemo.entity.Course;
import com.files.filesdemo.entity.CourseEvaluation;
import com.files.filesdemo.entity.CourseEvaluationKey;
import com.files.filesdemo.entity.Student;
import com.files.filesdemo.repository.CourseEvaluationRepository;
import com.files.filesdemo.repository.CourseRepository;
import com.files.filesdemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseEvaluationService {

    private final CourseEvaluationRepository courseEvaluationRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public ResponseEntity<String> evaluate(Long idStudent, String nameCourse, Integer evaluCourse){
        Optional<Student> studentOptional = studentRepository.findById(idStudent);
        if(!studentOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        Optional<Course> courseOptional = courseRepository.findByCourseName(nameCourse);
        if(!courseOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        CourseEvaluation courseEvaluation = new CourseEvaluation();
        courseEvaluation.setStudent(studentOptional.get());
        courseEvaluation.setCourse(courseOptional.get());
        courseEvaluation.setEvaluationGrade(evaluCourse);

        courseEvaluationRepository.save(courseEvaluation);
        return ResponseEntity.ok("Evaluation saved");
    }
}
