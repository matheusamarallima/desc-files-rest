package com.files.filesdemo.repository;

import com.files.filesdemo.entity.CourseEvaluation;
import com.files.filesdemo.entity.CourseEvaluationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEvaluationRepository extends JpaRepository<CourseEvaluation, CourseEvaluationKey> {
}
