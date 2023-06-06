package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long studentId);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Long studentId);
}
