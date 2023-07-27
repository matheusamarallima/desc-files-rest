package com.files.filesdemo.controller;


import com.files.filesdemo.entity.Course;
import com.files.filesdemo.entity.Student;
import com.files.filesdemo.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/courses")
public class CourseController {

    CourseService courseService;

    @GetMapping("/{id}")
    private ResponseEntity<Course> searchCourseById(@PathVariable Long id){
        return courseService.searchCourseById(id);

    }

    @GetMapping()
    private Page<Course> searchAllStudent(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer itensByPage
    ){
        return courseService.searchAllCourse(PageRequest.of(page, itensByPage));
    }

    @PostMapping()
    private ResponseEntity<Course> addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Course> updateStudent(@PathVariable Long id, @RequestBody Course course){
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }
}

