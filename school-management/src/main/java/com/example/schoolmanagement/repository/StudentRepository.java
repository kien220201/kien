package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Object> findById(Integer studentId);
    // Custom methods if needed
}
