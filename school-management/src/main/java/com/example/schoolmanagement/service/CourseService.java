package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Course;
import com.example.schoolmanagement.model.CourseStatistics;
import com.example.schoolmanagement.model.Student;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long courseId);
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(Long courseId);
    void registerStudentForCourse(Long courseId, Long studentId);
    void unregisterStudentFromCourse(Long courseId, Long studentId);

    void registerCourse(Long studentId, Long courseId);

    void unregisterCourse(Long studentId, Long courseId);

    List<Course> getRegisteredCourses(Long studentId);

    List<Student> getStudentsByCourseId(Long courseId);

    CourseStatistics getMostPopularCourses();
}