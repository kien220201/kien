package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Course;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long courseId);
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(Long courseId);
}
