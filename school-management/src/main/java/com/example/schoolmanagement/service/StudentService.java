package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Course;
import com.example.schoolmanagement.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long studentId);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Long studentId);
    void registerCourse(Long studentId, Long courseId);
    void unregisterCourse(Long studentId, Long courseId);
    List<Course> getRegisteredCourses(Long studentId);
    List<Student> getStudentsByCourseId(Long courseId);

}