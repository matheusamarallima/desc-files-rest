package com.files.filesdemo.repository;

import com.files.filesdemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByName(String studentName);

    Optional<Student> findByName(String student);
}
