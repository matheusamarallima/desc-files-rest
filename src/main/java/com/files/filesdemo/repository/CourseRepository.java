package com.files.filesdemo.repository;

import com.files.filesdemo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "Select * From course c Where c.courseName = :courseName", nativeQuery = true)
    Optional<Course> findByCourseName(@Param("courseName") String courseName);
    boolean existsByCourseName(String courseName);

}
