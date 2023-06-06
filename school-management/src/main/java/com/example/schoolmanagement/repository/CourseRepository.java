package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Custom methods if needed
}
