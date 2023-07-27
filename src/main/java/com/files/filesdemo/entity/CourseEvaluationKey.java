package com.files.filesdemo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CourseEvaluationKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "student_id")
    Long studentId;

    @Column(name = "course_id")
    Long courseId;

}
